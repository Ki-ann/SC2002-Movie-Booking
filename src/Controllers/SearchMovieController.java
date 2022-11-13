package Controllers;

import Models.Data.Enums.MovieStatus;
import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 * SearchMovieController is a Navigation that manages the logic and flow for searching of movies
 *
 * @author Shreyas Pramod Hegde, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class SearchMovieController implements INavigation {

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
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
     * Lists all movies for user to choose from in order from movie showing status
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
                ConsoleIOManager.printLine(movies[choice - 1].toString());
                gotoReviewSystem(movies[choice - 1]);
                break;
            }
        }while(true);
    }

    /**
     * Search movie by name then proceed to review system
     */
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
                gotoReviewSystem(movies[choice - 1]);
                break;
            }
        }while(true);
    }

    /**
     * Search movie by name and return the list of movies found
     * @return list of movies found.
     */
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

    /**
     * Set the selected movie and call NavigationController to load new instance of ReviewController object
     *
     * @param movie user selected movie
     * @see ReviewController
     * @see NavigationController
     */
    private void gotoReviewSystem(Movie movie) {
        ReviewController reviewController = new ReviewController();
        reviewController.setMovie(movie);
        NavigationController.getInstance().load(reviewController);
    }
}