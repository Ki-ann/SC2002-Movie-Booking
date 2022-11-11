package Views;

/**
 * LoginView class used by LoginController for printing information to console using static functions
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 * @see Controllers.LoginController
 * @see Views.ConsoleIOManager
 */
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
