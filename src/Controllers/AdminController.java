package Controllers;

import Controllers.Authentication.AuthController;
import Views.AdminView;
import Views.ConsoleIOManager;

/**
 * AdminController is a Navigation that manages the logic and flow for Admin operations
 *
 * @author Shreyas Pramod Hegde, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class AdminController implements INavigation {
    /**
     * Start method implementation for initialization after loading with NavigationController
     *
     * @see NavigationController
     * @see INavigation
     */
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

    /**
     * Call NavigationController to load new instance of MovieEditController object
     *
     * @see MovieEditController
     * @see NavigationController
     */
    public void gotoMovieEditSystem() {
        NavigationController.getInstance().load(new MovieEditController());
    }

    /**
     * Call NavigationController to load new instance of SettingsController object
     *
     * @see SettingsController
     * @see NavigationController
     */
    public void gotoSettingsSystem() {
        NavigationController.getInstance().load(new SettingsController());
    }

    /**
     * Call NavigationController to load new instance of ScreeningController object
     *
     * @see ScreeningController
     * @see NavigationController
     */
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

    /**
     * Call to IAuthenticator to logout the current Admin User
     */
    public void logout() {
        new AuthController().logout();
    }

}