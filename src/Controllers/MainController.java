package Controllers;

import Views.ConsoleIOManager;
import Views.MainView;

public class MainController implements INavigation {

	public void Start() {
		MainView.DisplayMenu();
		switch(ConsoleIOManager.ReadInt()){
			case 1 -> GotoAdminModule();
			case 2 -> GotoCustomerModule();
			case 0 -> NavigationController.getInstance().goBack();
		}

	}

	public void GotoAdminModule() {
		NavigationController.getInstance().Load(new AdminController());
	}

	public void GotoCustomerModule() {
		NavigationController.getInstance().Load(new CustomerController());
	}

}