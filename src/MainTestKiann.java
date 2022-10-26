import Controllers.*;
import Models.Data.Admin;
import Models.Data.Cineplex;
import Models.DataStoreManager;
import java.io.Serializable;
import java.util.ArrayList;

class MainTestKiann {

	public static void main(String[] args) {
		//Test file
		System.out.println("Application Starting...");
		// Load DB
		Admin obj = new Admin();
        DataStoreManager.getInstance().AddToStore(obj);
		DataStoreManager.getInstance().AddToStore(obj);
		DataStoreManager.getInstance().AddToStore(new Cineplex());

		DataStoreManager.getInstance().LoadAll();
		ArrayList<Serializable> adminList = DataStoreManager.getInstance().GetStore(Admin.class);
		DataStoreManager.getInstance().SaveAll();
		// Load Initial Controller
		NavigationController.getInstance().Load(new MainController());

	}
}

