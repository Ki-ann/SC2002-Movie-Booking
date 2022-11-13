package Views;


/**
 * AdminView class used by AdminController for printing information to console using static functions
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 * @see Controllers.AdminController
 * @see Views.ConsoleIOManager
 */
public class AdminView {
    /**
     * Print Menu to display different functionalities of Admin
     */
    public static void DisplayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the admin page",
                "Edit Movies",
                "Edit Screenings",
                "Edit Admin Settings",
                "View Top Movies (Admin)");
        ConsoleIOManager.printF("[%d] %s\n", 0, "Logout");
    }
    /**
     * Print a message for login failure
     */
    public static void printLoginFailed(){
        ConsoleIOManager.printLine("Wrong username or password!");
    }
    /**
     * Print a message for successful loin
     */
    public static void printLoginSuccess(){
        ConsoleIOManager.printLine("Login successful");
    }

}