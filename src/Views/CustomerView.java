package Views;

/**
 * CustomerView class used by CustomerController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-28
 * @see Controllers.CustomerController
 * @see Views.ConsoleIOManager
 */
public class CustomerView {

    /**
     * Prints the selection menu
     */
    public static void displayMenu() {
//		ConsoleIOManager.clearScreen();
		ConsoleIOManager.printMenu("This is the customer page.",
                "Book a ticket",
                "Search for/Review a movie",
                "List top rated movies",
                "I want to see my booking history");
        ConsoleIOManager.printGoBack();
    }

}