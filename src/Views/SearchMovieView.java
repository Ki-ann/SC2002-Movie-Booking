package Views;

public class SearchMovieView {
    public static String searchOptions() {
        ConsoleIOManager.printLine("Enter movie name: ");
        String name = ConsoleIOManager.readString();
        return name;
    }
    public static void listMovies(String[] titles){
        ConsoleIOManager.printMenu("These are the movies: ",titles);
    }
    public static void reviewOption(){
        ConsoleIOManager.printLine("[Y/N] Would you like to leave a review?");
    }
}