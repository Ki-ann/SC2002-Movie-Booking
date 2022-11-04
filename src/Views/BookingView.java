package Views;

import Models.Data.*;
import Models.Data.Enums.AgeClass;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.SeatType;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BookingView class used by BookingController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-29
 * @see Controllers.BookingController
 * @see Views.ConsoleIOManager
 */
public class BookingView {

    /**
     * Prints the selection menu.
     */
    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the booking page.",
                "Start booking");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of movies in the given list.
     * @param movieList List of movies given.
     */
    public static void printMovieList(List<Movie> movieList) {
        String[] movieStringList = movieList.stream()
                .map(Movie::getName)
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Here are the movies currently showing",
                movieStringList);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of cineplex in the given list.
     * @param filteredCineplexList filtered cineplex list that contain a specific movie.
     */
    public static void printCineplexList(List<Cineplex> filteredCineplexList) {
        String[] cineplexStringList = filteredCineplexList.stream()
                .map(Cineplex::getName)
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("The movie is currently screening in the following Cineplex",
                cineplexStringList);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of Cinemas that are showing the currently selected movie and
     * their listed show times collated below the Cinema name.
     * @param filteredCinemaList List of cinemas that contain selectedMovie.
     * @param selectedMovie the currently selected movie by the customer.
     * @param selectedDate the selected date to filter for.
     */
    public static void printCinemaOverview(List<Cinema> filteredCinemaList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = filteredCinemaList.stream()
                .map(model -> model.getName() + " | " + model.getCinemaType().name() +
                        "\r\n\tCurrent Show-Times:\r\n\t" +
                        model.getScreeningList().stream()
                                .filter(s -> s.getMovie().getName().equals(selectedMovie.getName())
                                        && s.getShowTime().getDateOfMovie().isEqual(selectedDate))
                                .map(s -> s.getShowTime().getTimeOfMovie() +
                                        " - " +
                                        s.getShowTime().getTimeOfMovie().plus(s.getMovie().getDuration()).toString())
                                .collect(Collectors.joining(" | ")))
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the available show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu for the customer to choose the specific showtime.
     * @param screeningList the list of screenings in the cinema for the specific date chosen.
     * @param selectedMovie the movie to filter for screeningList.
     * @param selectedDate the date to filter for screeningList.
     */
    public static void printCinemaShowtime(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = screeningList.stream()
                .map(s -> s.getShowTime().getTimeOfMovie() +
                        " - " +
                        s.getShowTime().getTimeOfMovie().plus(s.getMovie().getDuration()).toString())
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the available show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a list of 7 dates from today for the customer to choose for.
     * @param movie currently selected movie.
     */
    public static void printDatePicker(Movie movie) {
        LocalDate[] dates = new LocalDate[7];
        for (int i = 0; i < 7; i++) {
            dates[i] = LocalDate.now().plus(Period.ofDays(i));
        }
        String[] dateString = Arrays.stream(dates).map(date -> date.format(DateTimeFormatter.ofPattern("dd/MM"))).toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Select the date you want to book for for [" + movie.getName() + "]",
                dateString);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints the menu to retrieve customer information.
     * @param customer the current customer instance.
     */
    public static void printCustomerInfo(Customer customer) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Enter customer information");
        ConsoleIOManager.printLine("Name: " + customer.getName());
        ConsoleIOManager.printLine("Email: " + customer.getEmail());
        ConsoleIOManager.printLine("Phone: " + (customer.getPhone() == 0 ? "" : customer.getPhone()));
        ConsoleIOManager.printGoBack();
    }

    /**
     * Print an overview receipt of the current booking transaction.
     * @param bookingTicket the current booking transaction.
     */
    public static void printCheckout(BookingTicket bookingTicket) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Booking Preview");
        ConsoleIOManager.printLine("Movie: " + bookingTicket.getSelectedMovie().getName() +
                (bookingTicket.getSelectedMovie().getMovieStatus() == MovieStatus.PREVIEW? " - Preview" : ""));
        ConsoleIOManager.printF("%s - %s\n", bookingTicket.getSelectedCineplex().getName(), bookingTicket.getSelectedCinema().getName());
        ConsoleIOManager.printF("%s - %s\n", bookingTicket.getSelectedScreening().getShowTime().getDateOfMovie(), bookingTicket.getSelectedScreening().getShowTime().getTimeOfMovie());
        ConsoleIOManager.printF("Seat:  %s | %s | %s\n",bookingTicket.getSelectedSeat().getSeatString(),
                                                            bookingTicket.getSelectedSeat().getSeatType(),
                                                            bookingTicket.getCustomer().getAgeClass());
        ConsoleIOManager.printF("$%.2f (Inclusive of GST/Service Change)\n", bookingTicket.getPrice());
        ConsoleIOManager.printLine("Name: " + bookingTicket.getCustomer().getName());
        ConsoleIOManager.printLine("Email: " + bookingTicket.getCustomer().getEmail());
        ConsoleIOManager.printLine("Phone: " + bookingTicket.getCustomer().getPhone());
        ConsoleIOManager.printConfirm();
    }

    /**
     * Prints the given cinema seat layout 2-D array to a viewable interface for customers to pick.
     * @param layout the given session seat layout.
     */
    public static void printSeatLayout(ArrayList<ArrayList<Seat>> layout){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Pick the seat you want to book for");


        ConsoleIOManager.printLine(ConsoleIOManager.centerString((layout.get(0).size() + 1) * 3 + 3, String.format("%12s","[  Screen  ]"), ' '));
        for (int i = 0; i < layout.size(); i++) {
            ConsoleIOManager.printF("%-3c", (char) (65 + i));
            for (int j = 0; j < layout.get(i).size(); j++) {
                if (layout.get(i).get(j).isAssigned()) {
                    ConsoleIOManager.printF("%3s", "[X]");
                } else {
                    SeatType type = layout.get(i).get(j).getSeatType();
                    switch (type) {
                        case NORMAL -> ConsoleIOManager.printF("%3s", "[ ]");
                        case PROHIBITED -> ConsoleIOManager.printF("%3s", "  ");
                        case SPECIAL_NEEDS -> ConsoleIOManager.printF("%3s", "L ");
                        case COUPLE -> {
                            ConsoleIOManager.printF("%6s", "[____]");
                            j++;
                        }
                    }
                }
            }
            ConsoleIOManager.printLine();
        }
        ConsoleIOManager.printF("%-3s", "");
        for (int i = 0; i < layout.get(layout.size() - 1).size(); i++) {
            ConsoleIOManager.printF(i > 9 ? "%3d" : "%2d%1s", (i), "");
        }
        ConsoleIOManager.printLine();
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a saved booking message.
     */
    public static void printSaveBooking(){
        ConsoleIOManager.printMenu("Saved new booking");
        ConsoleIOManager.printLine();
        ConsoleIOManager.printGoBack();
    }

    /**
     * Print a booking cancel message.
     */
    public static void printCancelledBooking(){
        ConsoleIOManager.printMenu("Booking cancelled");
        ConsoleIOManager.printLine();
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of Age classes
     */
    public static void printAgeClassList() {
        String[] ageStringList = Arrays.stream(AgeClass.values())
                .map(Enum::name)
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Please select your age",
                ageStringList);
        ConsoleIOManager.printGoBack();
    }
}