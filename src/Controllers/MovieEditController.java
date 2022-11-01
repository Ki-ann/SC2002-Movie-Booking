package Controllers;

public class MovieEditController implements INavigation {

	public void start() {
		// TODO - implement Controllers.MovieEditController.Start
		throw new UnsupportedOperationException();
	}

	public void gotoSearchMoviesSystem() {
		NavigationController.getInstance().load(new SearchMovieController());
	}

	public void createMovie() {
		// TODO - implement Controllers.MovieEditController.CreateMovie
		throw new UnsupportedOperationException();
	}

	public void updateMovie() {
		// TODO - implement Controllers.MovieEditController.UpdateMovie
		throw new UnsupportedOperationException();
	}

}