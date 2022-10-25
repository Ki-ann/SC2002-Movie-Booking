package Controllers;

import Controllers.Authentication.IAuthenticator;

public class AdminController implements INavigation {

	private IAuthenticator authenticator;

	public void Start() {
		// TODO - implement Controllers.AdminController.Start
		throw new UnsupportedOperationException();
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