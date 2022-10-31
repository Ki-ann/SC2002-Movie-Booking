package Controllers;

import Views.ConsoleIOManager;
import Views.MainView;

public class MainController implements INavigation {

	public void Start() {
		MainView.DisplayMenu();
		boolean valid = true;
		do {
			switch (ConsoleIOManager.ReadInt()) {
				case 1 -> GotoAdminModule();
				case 2 -> GotoCustomerModule();
				default -> {
					ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
					valid = false;
				}
			}
		} while(!valid);
	}

	public void GotoAdminModule() {
		NavigationController.getInstance().Load(new AdminController());
	}

	public void GotoCustomerModule() {
		NavigationController.getInstance().Load(new CustomerController());
	}

}