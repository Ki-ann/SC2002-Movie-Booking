import Controllers.*;

class Main {

	public static void main(String args[]) {
		System.out.println("Application Starting...");
		// Load DB

		// Load Initial Controller
		NavigationController.getInstance().Load(new MainController());

	}
}

