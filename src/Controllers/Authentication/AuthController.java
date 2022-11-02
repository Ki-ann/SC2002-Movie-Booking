package Controllers.Authentication;

import Controllers.NavigationController;
import Models.DataStoreManager;
import Models.Data.Admin;
import Views.ConsoleIOManager;

import java.util.ArrayList;

public class AuthController implements IAuthenticator {
	public Admin login() {
		// TODO - implement Controllers.Authentication.AuthController.Login
		ConsoleIOManager.printLine("Username: ");
		String Username = ConsoleIOManager.readString();
		ConsoleIOManager.printLine("Password: ");
		String password = ConsoleIOManager.readString();

		ArrayList<Admin> admins = DataStoreManager.getInstance().getStore(Admin.class);
		for (Admin admin : admins) {
			if (Username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
				return new Admin(Username, password);
			}
		}
		return null;
	}
	public void createAccount(){
		ConsoleIOManager.printLine("Username: ");
		String Username	 = ConsoleIOManager.readString();
		ConsoleIOManager.printLine("Password: ");
		String password = ConsoleIOManager.readString();
		DataStoreManager.getInstance().addToStore(new Admin(Username, password));

		ConsoleIOManager.printLine("Account created");
	}
	
	public void logout() {
		// TODO - implement Controllers.Authentication.AuthController.Logout
		System.out.println("Logged out!");
		NavigationController.getInstance().goBack();
	}

}