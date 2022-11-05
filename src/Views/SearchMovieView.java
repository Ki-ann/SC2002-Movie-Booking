package Views;

public class SearchMovieView {
    public static String searchOptions() {
        ConsoleIOManager.printLine("Enter movie name: ");
        String name = ConsoleIOManager.readString();
        return name;
    }
}