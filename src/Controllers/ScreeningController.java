package Controllers;

import Models.Data.*;
import Models.DataStoreManager;
import Views.ScreeningView;
import Views.ConsoleIOManager;
import Models.Data.Screening;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


public class ScreeningController implements INavigation {

    private int initialMenuSelection = -1;

    public void start() {
        ScreeningView.displayMenu();
        do {
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

    private void addShowtime() {
        // get movie
        Screening screening = new Screening();
        CineplexController controller = new CineplexController();
        screening.setShowTime(new ShowTime());
        ScreeningView.printInputMovie();
        Movie movie;
        movie = getMovie();
        if (movie == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        //get cineplex
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
        if (selectedCinema == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        //get time
        ScreeningView.printCinemaShowtime(filteredScreeningList, movie, selectDate);
        screening.setMovie(movie);
        ScreeningView.printInputTime();

        LocalTime localTime = ConsoleIOManager.readTimeHHMM();
        screening.getShowTime().setTimeOfMovie(localTime);
        screening.getShowTime().setDateOfMovie(selectDate);
        //selectedCineplex.getCinemaByIndex(0).addToScreeningList(screening);
        selectedCinema.addToScreeningList(screening);
        DataStoreManager.getInstance().addToStore(movie);
        ScreeningView.printAddShowTimeSuccess();
        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    private void removeShowtime() {
        Screening screening = new Screening();
        CineplexController controller = new CineplexController();
        screening.setShowTime(new ShowTime());
        ScreeningView.printInputMovie();
        Movie movie;
        movie = getMovie();
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
        if (selectedCinema == null) {
            this.initialMenuSelection = -1; // Go back to main menu
            NavigationController.getInstance().goBack(0);
        }
        ScreeningView.printCinemaShowtime(filteredScreeningList, movie, selectDate);
        screening.setMovie(movie);
        ScreeningView.printInputTime();
        LocalTime localTime = ConsoleIOManager.readTimeHHMM();
        screening.getShowTime().setTimeOfMovie(localTime);
        screening.getShowTime().setDateOfMovie(selectDate);
        boolean x = selectedCinema.removeScreeningList(screening);
        ConsoleIOManager.printF(String.valueOf(x));
        selectedCinema.removeScreeningList(screening);
        DataStoreManager.getInstance().addToStore(movie);
        ScreeningView.printDeleteShowTimeSuccess();
        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);

    }

    private ArrayList<Movie> getMovieList() {
        return DataStoreManager.getInstance().getStore(Movie.class);
    }

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

    private Movie getMovie() {
        Movie selectedMovie;
        try {
            String name = ConsoleIOManager.readString();
            List<Movie> movieList = getMovieList().stream().filter(Movie -> Movie.getName().equals(name)).toList();
            selectedMovie = movieList.get(0);
            return selectedMovie;
        } catch (Exception ex) {
            System.out.println("Movie is not exist. Try again.");
            return getMovie();
        }
    }
}
