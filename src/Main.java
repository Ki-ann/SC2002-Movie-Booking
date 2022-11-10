import Controllers.MainController;
import Controllers.NavigationController;
import Models.DataSeeder;
import Models.DataStoreManager;

class Main {

	public static void main(String[] args) {
		System.out.println("Application Starting...");
		// Initialize Data
		DataSeeder.initializeData();
		// Load DB
		DataStoreManager.getInstance().loadAll();
		// Load Initial Controller
		NavigationController.getInstance().load(new MainController());

	}
}

