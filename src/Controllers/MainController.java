package Controllers;

public class MainController implements INavigation {

	public void Start() {
		// TODO - implement Controllers.MainController.Start
		throw new UnsupportedOperationException();
	}

	public void GotoAdminModule() {
		NavigationController.getInstance().Load(new AdminController());
	}

	public void GotoCustomerModule() {
		NavigationController.getInstance().Load(new CustomerController());
	}

}