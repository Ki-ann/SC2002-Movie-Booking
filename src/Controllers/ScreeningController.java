package Controllers;
import Models.Data.*;
import Models.DataStoreManager;
import Views.ScreeningView;
import Views.ConsoleIOManager;
import Models.Data.Screening;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/**
 * ScreeningController is a Navigation that manages the logic and flow for manage showtime
 *
 * @author Han Zhiguang
 * @version 1.0
 * @since 2022-11-05
 */
public class ScreeningController implements INavigation {

    private int initialMenuSelection = -1;

     /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        ScreeningView.displayMenu();
        do {
            this.initialMenuSelection = -1;
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.readInt();
            }
            switch (initialMenuSelection) {
                case 1 -> addShowtime();
                case 2 -> removeShowtime();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    }

    /**
     * The flow of adding new showtime.
     */
    private void addShowtime() {
        Screening screening = new Screening();
        CineplexController controller = new CineplexController();
        screening.setShowTime(new ShowTime());
        List<Movie> movieList = getMovieList();
        ScreeningView.printMovieList(movieList);
        Movie movie;
        movie = getSelectedMovie(movieList);
        if (movie == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        List<Cineplex> filteredCineplexList = controller.findCineplexAndCinemaWithSelectedMovie(movie);
        ScreeningView.printCineplexList(filteredCineplexList);
        Cineplex selectedCineplex = getSelectedCineplex(filteredCineplexList);

        if (selectedCineplex == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        //get date
        ScreeningView.printInputDate();
        LocalDate selectDate = ConsoleIOManager.readTimeMMdd();
        if (selectDate == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        //get cinema
        List<Cinema> CinemasWithSelectedMovie = selectedCineplex.getCinemasWithMovie(movie);
        ScreeningView.printCinemaOverview(CinemasWithSelectedMovie, movie, selectDate);
        Cinema selectedCinema = getSelectedCinema(CinemasWithSelectedMovie);
        List<Screening> filteredScreeningList = selectedCinema.getScreeningList().stream().filter(s -> s.getMovie().getName().equals(movie.getName()) && s.getShowTime().getDateOfMovie().isEqual(selectDate)).toList();
        
        //get time
        ScreeningView.printCinemaShowtime(filteredScreeningList, movie, selectDate);
        screening.setMovie(movie);
        ScreeningView.printInputTime();

        LocalTime localTime = ConsoleIOManager.readTimeHHMM();
        screening.getShowTime().setTimeOfMovie(localTime);
        screening.getShowTime().setDateOfMovie(selectDate);
        //selectedCineplex.getCinemaByIndex(0).addToScreeningList(screening);
        selectedCinema.addToScreeningList(screening);
        ScreeningView.printAddShowTimeSuccess();
        do {
            if (ConsoleIOManager.readInt() == 0) {
                this.initialMenuSelection = -1;
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * The flow of removing new showtime.
     */
    private void removeShowtime() {
        Screening screening = new Screening();
        CineplexController controller = new CineplexController();
        screening.setShowTime(new ShowTime());
        List<Movie> movieList = getMovieList();
        ScreeningView.printMovieList(movieList);
        Movie movie;
        movie = getSelectedMovie(movieList);
        if (movie == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }

        List<Cineplex> filteredCineplexList = controller.findCineplexAndCinemaWithSelectedMovie(movie);
        ScreeningView.printCineplexList(filteredCineplexList);
        Cineplex selectedCineplex = getSelectedCineplex(filteredCineplexList);

        if (selectedCineplex == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }

        ScreeningView.printInputDate();
        LocalDate selectDate = ConsoleIOManager.readTimeMMdd();
        if (selectDate == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        List<Cinema> CinemasWithSelectedMovie = selectedCineplex.getCinemasWithMovie(movie);
        ScreeningView.printCinemaOverview(CinemasWithSelectedMovie, movie, selectDate);
        Cinema selectedCinema = getSelectedCinema(CinemasWithSelectedMovie);
        List<Screening> filteredScreeningList = selectedCinema.getScreeningList().stream().filter(s -> s.getMovie().getName().equals(movie.getName()) && s.getShowTime().getDateOfMovie().isEqual(selectDate)).toList();
        ScreeningView.printCinemaShowtime(filteredScreeningList, movie, selectDate);
        screening.setMovie(movie);
        ScreeningView.printInputTime();
        LocalTime localTime = ConsoleIOManager.readTimeHHMM();
        screening.getShowTime().setTimeOfMovie(localTime);
        screening.getShowTime().setDateOfMovie(selectDate);
        selectedCinema.removeScreeningList(screening);
        ScreeningView.printDeleteShowTimeSuccess();
        do {
            if (ConsoleIOManager.readInt() == 0) {
                this.initialMenuSelection = -1;
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);

    }

    /**
     * Get movie list from database.
     */
    private ArrayList<Movie> getMovieList() {
        return DataStoreManager.getInstance().getStore(Movie.class);
    }

    /**
     * Gets the admin's desired cineplex selection input.
     *
     * @param filteredCineplexList The list of cineplex for the admin to choose from.
     * @return Admin selected cineplex.
     */
    private Cineplex getSelectedCineplex(List<Cineplex> filteredCineplexList) {
        Cineplex selectedCineplex;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > filteredCineplexList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                NavigationController.getInstance().goBack(0);
            } else {
                selectedCineplex = filteredCineplexList.get(input - 1);
                break;
            }
        } while (true);
        return selectedCineplex;
    }

    /**
     * Gets the admin's desired cinema selection input.
     *
     * @param cinemasWithSelectedMovie The list of cinemas that contain the selected movie.
     * @return Admin selected cinema.
     */
    private Cinema getSelectedCinema(List<Cinema> cinemasWithSelectedMovie) {
        Cinema selectedCinema;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > cinemasWithSelectedMovie.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                NavigationController.getInstance().goBack(0);
            } else {
                selectedCinema = cinemasWithSelectedMovie.get(input - 1);
                break;
            }
        } while (true);
        return selectedCinema;
    }

     /**
     * Gets the admin's desired movie selection input .
     *
     * @param movieList The list of movies for the admin to choose from.
     * @return Admin selected movie.
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
}
