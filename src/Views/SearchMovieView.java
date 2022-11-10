package Views;

import Models.Data.Movie;

public class SearchMovieView {
    public static void searchOptions() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printLine("Enter movie name: ");
    }
    public static void listMovies(Movie[] movieList){
        String[] titles = new String[movieList.length];
        for (int i = 0; i < movieList.length; i++) {
            titles[i] = movieList[i].getName()+ " | " + movieList[i].getMovieRating().name() + " | " + movieList[i].getMovieType().name() + " | " + movieList[i].getMovieStatus();
        }

        ConsoleIOManager.printMenu("These are the movies: ",titles);
        ConsoleIOManager.printGoBack();
    }

    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Search for movies",
                "List all movies",
                "Search via name");
        ConsoleIOManager.printGoBack();
    }
}