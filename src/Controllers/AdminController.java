package Controllers;

import Controllers.Authentication.AuthController;
import Views.AdminView;
import Views.ConsoleIOManager;

public class AdminController implements INavigation {
    public void start() {
        AdminView.DisplayMenu();
        switch (ConsoleIOManager.readInt()) {
            case 1 -> gotoMovieEditSystem();
            case 2 -> gotoScreeningEditSystem();
            case 3 -> gotoSettingsSystem();
            case 4 -> gotoTopMoviesSystem();
            case 0 -> logout();
        }
    }

    public void gotoMovieEditSystem() {
        NavigationController.getInstance().load(new MovieEditController());
    }

    public void gotoSettingsSystem() {
        NavigationController.getInstance().load(new SettingsController());
    }

    public void gotoScreeningEditSystem() {
        NavigationController.getInstance().load(new ScreeningController());
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

    public void logout() {
        new AuthController().logout();
    }

}