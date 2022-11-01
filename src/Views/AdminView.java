package Views;

public class AdminView {

    public static void DisplayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the admin page","Edit Movies","Edit Settings","Edit Cineplexes","Logout");
        // ConsoleIOManager.PrintGoBack();
    }

}