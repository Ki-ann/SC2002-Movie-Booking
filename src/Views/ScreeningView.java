package Views;
import Models.Data.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * ScreeningView class used by ScreeningController for printing information to console using static functions
 *
 * @author Han Zhiguang
 * @version 1.0
 * @since 2022-11-05
 * @see Controllers.ScreeningController
 * @see Views.ConsoleIOManager
 */
public class ScreeningView {

     /**
     * Prints a menu for showtime management.
     */
    public static void displayMenu() {
//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the ShowTime managing page.",
                "Add a showtime",
                            "Remove a showtime");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a date enter message.
     */
    public static void printInputDate(){
        ConsoleIOManager.printLine("Enter the date(Format: MM/DD)");
    }

    /**
     * Prints a time enter message.
     */
    public static void printInputTime(){
        ConsoleIOManager.printLine("Enter the time(Format: HH:mm)");
    }

    /**
     * Prints a showtime added message.
     */
    public static void printAddShowTimeSuccess(){
        ConsoleIOManager.printLine("Successfully added the show time.");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a showtime deleted message.
     */
    public static void printDeleteShowTimeSuccess(){
        ConsoleIOManager.printLine("Successfully deleted the show time.");
        ConsoleIOManager.printGoBack();
    }

    /**
     * prints a list cineplex for selection
     * @param filteredCineplexList filtered cineplex list that contain a specific movie
     */
    public static void printCineplexList(List<Cineplex> filteredCineplexList) {
        String[] cineplexStringList = filteredCineplexList.stream()
                .map(Cineplex::getName)
                .toArray(String[]::new);

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("The movie is currently in the following Cineplex",
                cineplexStringList);
        ConsoleIOManager.printGoBack();
    }

    /**
     * prints a selection menu of aviable showtime and cinema regarding selected movie and date.
     * @param filteredCinemaList  List of cinemas that contain selectedMovie.
     * @param selectedMovie the currently selected movie by the admin.
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

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the current show times for [" + selectedMovie.getName() + "]",
                showTimeString);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu for the admin to choose the specific showtime.
     * @param screeningList the list of screenings in the cinema for the specific date chosen.
     * @param selectedMovie the movie to filter for screeningList.
     * @param selectedDate the date to filter for screeningList.
     */
    public static void printCinemaShowtimePreview(List<Screening> screeningList, Movie selectedMovie, LocalDate selectedDate) {
        String[] showTimeString = screeningList.stream()
                .map(s -> s.getShowTime().getTimeOfMovie() +
                        " - " +
                        s.getShowTime().getTimeOfMovie().plus(s.getMovie().getDuration()).toString())
                .toArray(String[]::new);

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the current show times for [" + selectedMovie.getName() + "]");

        if(showTimeString.length> 0) {
            ConsoleIOManager.printF(Arrays.toString(showTimeString) + "\n");
        }else{
            ConsoleIOManager.printLine("No current show times");
        }
    }

    /**
     * Prints a list of showtime screenings for the selected movie
     * @param screeningList list of screenings
     * @param selectedMovie selected movie to display screenings for
     */
    public static void printCinemaShowtimeSelectionList(List<Screening> screeningList, Movie selectedMovie) {
        String[] showTimeString = screeningList.stream()
                .map(s -> s.getShowTime().getTimeOfMovie() +
                        " - " +
                        s.getShowTime().getTimeOfMovie().plus(s.getMovie().getDuration()).toString())
                .toArray(String[]::new);

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("These are the current show times for [" + selectedMovie.getName() + "]",
                showTimeString);

        if(showTimeString.length<= 0) {
            ConsoleIOManager.printLine("No current show times");
        }
        ConsoleIOManager.printGoBack();
    }

    /**
     * prints a list for movie selection
     * @param movieList a list of movies
     */
    public static void printMovieList(List<Movie> movieList) {
        String[] movieStringList = movieList.stream()
                .map(Movie::getName)
                .toArray(String[]::new);

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Here are the movies to modify",
                movieStringList);
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a list of dates for user selection
     * @param localDates list of selectable dates
     */
    public static void printDateList(LocalDate[] localDates) {
        String[] dateString = Arrays.stream(localDates).map(date -> date.format(DateTimeFormatter.ofPattern("dd/MM"))).toArray(String[]::new);

//        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Here are the movies to modify",
                dateString);
        ConsoleIOManager.printGoBack();
    }
}
