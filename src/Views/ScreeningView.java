package Views;
import Models.Data.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ScreeningView {
    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the ShowTime managing page.",
                "Add a showtime",
                            "Remove a showtime");
        ConsoleIOManager.printGoBack();
    }

    public static void printInputDate(){
        ConsoleIOManager.printLine("Enter the date(Format: MM/DD)");
    }
    public static void printInputTime(){
        ConsoleIOManager.printLine("Enter the time(Format: HH:mm)");
    }
    public static void printAddShowTimeSuccess(){
        ConsoleIOManager.printLine("Successfully added the show time.");
        ConsoleIOManager.printGoBack();
    }
    public static void printDeleteShowTimeSuccess(){
        ConsoleIOManager.printLine("Successfully deleted the show time.");
        ConsoleIOManager.printGoBack();
    }

    public static void printDate(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
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

    public static void printTime(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
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

    public static void printCineplexList(List<Cineplex> filteredCineplexList) {
        String[] cineplexStringList = filteredCineplexList.stream()
                .map(Cineplex::getName)
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("The movie is currently in the following Cineplex",
                cineplexStringList);
        ConsoleIOManager.printGoBack();
    }
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
        ConsoleIOManager.printMenu("These are the current show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.printGoBack();
    }
    public static void printCinemaShowtime(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = screeningList.stream()
                .map(s -> s.getShowTime().getTimeOfMovie() +
                        " - " +
                        s.getShowTime().getTimeOfMovie().plus(s.getMovie().getDuration()).toString())
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the available show times for [" + selectedMovie.getName() + "]");

        ConsoleIOManager.printF(Arrays.toString(showTimeString)+"\n");
        ConsoleIOManager.printGoBack();
    }
    public static void printMovieList(List<Movie> movieList) {
        String[] movieStringList = movieList.stream()
                .map(Movie::getName)
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Here are the movies to modify",
                movieStringList);
        ConsoleIOManager.printGoBack();
    }

}
