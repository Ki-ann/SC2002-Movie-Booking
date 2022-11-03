package Controllers;

import Views.ConsoleIOManager;
import Views.MainView;

/**
 * Main Controller class that handles entry point navigation logic, for users to choose between Admin Navigation and Customer Navigation
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-28
 */
public class MainController implements INavigation {

	/**
	 * Start method implementation for initialization after loading with NavigationController
	 *
	 * @see NavigationController
	 * @see INavigation
	 */
	public void start() {
		MainView.displayMenu();
		boolean valid = true;
		do {
			switch (ConsoleIOManager.readInt()) {
				case 1 -> gotoAdminModule();
				case 2 -> gotoCustomerModule();
				default -> {
					ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
					valid = false;
				}
			}
		} while(!valid);
	}

	/**
	 * Call NavigationController to load new instance of AdminController object
	 *
	 * @see AdminController
	 * @see NavigationController
	 */
	public void gotoAdminModule() {
		NavigationController.getInstance().load(new LoginController());
	}

	/**
	 * Call NavigationController to load new instance of CustomerController object
	 *
	 * @see CustomerController
	 * @see NavigationController
	 */
	public void gotoCustomerModule() {
		NavigationController.getInstance().load(new CustomerController());
	}

}