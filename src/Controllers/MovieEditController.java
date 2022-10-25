package Controllers;

public class MovieEditController implements INavigation {

	public void Start() {
		// TODO - implement Controllers.MovieEditController.Start
		throw new UnsupportedOperationException();
	}

	public void GotoSearchMoviesSystem() {
		NavigationController.getInstance().Load(new SearchMovieController());
	}

	public void CreateMovie() {
		// TODO - implement Controllers.MovieEditController.CreateMovie
		throw new UnsupportedOperationException();
	}

	public void UpdateMovie() {
		// TODO - implement Controllers.MovieEditController.UpdateMovie
		throw new UnsupportedOperationException();
	}

}