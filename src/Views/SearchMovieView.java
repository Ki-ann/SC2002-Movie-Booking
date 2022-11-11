package Views;

import Models.Data.Movie;

/**
 * ReviewView class used by ReviewController for printing information to console using static functions
 *
 * @author Shreyas Pramod Hegde, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 * @see Controllers.ReviewController
 * @see Views.ConsoleIOManager
 */
public class SearchMovieView {

    /**
     * Displays a selection menu
     */
    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Search for movies",
                "List all movies",
                "Search via name");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a message for movie name input
     */
    public static void searchOptions() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printLine("Enter movie name: ");
    }

    /**
     * Displays a selection for the list of movies
     * @param movieList selectable movie list
     */
    public static void listMovies(Movie[] movieList){
        String[] titles = new String[movieList.length];
        for (int i = 0; i < movieList.length; i++) {
            titles[i] = movieList[i].getName()+ " | " + movieList[i].getMovieRating().name() + " | " + movieList[i].getMovieType().name() + " | " + movieList[i].getMovieStatus();
        }

        ConsoleIOManager.printMenu("These are the movies: ",titles);
        ConsoleIOManager.printGoBack();
    }
}