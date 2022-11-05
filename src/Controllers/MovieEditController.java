package Controllers;

import Controllers.INavigation;
import Controllers.NavigationController;
import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieType;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;
import Models.Data.Movie;
import Views.ConsoleIOManager;
import Views.MovieEditView;
import java.util.ArrayList;

public class MovieEditController implements INavigation {

	private int MovieEditSelection = -1;
	public void start() {
		MovieEditView.displayMenu();
		do {
			if (MovieEditSelection == -1) {
				MovieEditSelection = ConsoleIOManager.readInt();
			}
			switch (MovieEditSelection) {
				case 1 -> createMovie();
				case 2 -> updateMovie();
				case 3 -> deleteMovie();
				case 4 -> NavigationController.getInstance().goBack();
				default -> {
					ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
					MovieEditSelection = -1;
				}
			}
		} while (MovieEditSelection == -1);
	}

	public void createMovie() {
		Movie movie = new Movie();

		//PROMPTS
		ConsoleIOManager.printMenu("Movie Details");

		//Name
		MovieEditView.printAddName();
		String name = ConsoleIOManager.readString();
		movie.setName(name);

//		//movieStatusENUM
		MovieEditView.printAddMovieStatus();
		MovieStatus movieStatus;
		int movieStatusInput;

		do {
			movieStatusInput = ConsoleIOManager.readInt();
			if (movieStatusInput < 0) {
				ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			}

			else {
				movieStatus = MovieStatus.values()[movieStatusInput-1];
				movie.setMovieStatus(movieStatus);
				break;
			}
		} while(true);

		//DurationD
		MovieEditView.printAddDuration();
		int minutes = ConsoleIOManager.readInt();
		movie.setDuration(0,minutes,0);

		//SynopsisD
		MovieEditView.printAddSynopsis();
		String synopsis = ConsoleIOManager.readString();
		movie.setSynopsis(synopsis);

		//LanguageD
		MovieEditView.printAddLanguage();
		String language = ConsoleIOManager.readString();
		movie.setLanguage(language);

		//Cast
		MovieEditView.printNumCast();
		int numCasts = ConsoleIOManager.readInt();
		ArrayList <String> cast = new ArrayList<>();
		for (int i = 0; i < numCasts; i++){
			MovieEditView.printAddCast();
			String castMember = ConsoleIOManager.readString();
			cast.add(castMember);
			movie.setCast(cast);
		}

		//Genre
		MovieEditView.printNumGenre();
		int numGenre = ConsoleIOManager.readInt();
		ArrayList <String> genre = new ArrayList<>();
		for (int i = 0; i < numGenre; i++){
			MovieEditView.printAddGenre();
			String genreType = ConsoleIOManager.readString();
			cast.add(genreType);
			movie.setCast(genre);
		}

		//Director
		MovieEditView.printNumDirector();
		int numDirector = ConsoleIOManager.readInt();
		ArrayList <String> director = new ArrayList<>();
		for (int i = 0; i < numDirector; i++){
			MovieEditView.printAddDirector();
			String directorMember = ConsoleIOManager.readString();
			cast.add(directorMember);
			movie.setCast(director);
		}

		//MovieTypeENUM
		MovieType movieType;
		int movieTypeInput;

		do {
			MovieEditView.printAddMovieType();
			movieTypeInput = ConsoleIOManager.readInt();
			if (movieTypeInput < 0) {
				ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			}

			else {
				movieType = MovieType.values()[movieTypeInput-1];
				movie.setMovieType(movieType);
				break;
			}
		} while(true);


		//MovieRatingENUM
		MovieEditView.printAddMovieRating();
		MovieRating movieRating;
		int movieRatingInput;

		do {
			movieRatingInput = ConsoleIOManager.readInt();
			if (movieRatingInput < 0) {
				ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			}

			else {
				movieRating = MovieRating.values()[movieRatingInput-1];
				movie.setMovieRating(movieRating);
				break;
			}
		} while(true);

		throw new UnsupportedOperationException();
	}

	public void updateMovie() {
		// TODO - implement Controllers.MovieEditController.UpdateMovie
		throw new UnsupportedOperationException();
	}

	public void deleteMovie() {
		//MovieSearch

		//particular movie change to ENUM endofshowing
		throw new UnsupportedOperationException();
	}

	public void gotoSearchMoviesSystem() {
		NavigationController.getInstance().load(new SearchMovieController());
	}

}