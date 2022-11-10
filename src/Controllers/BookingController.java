package Controllers;

import Models.Data.*;
import Models.Data.Enums.AgeClass;
import Models.Data.Enums.BookingState;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.SeatType;
import Models.DataStoreManager;
import Views.BookingView;
import Views.ConsoleIOManager;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * BookingController is a Navigation that manages the logic and flow for booking of movie tickets
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-29
 */
public class BookingController implements INavigation {

    private int initialMenuSelection = -1;
    private BookingState currentBookingBookingState = BookingState.GET_MOVIES;
    private BookingTicket bookingTicket = new BookingTicket();
    private LocalDate currentlySelectedDate = LocalDate.now();

    /**
     * Start method implementation for initialization after loading with NavigationController
     * Resets the currently stored variables and displays the selection menu for the user.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        reset();
        BookingView.displayMenu();
        do {
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.readInt();
            }
            switch (initialMenuSelection) {
                case 1 -> createBookingTransaction();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    }

    /**
     * The state machine function that handles the flow of creating a booking transaction.
     */
    public void createBookingTransaction() {
        CineplexController controller = new CineplexController();

        do {
            Movie selectedMovie = bookingTicket.getSelectedMovie();
            Cineplex selectedCineplex = bookingTicket.getSelectedCineplex();
            Cinema selectedCinema = bookingTicket.getSelectedCinema();
            Screening selectedScreening = bookingTicket.getSelectedScreening();
            Customer customer = bookingTicket.getCustomer();
            // State machine
            switch (currentBookingBookingState) {
                case GET_MOVIES ->//===== Get Movies that are NOW_SHOWING
                        {
                            List<Movie> movieList = getMovieList().stream().filter(Movie -> Movie.getMovieStatus() == MovieStatus.NOW_SHOWING).toList();

                            // Display Movie list
                            BookingView.printMovieList(movieList);
                            // TODO handle no movie listings
                            // Get input
                            selectedMovie = (getSelectedMovie(movieList));
                            bookingTicket.setSelectedMovie(selectedMovie);

                            // Did not get any Movie
                            if (selectedMovie == null) {
                                this.initialMenuSelection = -1; // Go back to main menu
                                NavigationController.getInstance().goBack(0);
                            }
                        }
                case GET_CINEPLEX -> //===== Find all available cineplex with selected Movie
                        {
                            List<Cineplex> filteredCineplexList = controller.findCineplexAndCinemaWithSelectedMovie(bookingTicket.getSelectedMovie());

                            // Display available cineplex with selected Movie
                            BookingView.printCineplexList(filteredCineplexList);
                            // TODO handle no Cineplex listings
                            // Get input
                            selectedCineplex = getSelectedCineplex(filteredCineplexList);
                            bookingTicket.setSelectedCineplex(selectedCineplex);

                            // Did not get any Cineplex
                            if (selectedCineplex == null) {
                                currentBookingBookingState = currentBookingBookingState.prev();
                                continue;
                            }
                        }
                case GET_DATE -> //===== Date Picker
                        {
                            // Print list of 7 dates starting from today
                            BookingView.printDatePicker(selectedMovie);

                            // Get input
                            var selectedDate = getDatePicker();
                            this.currentlySelectedDate = selectedDate;

                            // Did not get a date
                            if (selectedDate == null) {
                                currentBookingBookingState = currentBookingBookingState.prev();
                                continue;
                            }
                        }


                case GET_ALL_SHOWTIME -> //===== Display all show times collated amongst all cinemas
                        {
                            List<Cinema> CinemasWithSelectedMovie = selectedCineplex.getCinemasWithMovie(selectedMovie);

                            // Display available time slots
                            BookingView.printCinemaOverview(CinemasWithSelectedMovie, selectedMovie, currentlySelectedDate);
                            // Get input
                            selectedCinema = getSelectedCinema(CinemasWithSelectedMovie);
                            bookingTicket.setSelectedCinema(selectedCinema);

                            // Did not get any Cinema
                            if (selectedCinema == null) {
                                currentBookingBookingState = currentBookingBookingState.prev();
                                continue;
                            }
                        }

                case GET_SHOWTIME_DETAILED -> //===== Display show time picker
                        {
                            List<Screening> filteredScreeningList = selectedCinema.getScreeningList().stream().filter(s -> s.getMovie().getName().equals(bookingTicket.getSelectedMovie().getName()) && s.getShowTime().getDateOfMovie().isEqual(currentlySelectedDate)).toList();
                            // Display available time slots
                            BookingView.printCinemaShowtime(filteredScreeningList, selectedMovie, currentlySelectedDate);
                            // Get input
                            selectedScreening = getSelectedScreening(filteredScreeningList);
                            bookingTicket.setSelectedScreening(selectedScreening);

                            // Did not get any screening
                            if (selectedScreening == null) {
                                currentBookingBookingState = currentBookingBookingState.prev();
                                continue;
                            }
                        }
                case GET_SEAT -> //===== Seat picker
                        {
                            // Get input
                            BookingView.printSeatLayout(selectedScreening.getSessionLayout());
                            ArrayList<Seat> selectedLayout = getSelectedSeat(selectedScreening.getSessionLayout());
                            // Did not get any seats
                            if (selectedLayout == null || selectedLayout.size() == 0) {
                                currentBookingBookingState = currentBookingBookingState.prev();
                                continue;
                            }

                            for(Seat seat : selectedLayout){
                                selectedScreening.getSessionLayout().get(seat.getRow()).set(seat.getColumn(), seat);
                                bookingTicket.setSelectedSeats(seat);
                            }
                        }
                case GET_CUSTOMER_INFO -> //===== Get Customer info
                        {
                            if (customer == null || !customer.isAllFilled()) {
                                customer = getCustomerInfo();
                                // Did not get customer, go back
                                if (customer == null) {
                                    currentBookingBookingState = currentBookingBookingState.prev();
                                    // Customer cancelled
                                    bookingTicket.getSelectedSeats().forEach(seat -> seat.setAssigned(false));
                                    continue;
                                }
                                // Print Final Preview
                                BookingView.printCustomerInfo(customer);
                                bookingTicket.setCustomer(customer);

                            }
                        }
                case GET_CUSTOMER_AGE -> {
                    BookingView.printAgeClassList();
                    AgeClass selectedAgeClass = getSelectedAgeClass();

                    if (selectedAgeClass == null) {
                        bookingTicket.setCustomer(null);
                        currentBookingBookingState = currentBookingBookingState.prev();
                        continue;
                    }
                    bookingTicket.getCustomer().setAgeClass(selectedAgeClass);
                }
                case GET_CONFIRMATION -> //=====Price Check
                        {
                            double price = Setting.getSettings().getPrice(bookingTicket);
                            bookingTicket.setPrice(price);
                            //===== Confirm
                            {
                                BookingView.printCheckout(bookingTicket);
                                boolean confirm = ConsoleIOManager.readConfirm();

                                if (!confirm) {
                                    currentBookingBookingState = currentBookingBookingState.prev();
                                    bookingTicket.setCustomer(new Customer());
                                    continue;
                                }
                            }
                        }
            }
            currentBookingBookingState = currentBookingBookingState.next();
        } while (currentBookingBookingState != BookingState.FINISHED);


        //===== Payment (Success)
        PaymentController paymentController = new PaymentController();
        String TID = paymentController.newPaymentTransaction(bookingTicket);
        bookingTicket.setBookingID(TID);
        if (TID == null || TID.isEmpty()) {
            CancelBookingTransaction();
        }
        //===== All succeeded, and save transaction
        saveBookingTransaction(this.bookingTicket);
    }

    /**
     * Gets the user's desired movie selection input .
     *
     * @param movieList The list of movies for the user to choose from.
     * @return User selected movie.
     */
    private Movie getSelectedMovie(List<Movie> movieList) {
        Movie selectedMovie;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > movieList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                selectedMovie = movieList.get(input - 1);
                break;
            }
        } while (true);
        return selectedMovie;
    }

    /**
     * Gets the user's desired cineplex selection input.
     *
     * @param filteredCineplexList The list of cineplex for the user to choose from.
     * @return User selected cineplex.
     */
    private Cineplex getSelectedCineplex(List<Cineplex> filteredCineplexList) {
        Cineplex selectedCineplex;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > filteredCineplexList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose movie
                return null;
            } else {
                selectedCineplex = filteredCineplexList.get(input - 1);
                break;
            }
        } while (true);
        return selectedCineplex;
    }

    /**
     * Gets the user's desired date selection input.
     *
     * @return User selected date.
     */
    private LocalDate getDatePicker() {
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > 7) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                return LocalDate.now().plus(Period.ofDays(input - 1));
            }
        } while (true);
    }

    /**
     * Gets the user's desired cinema selection input.
     *
     * @param cinemasWithSelectedMovie The list of cinemas that contain the selected movie.
     * @return User selected cinema.
     */
    private Cinema getSelectedCinema(List<Cinema> cinemasWithSelectedMovie) {
        Cinema selectedCinema;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > cinemasWithSelectedMovie.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose cineplex
                return null;
            } else {
                selectedCinema = cinemasWithSelectedMovie.get(input - 1);
                break;
            }
        } while (true);
        return selectedCinema;
    }

    /**
     * Gets the user's customer information. Runs in a loop until all fields are successfully inputted.
     *
     * @return User's customer information.
     */
    private Customer getCustomerInfo() {
        Customer customer = new Customer();
        int intInput = -1;
        String stringInput = "";
        do {
            BookingView.printCustomerInfo(customer);

            if (customer.getName().isEmpty()) {
                ConsoleIOManager.printF("Please enter your Name or choice: ");
                stringInput = ConsoleIOManager.readString();
                if (stringInput.equals("0")) break;
                customer.setName(stringInput);

                continue;
            }
            if (customer.getEmail().isEmpty()) {
                ConsoleIOManager.printF("Please enter your Email or choice: ");
                stringInput = ConsoleIOManager.readString();
                if (stringInput.equals("0")) break;

                customer.setEmail(stringInput);
                continue;
            }
            if (customer.getPhone() == 0) {
                ConsoleIOManager.printF("Please enter your Phone or choice: ");
                intInput = ConsoleIOManager.readInt();
                if (intInput == 0) break;

                customer.setPhone(intInput);
                break;
            }


        } while (true);

        if (stringInput.equals("0") || intInput == 0) {
            //Restart from choose cineplex
            return null;
        }
        return customer;
    }

    /**
     * Gets the user's desired screening selection input.
     *
     * @param filteredScreeningList The list of available screenings to choose from
     * @return User selected screening
     */
    private Screening getSelectedScreening(List<Screening> filteredScreeningList) {
        Screening selectedScreening;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > filteredScreeningList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose cineplex
                return null;
            } else {
                selectedScreening = filteredScreeningList.get(input - 1);
                break;
            }
        } while (true);
        return selectedScreening;
    }

    /**
     * Gets the user's desired seat selection input
     *
     * @param layout The current selected Screening's layout
     * @return User's selected Seat information
     */
    private ArrayList<Seat> getSelectedSeat(ArrayList<ArrayList<Seat>> layout) {
        String input;
        boolean done = false;
        ArrayList<Seat> seats = new ArrayList<>();
        do {
            input = ConsoleIOManager.readString();
            Seat selectedLayout = null;
            // Get input
            if (input.trim().length() >= 2) {
                try {
                    char inputCharArray = input.charAt(0);
                    int row = Character.isLetter(inputCharArray) ? Character.toUpperCase(inputCharArray) - 65 : -1;
                    int col = Integer.parseInt(input.substring(1));
                    if (row >= 0 && row < layout.size() && col >= 0 && col < layout.get(row).size()) {
                        // Get seat layout
                        selectedLayout = layout.get(row).get(col);
                    }
                } catch (NumberFormatException exception) {
                    ConsoleIOManager.printLine("Invalid input! Please select a valid row & column! e.g. A10");
                    continue;
                }
            } else if (input.equals("0")) {
                return null;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select a valid row & column! e.g. A10");
                continue;
            }

            // Found seat, assigning
            if (selectedLayout != null) {
                if (selectedLayout.isAssigned()) {
                    ConsoleIOManager.printLine("Seat is already taken!");
                    continue;
                } else if (selectedLayout.getSeatType() == SeatType.PROHIBITED) {
                    ConsoleIOManager.printLine("Invalid input! Please select a valid row & column! e.g. A10");
                    continue;
                }
                selectedLayout.setAssigned(true);
                //return selectedLayout;
                seats.add(selectedLayout);
            }

            BookingView.printDoneSeatPickingConfirm();
            done = !ConsoleIOManager.readConfirm();
            if(!done){
                BookingView.printSeatLayout(layout);
            }
        } while (!done);

        return seats;
    }

    /**
     * Gets user's selection of age class
     *
     * @return User selected age class
     */
    private AgeClass getSelectedAgeClass() {
        AgeClass selectedClass;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > AgeClass.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                selectedClass = AgeClass.values()[input - 1];
                break;
            }
        } while (true);
        return selectedClass;
    }

    /**
     * Booking was successful. Booking information is saved into the database, and cineplex is updated in the database.
     *
     * @param newBookingTicket the new successful booking transaction
     */
    private void saveBookingTransaction(BookingTicket newBookingTicket) {
        BookingView.printSaveBooking();
        newBookingTicket.getSelectedMovie().incrementTicketSales();
        DataStoreManager.getInstance().addToStore(newBookingTicket);
        // Save everything including Cineplex information
        DataStoreManager.getInstance().saveAll();

        // Go back on user input
        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                break;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * User cancelled booking transaction, display relevant info and return to menu.
     */
    public void CancelBookingTransaction() {
        BookingView.printCancelledBooking();
        // Go back on user input
        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                break;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * Resets the temporary data fields in the object
     */
    private void reset() {
        initialMenuSelection = -1;
        currentBookingBookingState = BookingState.GET_MOVIES;
        bookingTicket = new BookingTicket();
        currentlySelectedDate = LocalDate.now();
    }

    /**
     * Retrieves the Movie list from DataStore
     *
     * @return Arraylist of Movies in the DataStore
     * @see DataStoreManager
     */
    private ArrayList<Movie> getMovieList() {
        return DataStoreManager.getInstance().getStore(Movie.class);
    }
}