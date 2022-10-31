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

	public void Start() {
		boolean login = false;
		while(login==false){
			Admin a = authenticator.Login();
			// ArrayList<Admin> admins = DataStoreManager.getInstance().GetStore(Admin.class);
			// for(int i=0;i<admins.size();i++){
			// 	if(a.getUsername()==admins.get(i).getUsername() && a.getPassword()==admins.get(i).getPassword()){
			// 		ConsoleIOManager.PrintLine("Login successful");
			// 		login = true;
			// 		break;
			// 	}
			// }
			if(a.getUsername().equals("Shreyas") && a.getPassword().equals("12345")){
				ConsoleIOManager.PrintLine("Login Successful");
				login = true;
				break;
			}
			ConsoleIOManager.PrintLine("Wrong username or password!");
		}

		AdminView.DisplayMenu();
		switch(ConsoleIOManager.ReadInt()){
			case 1->GotoMovieEditSystem();
			case 2->GotoSettingsSystem();
			case 3->GotoCineplexEditSystem();
			case 4 -> NavigationController.getInstance().goBack();
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
		authenticator.Logout();
		throw new UnsupportedOperationException();
	}

}