package Views;

public class SearchMovieView {
    public static void searchOptions() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printLine("Enter movie name: ");
    }
    public static void listMovies(String[] titles){
        ConsoleIOManager.printMenu("These are the movies: ",titles);
    }

}