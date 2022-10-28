package Views;

public class AdminView {

    public static void DisplayMenu() {
        ConsoleIOManager.ClearScreen();
        ConsoleIOManager.PrintMenu("This is the admin page.");
        ConsoleIOManager.PrintGoBack();
    }

}