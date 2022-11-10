package Controllers;

import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchMovieController implements INavigation {

    public void start() {
        SearchMovieView.displayMenu();
        int choice = ConsoleIOManager.readInt();

        switch (choice) {
            case 1 -> listAllMovie();
            case 2 -> searchAndReview();
            case 0 -> NavigationController.getInstance().goBack();
            default -> ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
        }

    }

    /**
     *
     */
    public void listAllMovie() {
        ArrayList<Movie> moviesList = DataStoreManager.getInstance().getStore(Movie.class);
        Movie[] movies = moviesList.stream().sorted(Comparator.comparing(m -> m.getMovieStatus().name())).toArray(Movie[]::new);

        SearchMovieView.listMovies(movies);

        do {
            int choice = ConsoleIOManager.readInt();

            if (choice < 0 || choice > movies.length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }else if(choice == 0) {
                NavigationController.getInstance().goBack(0);
                break;
            }else{
                ConsoleIOManager.printMenu(movies[choice - 1].toString());
                gotoReviewSystem(movies[choice - 1]);
                break;
            }
        }while(true);
    }

    public void searchAndReview() {
        Movie[] movies = searchMovieByName();

        SearchMovieView.listMovies(movies);

        do {
            int choice = ConsoleIOManager.readInt();

            if (choice < 0 || choice > movies.length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }else if(choice == 0) {
                NavigationController.getInstance().goBack(0);
                break;
            }else{
                ConsoleIOManager.printMenu(movies[choice - 1].toString());
                gotoReviewSystem(movies[choice - 1]);
                break;
            }
        }while(true);
    }

    public Movie[] searchMovieByName() {
        ArrayList<Movie> foundMovies = new ArrayList<>();
        while (true) {
            SearchMovieView.searchOptions();
            String name = ConsoleIOManager.readString();

            ArrayList<Movie> movies = DataStoreManager.getInstance().getStore(Movie.class);
            for (Movie movie : movies) {
                if (movie.getName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT))) {
                    foundMovies.add(movie);
                }
            }

            if (foundMovies.size() == 0) {
                ConsoleIOManager.printLine("Sorry movie not found! Please type the correct movie name");
            } else {
                break;
            }
        }
        return foundMovies.toArray(Movie[]::new);
    }

    public void gotoReviewSystem(Movie movie) {
        ReviewController reviewController = new ReviewController();
        reviewController.setMovie(movie);
        NavigationController.getInstance().load(reviewController);
    }
}