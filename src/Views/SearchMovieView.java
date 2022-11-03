package Views;

public class SearchMovieView {
    /**
     * Print Menu to display functionalities of Search Movie
     */
    public static String searchOptions(){
        ConsoleIOManager.printLine("Enter movie name: ");
        String name = ConsoleIOManager.readString();
        return name;
    }
}