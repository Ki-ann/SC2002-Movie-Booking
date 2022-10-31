package Controllers.Authentication;

import Controllers.NavigationController;
import Models.DataStoreManager;
import Models.Data.Admin;
import Views.ConsoleIOManager;

public class AuthController implements IAuthenticator {
	public Admin Login() {
		// TODO - implement Controllers.Authentication.AuthController.Login
		ConsoleIOManager.PrintLine("Username: ");
		String Username = ConsoleIOManager.ReadString();
		ConsoleIOManager.PrintLine("Password: ");
		String password = ConsoleIOManager.ReadString();

		return new Admin(Username,password);
	}
	public void createAccount(){
		ConsoleIOManager.PrintLine("Username: ");
		String Username	 = ConsoleIOManager.ReadString();
		ConsoleIOManager.PrintLine("Password: ");
		String password = ConsoleIOManager.ReadString();
		DataStoreManager.getInstance().AddToStore(new Admin(Username, password));

		ConsoleIOManager.PrintLine("Account created");
	}
	
	public void Logout() {
		// TODO - implement Controllers.Authentication.AuthController.Logout
		System.out.println("Logged out!");
		NavigationController.getInstance().goBack();
	}

}