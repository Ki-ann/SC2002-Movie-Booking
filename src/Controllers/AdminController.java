package Controllers;

import java.util.ArrayList;
import Controllers.Authentication.AuthController;
import Controllers.Authentication.IAuthenticator;
import Models.DataStoreManager;
import Models.Data.Admin;
import Views.AdminView;
import Views.ConsoleIOManager;

public class AdminController implements INavigation {

	private IAuthenticator authenticator = new AuthController();

	public void start() {
		boolean login = false;
		while(!login){
			Admin a = authenticator.login();
			// ArrayList<Admin> admins = DataStoreManager.getInstance().GetStore(Admin.class);
			// for(int i=0;i<admins.size();i++){
			// 	if(a.getUsername()==admins.get(i).getUsername() && a.getPassword()==admins.get(i).getPassword()){
			// 		ConsoleIOManager.PrintLine("Login successful");
			// 		login = true;
			// 		break;
			// 	}
			// }
			if(a!=null) {
					ConsoleIOManager.printLine("Login Successful");
					login = true;
					break;
			}
			ConsoleIOManager.printLine("Wrong username or password!");
		}

		AdminView.DisplayMenu();
		switch(ConsoleIOManager.readInt()){
			case 1->GotoMovieEditSystem();
			case 2->GotoSettingsSystem();
			case 3->GotoCineplexEditSystem();
			case 4 -> NavigationController.getInstance().goBack();
		}
	}

	public void GotoMovieEditSystem() {
		NavigationController.getInstance().load(new MovieEditController());
	}

	public void GotoSettingsSystem() {
		NavigationController.getInstance().load(new SettingsController());
	}

	public void GotoCineplexEditSystem() {
		NavigationController.getInstance().load(new CineplexController());
	}

	public void logout() {
		// TODO - implement Controllers.AdminController.Logout
		authenticator.logout();
		throw new UnsupportedOperationException();
	}

}