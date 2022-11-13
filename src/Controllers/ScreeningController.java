package Controllers;

import Models.Data.*;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;
import Views.ScreeningView;
import Views.ConsoleIOManager;
import Models.Data.Screening;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
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

        List<Movie> movieList = getMovieList().stream()
                .filter(Movie -> Movie.getMovieStatus() == MovieStatus.NOW_SHOWING ||  Movie.getMovieStatus() == MovieStatus.PREVIEW)
                .sorted(Comparator.comparing(Movie::getName))
                .toList();

        ScreeningView.printMovieList(movieList);
        Movie movie;
        movie = getSelectedMovie(movieList);

        if (movie == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        List<Cineplex> filteredCineplexList = controller.getCineplexList();
        ScreeningView.printCineplexList(filteredCineplexList);
        Cineplex selectedCineplex = getSelectedCineplex(filteredCineplexList);

        if (selectedCineplex == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //get date
        ScreeningView.printInputDate();
        LocalDate selectDate = ConsoleIOManager.readTimeMMdd();
        if (selectDate == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //get cinema
        List<Cinema> CinemasWithSelectedMovie = selectedCineplex.getCinemaList();
        ScreeningView.printCinemaOverview(CinemasWithSelectedMovie, movie, selectDate);
        Cinema selectedCinema = getSelectedCinema(CinemasWithSelectedMovie);
        List<Screening> filteredScreeningList = selectedCinema.getScreeningList().stream().filter(s -> s.getMovie().getName().equals(movie.getName()) && s.getShowTime().getDateOfMovie().isEqual(selectDate)).toList();


        //get time
        ScreeningView.printCinemaShowtimePreview(filteredScreeningList, movie, selectDate);
        screening.setMovie(movie);
        ScreeningView.printInputTime();
        LocalTime localTime = ConsoleIOManager.readTimeHHMM();
        screening.getShowTime().setTimeOfMovie(localTime);
        screening.getShowTime().setDateOfMovie(selectDate);

        selectedCinema.addToScreeningList(screening);
        ScreeningView.printAddShowTimeSuccess();
        DataStoreManager.getInstance().saveAll();

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
        CineplexController controller = new CineplexController();
        List<Movie> movieList = getMovieList().stream()
                .filter(Movie -> Movie.getMovieStatus() == MovieStatus.NOW_SHOWING ||  Movie.getMovieStatus() == MovieStatus.PREVIEW)
                .sorted(Comparator.comparing(Movie::getName))
                .toList();

        //====Get Movie
        ScreeningView.printMovieList(movieList);
        Movie movie = getSelectedMovie(movieList);
        if (movie == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }
        //====Get Cineplex
        List<Cineplex> filteredCineplexList = controller.findCineplexAndCinemaWithSelectedMovie(movie);
        ScreeningView.printCineplexList(filteredCineplexList);
        Cineplex selectedCineplex = getSelectedCineplex(filteredCineplexList);

        if (selectedCineplex == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //====Get Cinema
        List<Cinema> CinemasWithSelectedMovie = selectedCineplex.getCinemasWithMovie(movie);
        var localDates = CinemasWithSelectedMovie.stream()
                .map(model -> model.getScreeningList().stream()
                        .filter(s -> s.getMovie().getName().equals(movie.getName()))
                        .map(s -> s.getShowTime().getDateOfMovie())
                        .toList())
                .flatMap(Collection::stream)
                .sorted(LocalDate::compareTo).toArray(LocalDate[]::new);

        //====Get Date list
        ScreeningView.printDateList(localDates);
        LocalDate selectDate = getSelectedDate(localDates);
        if (selectDate == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //====Get Cinema overview
        ScreeningView.printCinemaOverview(CinemasWithSelectedMovie, movie, selectDate);
        Cinema selectedCinema = getSelectedCinema(CinemasWithSelectedMovie);

        if (selectedCinema == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //====Get Screening
        List<Screening> filteredScreeningList = selectedCinema.getScreeningList().stream().filter(s -> s.getMovie().getName().equals(movie.getName()) && s.getShowTime().getDateOfMovie().isEqual(selectDate)).toList();
        ScreeningView.printCinemaShowtimeSelectionList(filteredScreeningList, movie);
        Screening selectedScreening = getScreening(filteredScreeningList);
        if (selectedScreening == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
            return;
        }

        //====Remove Screening
        selectedCinema.removeScreeningList(selectedScreening);
        ScreeningView.printDeleteShowTimeSuccess();
        DataStoreManager.getInstance().saveAll();

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
     * Gets the user input for the specific screening.
     * @param filteredScreeningList list of screenings the user can choose from.
     * @return user selected screening
     */
    private Screening getScreening(List<Screening> filteredScreeningList) {
        Screening selectedScreening;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > filteredScreeningList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                NavigationController.getInstance().goBack(0);
            } else {
                selectedScreening = filteredScreeningList.get(input - 1);
                break;
            }
        } while (true);
        return selectedScreening;
    }

    /**
     * Gets the user selected date from a list of dates.
     * @param localDates list of dates the user can choose from.
     * @return user selected date
     */
    private LocalDate getSelectedDate(LocalDate[] localDates) {
        LocalDate selectedLocalDate;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > localDates.length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                NavigationController.getInstance().goBack(0);
            } else {
                selectedLocalDate = localDates[input - 1];
                break;
            }
        } while (true);
        return selectedLocalDate;
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
