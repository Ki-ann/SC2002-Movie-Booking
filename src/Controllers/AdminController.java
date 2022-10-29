package Controllers;

import Controllers.Authentication.IAuthenticator;
import Views.AdminView;
import Views.ConsoleIOManager;

public class AdminController implements INavigation {

	private IAuthenticator authenticator;

	public void Start() {
		AdminView.DisplayMenu();
		switch(ConsoleIOManager.ReadInt()){
			case 1 -> GotoSettingsSystem();
			case 0 -> NavigationController.getInstance().goBack();
		}
	}

	public void GotoMovieEditSystem() {
		NavigationController.getInstance().Load(new MovieEditController());
	}

	public void GotoSettingsSystem() {
		NavigationController.getInstance().Load(new SettingsController());
	}

	public void GotoCineplexEditSystem() {
		NavigationController.getInstance().Load(new CineplexController());
	}

	public void Logout() {
		// TODO - implement Controllers.AdminController.Logout
		throw new UnsupportedOperationException();
	}

}