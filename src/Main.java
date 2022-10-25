import Controllers.*;

class Main {

	public static void main(String args[]) {
		// TODO - implement Main.main
		System.out.println("Application Starting...");
		// Load DB

		// Load Initial Controller
		NavigationController.getInstance().Load(new MainController());

	}
}

