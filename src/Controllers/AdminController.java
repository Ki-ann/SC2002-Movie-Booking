package Controllers;

import Controllers.Authentication.IAuthenticator;
import Views.AdminView;
import Views.ConsoleIOManager;

public class AdminController implements INavigation {

	private IAuthenticator authenticator;

	public void start() {
		AdminView.DisplayMenu();
		switch(ConsoleIOManager.readInt()){
			case 1 -> NavigationController.getInstance().load(new SettingsController());
			case 0 -> NavigationController.getInstance().goBack();
		}
	}
	public void gotoMovieEditSystem() {
		NavigationController.getInstance().load(new MovieEditController());
	}

	public void gotoSettingsSystem() {
		NavigationController.getInstance().load(new SettingsController());
	}

	public void gotoCineplexEditSystem() {
		NavigationController.getInstance().load(new CineplexController());
	}

	public void logout() {
		// TODO - implement Controllers.AdminController.Logout
		throw new UnsupportedOperationException();
	}

}