package Views;

public class LoginView {
    /**
     * Print menu for Login
     */
    public static void DisplayLoginMenu(){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the login page","Login");
        ConsoleIOManager.printGoBack();
    }
}
