package Views;

public class AdminView {

    public static void DisplayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the admin page.");
        ConsoleIOManager.printGoBack();
    }

}