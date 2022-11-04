package Controllers;

import Controllers.INavigation;
import Controllers.NavigationController;
import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieType;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;
import Models.Data.Movie;
import Views.ConsoleIOManager;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieEditController implements INavigation {

	public void start() {
		// TODO - implement Controllers.MovieEditController.Start
	}

	public void createMovie() {
		Movie movie = new Movie();

		//PROMPTS
		ConsoleIOManager.printMenu("Movie Details");

		//Name
		ConsoleIOManager.printLine("Please input the Name of the movie: ");
		String name = ConsoleIOManager.readString();
		movie.setName(name);

//		//movieStatus ENUM (In Progress)
//		ConsoleIOManager.printLine("Please input the movie status: \n1: Coming Soon\n2: Preview\n3: Now Showing\n");
//		MovieStatus choice;
//		movie.setMovieStatus(choice);
//
//		Movie.setMovieStatus(MovieStatus.NOW_SHOWING);
		//Duration
		ConsoleIOManager.printLine("Please input the Duration of Movie (in minutes): ");
		int minutes = ConsoleIOManager.readInt();
		movie.setDuration(0,minutes,0);
		//SynopsisD
		ConsoleIOManager.printLine("Please input the Synopsis: ");
		String synopsis = ConsoleIOManager.readString();
		movie.setSynopsis(synopsis);
		//LanguageD
		ConsoleIOManager.printLine("Please input the Language of Movie:");
		String language = ConsoleIOManager.readString();
		movie.setLanguage(language);

		//Cast
		ConsoleIOManager.printLine("Please input the number of Casts: ");
		int numCasts = ConsoleIOManager.readInt();
		ArrayList <String> cast = new ArrayList<>();
		for (int i = 0; i < numCasts; i++){
			ConsoleIOManager.printLine("Please input the Cast(s): ");
			String castMember = ConsoleIOManager.readString();
			cast.add(castMember);
			movie.setCast(cast);
		}

		//Genre
		ConsoleIOManager.printLine("Please input the number of Genre(s): ");
		int numGenre = ConsoleIOManager.readInt();
		ArrayList <String> genre = new ArrayList<>();
		for (int i = 0; i < numGenre; i++){
			ConsoleIOManager.printLine("Please input the Cast(s): ");
			String genreType = ConsoleIOManager.readString();
			cast.add(genreType);
			movie.setCast(genre);
		}
		//Director
		ConsoleIOManager.printLine("Please input the number of Casts: ");
		int numDirector = ConsoleIOManager.readInt();
		ArrayList <String> director = new ArrayList<>();
		for (int i = 0; i < numDirector; i++){
			ConsoleIOManager.printLine("Please input the Cast(s): ");
			String directorMember = ConsoleIOManager.readString();
			cast.add(directorMember);
			movie.setCast(director);
		}
		//MovieTypeENUM
//		ConsoleIOManager.printLine("Movie Type: ");
//		String movieType = ConsoleIOManager.readString();
//		movie.setMovieType(movieType);
//
//		//MovieRatingENUM
//		ConsoleIOManager.printLine("Movie Rating: ");
//		MovieRating = ConsoleIOManager.readString();
//		movie.setMovieType(movieType);

		throw new UnsupportedOperationException();
	}

	public void updateMovie() {
		// TODO - implement Controllers.MovieEditController.UpdateMovie
		throw new UnsupportedOperationException();
	}

	public void deleteMovie() {
		//moviesearch

		//particular movie change to ENUM endofshowing
		throw new UnsupportedOperationException();
	}

	public void gotoSearchMoviesSystem() {
		NavigationController.getInstance().load(new SearchMovieController());
	}

}