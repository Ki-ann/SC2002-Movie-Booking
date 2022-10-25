package Controllers;

public class CustomerController implements INavigation {

	public void Start() {
		// TODO - implement Controllers.CustomerController.Start
		throw new UnsupportedOperationException();
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