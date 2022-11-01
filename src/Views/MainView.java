package Views;

/**
 * MainView class used by MainController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-28
 * @see Controllers.MainController
 * @see Views.ConsoleIOManager
 */
public class MainView {

    /**
     * Prints the selection menu
     */
    public static void displayMenu(){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the Main page.",
                "Admin Module",
                "Customer Module");
    }
}
