package Controllers;

import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.SearchMovieView;

import javax.lang.model.type.UnknownTypeException;
import javax.swing.text.NavigationFilter;
import java.io.Console;
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
		String[] titles = new String[movies.length];
		for(int i=0;i<movies.length;i++){
			titles[i] = movies[i].getName();
		}
		SearchMovieView.listMovies(titles);
		int choice = ConsoleIOManager.readInt();
		if(choice!=0){
			ConsoleIOManager.printLine(movies[choice-1].toString());
			SearchMovieView.reviewOption();
			String ans = ConsoleIOManager.readString();
			if(ans.equals("Y") || ans.equals("y")){
				gotoReviewSystem();
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
			if(movie.getName().contains(NameSubstring)){
				found.add(movie);
			}
		}
		Movie[] foundMovies = new Movie[found.size()];
		foundMovies = found.toArray(foundMovies);
		return foundMovies;
	}

	public void gotoReviewSystem() {
		return;
	}
}