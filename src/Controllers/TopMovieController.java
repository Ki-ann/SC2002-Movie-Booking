package Controllers;

import Models.Data.Admin;
import Models.Data.Enums.AgeClass;
import Models.Data.Enums.TopMovieViewingState;
import Models.Data.Movie;
import Models.Data.Setting;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.TopMovieView;

/**
 * TopMovieController that handles the logic for displaying top 5 movie ranking lists.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class TopMovieController implements INavigation {

    private final Setting setting = Setting.getSettings();
    private Admin currentAdmin = setting.getCurrentAdmin();
    private boolean editMode = false;

    /**
     * Start method implementation for initialization after loading with NavigationController.
     * Chooses which option to display based on Setting configuration and if user is an Admin
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        if (editMode) {
            editViewingState();
            return;
        }

        TopMovieViewingState currentViewingState = setting.getCurrentTopMovieViewingState();
        TopMovieView.displayMenu(currentViewingState, currentAdmin);

        boolean valid = true;
        do {

            switch (ConsoleIOManager.readInt()) {
                case 0:
                    NavigationController.getInstance().goBack();
                    break;
                case 1:
                    if (currentViewingState == TopMovieViewingState.BY_BOTH || currentViewingState == TopMovieViewingState.BY_SALES) {
                        viewBySales();
                        break;
                    } else if (currentViewingState == TopMovieViewingState.BY_RATINGS) {
                        viewByRatings();
                        break;
                    }
                    break;
                case 2:
                    // Fall through to default case if not available
                    if (currentViewingState == TopMovieViewingState.BY_BOTH || currentAdmin != null) {
                        viewByRatings();
                        break;
                    }
                case 3:
                    if (currentAdmin != null) {
                        editViewingState();
                        break;
                    }
                default:
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    valid = false;
                    break;
            }
        } while (!valid);
    }

    public void adminSetEditMode() {
        this.editMode = true;
    }

    /**
     * Allows for admin to configure which listing methods to be displayed for the customers
     */
    private void editViewingState() {
        if (currentAdmin == null) {
            TopMovieView.printUnauthorized();
            return;
        }
        TopMovieView.printEditStates();
        TopMovieViewingState selectedState = getSelectedViewingState();
        if (selectedState == null) {
            NavigationController.getInstance().goBack();
            return;
        }

        Setting.getSettings().setCurrentTopMovieViewingState(selectedState);
        TopMovieView.printEditSuccess();

        if (ConsoleIOManager.readInt() == 0) {
            NavigationController.getInstance().goBack();
        }

    }

    private TopMovieViewingState getSelectedViewingState() {
        TopMovieViewingState selectedState;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > TopMovieViewingState.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                selectedState = TopMovieViewingState.values()[input - 1];
                break;
            }
        } while (true);
        return selectedState;
    }

    /**
     * Lists top 5 movies by review ratings
     */
    private void viewByRatings() {
        TopMovieView.printTopReviews(DataStoreManager.getInstance().getStore(Movie.class));

        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * Lists top 5 movies by ticket sales
     */
    private void viewBySales() {
        TopMovieView.printTopSales(DataStoreManager.getInstance().getStore(Movie.class));

        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * Sets the currently logged in admin for the session
     *
     * @param admin current admin user
     */
    public void SetAdmin(Admin admin) {
        this.currentAdmin = admin;
    }
}
