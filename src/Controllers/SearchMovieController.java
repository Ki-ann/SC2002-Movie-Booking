package Controllers;

import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchMovieController implements INavigation {

	public void start() {
		Movie[] movies;
		while(true){
			String name = SearchMovieView.searchOptions();
			movies = searchMoviebyName(name);
			if(movies.length==0){
				ConsoleIOManager.printLine("Sorry movie not found! Please type the correct movie name");
			}else{
				break;
			}
		}
		String[] titles = new String[movies.length];
		for(int i=0;i<movies.length;i++){
			titles[i] = movies[i].getName();
		}
		SearchMovieView.listMovies(titles);
		int choice = ConsoleIOManager.readInt();
		if(choice!=0){
			ConsoleIOManager.printMenu(movies[choice-1].toString());
			SearchMovieView.reviewOption();
			String ans = ConsoleIOManager.readString();
			if(ans.equals("Y") || ans.equals("y")){
				gotoReviewSystem(movies[choice-1]);
			}
			else{
				NavigationController.getInstance().goBack();
			}
		}
	}

	/**
	 *
	 * @param NameSubstring
	 */
	public Movie[] searchMoviebyName(String NameSubstring) {
		ArrayList<Movie> movies	= DataStoreManager.getInstance().getStore(Movie.class);
		ArrayList<Movie> found = new ArrayList<Movie>();
		for(Movie movie:movies){
			if(movie.getName().toUpperCase(Locale.ROOT).contains(NameSubstring.toUpperCase(Locale.ROOT))){
				found.add(movie);
			}
		}
		Movie[] foundMovies = new Movie[found.size()];
		foundMovies = found.toArray(foundMovies);
		return foundMovies;
	}

	public void gotoReviewSystem(Movie movie) {
		ReviewController reviewController = new ReviewController();
		reviewController.setMovie(movie);
		NavigationController.getInstance().load(reviewController);
	}
}