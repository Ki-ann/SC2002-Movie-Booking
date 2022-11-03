package Controllers;

import Models.Data.Admin;
import Models.Data.Enums.TopMovieViewingState;
import Models.Data.Movie;
import Models.Data.Setting;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.TopMovieView;

public class TopMovieController implements INavigation {

    Setting setting = Setting.getSettings();
    Admin currentAdmin;

    @Override
    public void start() {
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

    private void editViewingState() {
    }

    private void viewByRatings() {
    }

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

    public void SetAdmin(Admin admin) {
        this.currentAdmin = admin;
    }
}
