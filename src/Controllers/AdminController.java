package Controllers;

import java.util.ArrayList;
import Controllers.Authentication.AuthController;
import Controllers.Authentication.IAuthenticator;
import Models.DataStoreManager;
import Models.Data.Admin;
import Views.AdminView;
import Views.ConsoleIOManager;
import Views.LoginView;

public class AdminController implements INavigation {
	public void start() {
		AdminView.DisplayMenu();
		switch(ConsoleIOManager.readInt()){
			case 1->gotoMovieEditSystem();
			case 2->gotoSettingsSystem();
			case 3->gotoCineplexEditSystem();
			case 4 -> logout();
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
		new AuthController().logout();
		throw new UnsupportedOperationException();
	}

}