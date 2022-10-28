package Controllers;

import Controllers.Payment.SimplePayment;
import Models.Data.*;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;
import Views.BookingView;
import Views.ConsoleIOManager;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class BookingController implements INavigation {

    public int initialMenuSelection = -1;
    public BookingTicket bookingTicket = new BookingTicket();
    public LocalDate currentlySelectedDate = LocalDate.now();

    public void Start() {
        BookingView.DisplayMenu();
        do {
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.ReadInt();
            }
            switch (initialMenuSelection) {
                case 1 -> CreateBookingTransaction();
                case 2 -> ViewBookingHistory();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    }

    public void CreateBookingTransaction() {
        Movie selectedMovie = bookingTicket.getSelectedMovie();
        Cineplex selectedCineplex = bookingTicket.getSelectedCineplex();
        Cinema selectedCinema = bookingTicket.getSelectedCinema();
        Screening selectedScreening = bookingTicket.getSelectedScreening();
        Customer customer = bookingTicket.getCustomer();

        //===== Get Movies that are NOW_SHOWING
        if (selectedMovie == null) {
            List<Movie> movieList = GetMovieList().stream().filter(Movie -> Movie.getMovieStatus() == MovieStatus.NOW_SHOWING).toList();

            // Display Movie list
            BookingView.PrintMovieList(movieList);
            // Get input
            selectedMovie = (GetSelectedMovie(movieList));
            bookingTicket.setSelectedMovie(selectedMovie);
        }
        // Did not get any Movie
        if (selectedMovie == null) {
            throw new RuntimeException();
        }

        //// Find all available cineplex with selected Movie
        if (selectedCineplex == null) {
            CineplexController controller = new CineplexController();
            List<Cineplex> filteredCineplexList = controller.FindCineplexAndCinemaWithSelectedMovie(bookingTicket.getSelectedMovie());

            // Display available cineplex with selected Movie
            BookingView.PrintCineplexList(filteredCineplexList);
            // Get input
            selectedCineplex = GetSelectedCineplex(filteredCineplexList);
            bookingTicket.setSelectedCineplex(selectedCineplex);
        }
        //===== Did not get any Cineplex
        if (selectedCineplex == null) {
            throw new RuntimeException();
        }

        //===== Date Picker
        {
            // Print list of 7 dates starting from today
            BookingView.PrintDatePicker(selectedMovie);

            // Get input
            SetSelectedDate(GetDatePicker());
        }

        //===== Display all show times collated amongst all cinemas
        if (selectedCinema == null) {
            List<Cinema> CinemasWithSelectedMovie = selectedCineplex.GetFilteredCinemaList(selectedMovie);

            // Display available time slots
            BookingView.PrintCinemaOverview(CinemasWithSelectedMovie, selectedMovie, currentlySelectedDate);
            // Get input
            selectedCinema = GetSelectedCinema(CinemasWithSelectedMovie);
            bookingTicket.setSelectedCinema(selectedCinema);
        }
        // Did not get any Cinema
        if (selectedCinema == null) {
            throw new RuntimeException();
        }

        //===== Display show time picker
        if (selectedScreening == null) {
            List<Screening> filteredScreeningList = selectedCinema.screeningList.stream()
                    .filter(s->s.movie.getName().equals(bookingTicket.getSelectedMovie().getName())
                            && s.showTime.dateOfMovie.isEqual(currentlySelectedDate)).toList();
            // Display available time slots
            BookingView.PrintCinemaShowtime(filteredScreeningList, selectedMovie, currentlySelectedDate);
            // Get input
            selectedScreening = GetSelectedScreening(filteredScreeningList);
            bookingTicket.setSelectedScreening(selectedScreening);
        }
        // Did not get any screening ?? shouldn't reach here anyway
        if (selectedScreening == null) {
            throw new RuntimeException();
        }

        //===== Seat picker
        {
            // Get input
        }

        //===== Get Customer info
        if(customer == null || !customer.AllFilled()){
            customer = GetCustomerInfo();
            // Print Final Preview
            BookingView.PrintCustomerInfo(customer);
            bookingTicket.setCustomer(customer);
        }

        //=====Price Check
        bookingTicket.setPrice(10.50);
        //===== Confirm
        {
            BookingView.PrintCheckout(bookingTicket);
            ConsoleIOManager.ReadConfirm();
        }

        //===== Payment (Success)
        //PaymentController paymentController = new PaymentController();
        //paymentController.Pay(new SimplePayment());

        //===== All succeeded, and save transaction
        SaveBookingTransaction(this.bookingTicket);
    }

    private Customer GetCustomerInfo() {
        Customer customer = new Customer();
        int intInput = -1;
        String stringInput = "";
        do {
            BookingView.PrintCustomerInfo(customer);

            if (customer.getName().isEmpty()) {
                ConsoleIOManager.PrintF("Please enter your Name: ");
                stringInput = ConsoleIOManager.ReadString();
                if(stringInput.equals("0"))
                    break;
                customer.setName(stringInput);

                continue;
            }
            if (customer.getEmail().isEmpty()) {
                ConsoleIOManager.PrintF("Please enter your Email: ");
                stringInput = ConsoleIOManager.ReadString();
                if(stringInput.equals("0"))
                    break;

                customer.setEmail(stringInput);
                continue;
            }
            if (customer.getPhone() == 0) {
                ConsoleIOManager.PrintF("Please enter your Phone: ");
                intInput = ConsoleIOManager.ReadInt();
                if(intInput == 0)
                    break;

                customer.setPhone(intInput);
                break;
            }


        }while(true);

        if (stringInput.equals("0") || intInput == 0) {
            //Restart from choose cineplex
            this.bookingTicket.setCustomer(null);
            NavigationController.getInstance().goBack(0);
        }
        return customer;
    }

    private Screening GetSelectedScreening(List<Screening> filteredScreeningList) {
        Screening selectedScreening;
        int input;
        do {
            input = ConsoleIOManager.ReadInt();

            if (input < 0 || input > filteredScreeningList.size()) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose cineplex
                this.bookingTicket.setSelectedCineplex(null);
                NavigationController.getInstance().goBack(0);
                return null;
            } else {
                selectedScreening = filteredScreeningList.get(input - 1);
                break;
            }
        } while (true);
        return selectedScreening;
    }

    private Movie GetSelectedMovie(List<Movie> movieList) {
        Movie selectedMovie;
        int input;
        do {
            input = ConsoleIOManager.ReadInt();

            if (input < 0 || input > movieList.size()) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                this.initialMenuSelection = -1;
                NavigationController.getInstance().goBack(0);
                return null;
            } else {
                selectedMovie = movieList.get(input - 1);
                break;
            }
        } while (true);
        return selectedMovie;
    }

    private Cineplex GetSelectedCineplex(List<Cineplex> filteredCineplexList) {
        Cineplex selectedCineplex;
        int input;
        do {
            input = ConsoleIOManager.ReadInt();

            if (input < 0 || input > filteredCineplexList.size()) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose movie
                this.bookingTicket.setSelectedMovie(null);
                NavigationController.getInstance().goBack(0);
                return null;
            } else {
                selectedCineplex = filteredCineplexList.get(input - 1);
                break;
            }
        } while (true);
        return selectedCineplex;
    }

    private LocalDate GetDatePicker() {
        int input;
        do {
            input = ConsoleIOManager.ReadInt();

            if (input < 0 || input > 7) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                this.bookingTicket.setSelectedCineplex(null);
                NavigationController.getInstance().goBack(0);
                return null;
            } else {
                return LocalDate.now().plus(Period.ofDays(input - 1));
            }
        } while (true);
    }

    private Cinema GetSelectedCinema(List<Cinema> cinemasWithSelectedMovie) {
        Cinema selectedCinema;
        int input;
        do {
            input = ConsoleIOManager.ReadInt();

            if (input < 0 || input > cinemasWithSelectedMovie.size()) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                //Restart from choose cineplex
                this.bookingTicket.setSelectedCineplex(null);
                NavigationController.getInstance().goBack(0);
                return null;
            } else {
                selectedCinema = cinemasWithSelectedMovie.get(input - 1);
                break;
            }
        } while (true);
        return selectedCinema;
    }

    public void SaveBookingTransaction(BookingTicket newBookingTicket) {
        //debug
        ConsoleIOManager.PrintLine("Saved new booking");
        DataStoreManager.getInstance().AddToStore(newBookingTicket);
    }

    public void ViewBookingHistory() {
        // TODO - implement Controllers.BookingController.ViewBookingHistory
        throw new UnsupportedOperationException();
    }

    public ArrayList<Movie> GetMovieList() {
        return DataStoreManager.getInstance().GetStore(Movie.class);
    }

    public void SetSelectedDate(LocalDate newDate) {
        this.currentlySelectedDate = newDate;
    }

    public LocalDate GetSelectedDate() {
        return this.currentlySelectedDate;
    }

}