package Controllers;

import Views.ConsoleIOManager;
import Views.CustomerView;

public class CustomerController implements INavigation {

	public void Start() {
		CustomerView.DisplayMenu();
		boolean valid = true;
		do {
			switch (ConsoleIOManager.ReadInt()) {
				case 1 -> GotoBookingSystem();
				case 2 -> GotoSearchMoviesSystem();
				case 3 -> GotoTopMoviesSystem();
				case 4 -> GotoHistorySystem();
				case 0 -> NavigationController.getInstance().goBack();
				default -> {
					ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
					valid = false;
				}
			}
		} while(!valid);
	}

	public void GotoBookingSystem() {
		NavigationController.getInstance().Load(new BookingController());
	}

	public void GotoSearchMoviesSystem() {
		NavigationController.getInstance().Load(new SearchMovieController());
	}

	public void GotoTopMoviesSystem() {
		NavigationController.getInstance().Load(new TopMovieController());
	}

	public void GotoHistorySystem() {
		// TODO - Rethink whether to merge with BookingSystem or keep it seperate
		NavigationController.getInstance().Load(new BookingController());
	}

}