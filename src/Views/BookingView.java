package Views;

import Models.Data.*;
import Models.Data.Enums.MovieStatus;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingView {

    public static void DisplayMenu() {
        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("This is the booking page.",
                "Start booking",
                "See my booking history");
        ConsoleIOManager.PrintGoBack();
    }


    public static void PrintMovieList(List<Movie> movieList) {
        String[] movieStringList = movieList.stream()
                .map(Movie::getName)
                .toArray(String[]::new);

        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("Here are the movies currently showing",
                movieStringList);
        ConsoleIOManager.PrintGoBack();
    }

    public static void PrintCineplexList(List<Cineplex> filteredCineplexList) {
        String[] cineplexStringList = filteredCineplexList.stream()
                .map(Cineplex::getName)
                .toArray(String[]::new);

        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("The movie is currently screening in the following Cineplex",
                cineplexStringList);
        ConsoleIOManager.PrintGoBack();
    }

    public static void PrintCinemaOverview(List<Cinema> filteredCinemaList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = filteredCinemaList.stream()
                .map(model -> model.getName() +
                        "\r\n\tCurrent Show-Times:\r\n\t" +
                        model.screeningList.stream()
                                .filter(s -> s.movie.getName().equals(selectedMovie.getName())
                                        && s.showTime.dateOfMovie.isEqual(selectedDate))
                                .map(s -> s.showTime.timeOfMovie +
                                        " - " +
                                        s.showTime.timeOfMovie.plus(s.movie.getDuration()).toString())
                                .collect(Collectors.joining(" | ")))
                .toArray(String[]::new);

        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("These are the available show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.PrintGoBack();
    }

    public static void PrintCinemaShowtime(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = screeningList.stream()
                .map(s -> s.showTime.timeOfMovie +
                        " - " +
                        s.showTime.timeOfMovie.plus(s.movie.getDuration()).toString())
                .toArray(String[]::new);

        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("These are the available show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.PrintGoBack();
    }


    public static void PrintDatePicker(Movie movie) {
        LocalDate[] dates = new LocalDate[7];
        for (int i = 0; i < 7; i++) {
            dates[i] = LocalDate.now().plus(Period.ofDays(i));
        }
        String[] dateString = Arrays.stream(dates).map(date -> date.format(DateTimeFormatter.ofPattern("dd/MM"))).toArray(String[]::new);

        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("Select the date you want to book for for [" + movie.getName() + "]",
                dateString);
        ConsoleIOManager.PrintGoBack();
    }

    public static void PrintCustomerInfo(Customer customer) {
        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintLine("Name: " + customer.getName());
        ConsoleIOManager.PrintLine("Email: " + customer.getEmail());
        ConsoleIOManager.PrintLine("Phone: " + (customer.getPhone() == 0 ? "" : customer.getPhone()));
        ConsoleIOManager.PrintGoBack();
    }

    public static void PrintCheckout(BookingTicket bookingTicket) {
        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintLine("Movie: " + bookingTicket.getSelectedMovie().getName() +
                (bookingTicket.getSelectedMovie().getMovieStatus() == MovieStatus.PREVIEW? " - Preview" : ""));
        ConsoleIOManager.PrintF("%s - %s\n", bookingTicket.getSelectedCineplex().getName(), bookingTicket.getSelectedCinema().getName());
        ConsoleIOManager.PrintF("%s - %s\n", bookingTicket.getSelectedScreening().showTime.dateOfMovie, bookingTicket.getSelectedScreening().showTime.timeOfMovie);
        ConsoleIOManager.PrintLine("Seat (Hardcoded RN): " + "F12");
        ConsoleIOManager.PrintF("$%.2f (Inclusive of GST/Service Change)\n", bookingTicket.getPrice());
        ConsoleIOManager.PrintLine("Name: " + bookingTicket.getCustomer().getName());
        ConsoleIOManager.PrintLine("Email: " + bookingTicket.getCustomer().getEmail());
        ConsoleIOManager.PrintLine("Phone: " + bookingTicket.getCustomer().getPhone());
        ConsoleIOManager.PrintConfirm();
    }
}