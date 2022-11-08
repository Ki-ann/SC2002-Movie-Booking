package Controllers;

import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchMovieController implements INavigation {

    public void start() {
        Movie[] movies = searchMovieByName();

        String[] titles = new String[movies.length];
        for (int i = 0; i < movies.length; i++) {
            titles[i] = movies[i].getName();
        }
        SearchMovieView.listMovies(titles);
        int choice = ConsoleIOManager.readInt();
        if (choice != 0) {
            ConsoleIOManager.printMenu(movies[choice - 1].toString());
            gotoReviewSystem(movies[choice - 1]);

        }
    }

    /**
     *
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

    public void gotoReviewSystem(Movie movie) {
        ReviewController reviewController = new ReviewController();
        reviewController.setMovie(movie);
        NavigationController.getInstance().load(reviewController);
    }
}