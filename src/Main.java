import Controllers.MainController;
import Controllers.NavigationController;
import Models.Data.Setting;
import Models.DataSeeder;
import Models.DataStoreManager;

class Main {

	public static void main(String[] args) {
		System.out.println("Application Starting...");
		// Initialize Data
		DataSeeder.initializeData();
		// Load DB
		DataStoreManager.getInstance().loadAll();
		// Delete old admin user
		Setting.getSettings().setCurrentAdmin(null);
		// Load Initial Controller
		NavigationController.getInstance().load(new MainController());
	}
}

