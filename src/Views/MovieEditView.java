package Views;

public class MovieEditView {

    public static void DisplayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the Movie Editing Page", "Create a Movie", "Update existing Movies",
                "Delete existing Movies",
                "Go Back");
        // ConsoleIOManager.PrintGoBack();
    }
}