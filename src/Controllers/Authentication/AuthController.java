package Controllers.Authentication;

import Controllers.NavigationController;
import Models.Data.Setting;
import Models.DataStoreManager;
import Models.Data.Admin;
import Views.AdminView;
import Views.ConsoleIOManager;
import java.util.ArrayList;

/**
 * Authentication Controller controls the logic for account related operations, implements IAuthenticator.
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 */
public class AuthController implements IAuthenticator{
	/**
	 * Prompts the User to log in to and check whether it is a valid account.
	 * @return The valid Admin account which the user has inputted
	 */
	public Admin login() {
		ConsoleIOManager.printMenu("Login Page");
		ConsoleIOManager.printLine("Username: ");
		String Username = ConsoleIOManager.readString();
		ConsoleIOManager.printLine("Password: ");
		String password = ConsoleIOManager.readString();

		ArrayList<Admin> admins = DataStoreManager.getInstance().getStore(Admin.class);
		for (Admin admin : admins) {
			if (Username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
				AdminView.printLoginSuccess();
				Setting.getSettings().setCurrentAdmin(admin);
				return new Admin(Username, password);
			}
		}
		AdminView.printLoginFailed();
		return null;
	}

	/**
	 * Allows for creation of an account and adding it to the store
	 */
	public void createAccount(){
		ConsoleIOManager.printLine("Username: ");
		String Username	 = ConsoleIOManager.readString();
		ConsoleIOManager.printLine("Password: ");
		String password = ConsoleIOManager.readString();
		DataStoreManager.getInstance().addToStore(new Admin(Username, password));

		ConsoleIOManager.printLine("Account created");
	}

	/**
	 * Sets the current admin object instance to null
	 */
	public void logout() {
		ConsoleIOManager.printLine("Logged out!");
		Setting.getSettings().setCurrentAdmin(null);
		NavigationController.getInstance().goBack();
	}

}