package Controllers;

import Views.ConsoleIOManager;
import Views.CustomerView;

/**
 * Customer Controller class that handles navigation logic, for Customers to pick their next function.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-29
 */
public class CustomerController implements INavigation {

    /**
     * Start method implementation for initialization after loading with NavigationController
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        CustomerView.displayMenu();
        boolean valid = true;
        do {
            switch (ConsoleIOManager.readInt()) {
                case 1 -> gotoBookingSystem();
                case 2 -> gotoSearchMoviesSystem();
                case 3 -> gotoTopMoviesSystem();
                case 4 -> gotoHistorySystem();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    valid = false;
                }
            }
        } while (!valid);
    }

    /**
     * Call NavigationController to load new instance of BookingController object
     *
     * @see BookingController
     * @see NavigationController
     */
    public void gotoBookingSystem() {
        NavigationController.getInstance().load(new BookingController());
    }

    /**
     * Call NavigationController to load new instance of SearchMovieController object
     *
     * @see SearchMovieController
     * @see NavigationController
     */
    public void gotoSearchMoviesSystem() {
        NavigationController.getInstance().load(new SearchMovieController());
    }

    /**
     * Call NavigationController to load new instance of TopMovieController object
     *
     * @see TopMovieController
     * @see NavigationController
     */
    public void gotoTopMoviesSystem() {
        NavigationController.getInstance().load(new TopMovieController());
    }

    /**
     * Call NavigationController to load new instance of BookingHistoryController object
     *
     * @see BookingHistoryController
     * @see NavigationController
     */
    public void gotoHistorySystem() {
        NavigationController.getInstance().load(new BookingHistoryController());
    }

}