package Controllers;

import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;

import java.sql.Array;
import java.util.ArrayList;

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
		for(int i=0;i<movies.length;i++){
			ConsoleIOManager.printLine("[ "+i+" ]");
			ConsoleIOManager.printLine("Movie title: "+movies[i].getName());
			ConsoleIOManager.printLine("Showing status: "+movies[i].getMovieStatus());
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
			if(movie.getName().contains(NameSubstring)){
				found.add(movie);
			}
		}
		Movie[] foundMovies = new Movie[found.size()];
		foundMovies = found.toArray(foundMovies);
		return foundMovies;
	}

	public void gotoReviewSystem() {
		// TODO - implement Controllers.SearchMovieController.GotoReviewSystem
		throw new UnsupportedOperationException();
	}

}