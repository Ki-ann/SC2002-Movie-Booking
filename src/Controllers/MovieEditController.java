package Controllers;

import Controllers.INavigation;
import Controllers.NavigationController;
import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieType;
import Models.Data.Enums.MovieStatus;
import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.MovieEditView;
import Views.SearchMovieView;

import java.util.ArrayList;

public class MovieEditController implements INavigation {

	private int MovieEditSelection = -1;

	public void start() {
		MovieEditView.displayMenu();
		do {
			MovieEditView.displayMenu();
			MovieEditSelection = ConsoleIOManager.readInt();
			switch (MovieEditSelection) {
				case 1 -> createMovie();
				case 2 -> updateMovie();
				case 3 -> deleteMovie();
				case 4 -> NavigationController.getInstance().goBack();
				default -> {
					ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
				}
			}
		} while (MovieEditSelection != -1);
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
			} else {
				movieStatus = MovieStatus.values()[movieStatusInput - 1];
				movie.setMovieStatus(movieStatus);
				break;
			}
		} while (true);

		//DurationD
		MovieEditView.printAddDuration();
		int minutes = ConsoleIOManager.readInt();
		movie.setDuration(0, minutes, 0);

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
		ArrayList<String> cast = new ArrayList<>();
		for (int i = 0; i < numCasts; i++) {
			MovieEditView.printAddCast();
			String castMember = ConsoleIOManager.readString();
			cast.add(castMember);
		}
		movie.setCast(cast);

		//Genre
		MovieEditView.printNumGenre();
		int numGenre = ConsoleIOManager.readInt();
		ArrayList<String> genre = new ArrayList<>();
		for (int i = 0; i < numGenre; i++) {
			MovieEditView.printAddGenre();
			String genreType = ConsoleIOManager.readString();
			genre.add(genreType);
		}
		movie.setMovieGenre(genre);

		//Director
		MovieEditView.printNumDirector();
		int numDirector = ConsoleIOManager.readInt();
		ArrayList<String> director = new ArrayList<>();
		for (int i = 0; i < numDirector; i++) {
			MovieEditView.printAddDirector();
			String directorMember = ConsoleIOManager.readString();
			director.add(directorMember);
		}
		movie.setDirector(director);

		//MovieTypeENUM
		MovieEditView.printAddMovieType();
		MovieType movieType;
		int movieTypeInput;

		do {
			movieTypeInput = ConsoleIOManager.readInt();
			if (movieTypeInput < 0) {
				ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			} else {
				movieType = MovieType.values()[movieTypeInput - 1];
				movie.setMovieType(movieType);
				break;
			}
		} while (true);


		//MovieRatingENUM
		MovieEditView.printAddMovieRating();
		MovieRating movieRating;
		int movieRatingInput;

		do {
			movieRatingInput = ConsoleIOManager.readInt();
			if (movieRatingInput < 0) {
				ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			} else {
				movieRating = MovieRating.values()[movieRatingInput - 1];
				movie.setMovieRating(movieRating);
				break;
			}
		} while (true);

		MovieEditView.printAddSuccess();
		DataStoreManager.getInstance().addToStore(movie);
		ConsoleIOManager.printMenu(movie.toString());
	}

	public void updateMovie() {
		Movie[] movies;
		while (true) {
			String name = SearchMovieView.searchOptions();
			movies = gotoSearchMoviesSystem(name);
			if (movies.length == 0) {
				ConsoleIOManager.printLine("Sorry movie not found! Please type the correct movie name");
			} else {
				break;
			}
		}
		String[] titles = new String[movies.length];
		for (int i = 0; i < movies.length; i++) {
			titles[i] = movies[i].getName();
		}
		SearchMovieView.listMovies(titles);
		int choice = ConsoleIOManager.readInt();

		int MovieEditPromptSelect;
		if (choice != 0) {
			ConsoleIOManager.printMenu(movies[choice - 1].toString());
			MovieEditView.printEditMoviePrompt();
			MovieEditPromptSelect = ConsoleIOManager.readInt();

			switch (MovieEditPromptSelect) {
				case 1 : MovieEditView.printAddName();
						String name = ConsoleIOManager.readString();
						movies[choice - 1].setName(name);
						MovieEditView.printEditMovie();
						break;
				case 2 : MovieEditView.printAddMovieStatus();
						MovieStatus movieStatus;
						int movieStatusInput;

						do {
							movieStatusInput = ConsoleIOManager.readInt();
							if (movieStatusInput < 0) {
								ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
							} else {
								movieStatus = MovieStatus.values()[movieStatusInput - 1];
								movies[choice - 1].setMovieStatus(movieStatus);
								break;
							}
						} while (true);
						MovieEditView.printEditMovie();
						break;
				case 3 : MovieEditView.printAddDuration();
						int minutes = ConsoleIOManager.readInt();
						movies[choice - 1].setDuration(0, minutes, 0);
						MovieEditView.printEditMovie();
						break;
				case 4 : MovieEditView.printAddSynopsis();
						String synopsis = ConsoleIOManager.readString();
						movies[choice - 1].setSynopsis(synopsis);
						MovieEditView.printEditMovie();
						break;
				case 5 : MovieEditView.printAddLanguage();
						String language = ConsoleIOManager.readString();
						movies[choice - 1].setLanguage(language);
						MovieEditView.printEditMovie();
						break;
				case 6 : MovieEditView.printNumCast();
						int numCasts = ConsoleIOManager.readInt();
						ArrayList<String> cast = new ArrayList<>();
						for (int i = 0; i < numCasts; i++) {
							MovieEditView.printAddCast();
							String castMember = ConsoleIOManager.readString();
							cast.add(castMember);
						}
						movies[choice - 1].setCast(cast);
						MovieEditView.printEditMovie();
						break;
				case 7 : MovieEditView.printNumGenre();
						int numGenre = ConsoleIOManager.readInt();
						ArrayList<String> genre = new ArrayList<>();
						for (int i = 0; i < numGenre; i++) {
							MovieEditView.printAddGenre();
							String genreType = ConsoleIOManager.readString();
							genre.add(genreType);
						}
						movies[choice - 1].setMovieGenre(genre);
						MovieEditView.printEditMovie();
						break;
				case 8 : MovieEditView.printNumDirector();
						int numDirector = ConsoleIOManager.readInt();
						ArrayList<String> director = new ArrayList<>();
						for (int i = 0; i < numDirector; i++) {
							MovieEditView.printAddDirector();
							String directorMember = ConsoleIOManager.readString();
							director.add(directorMember);
						}
						movies[choice - 1].setDirector(director);
						MovieEditView.printEditMovie();
						break;
				case 9 : MovieEditView.printAddMovieType();
				MovieType movieType;
						int movieTypeInput;

						do {
							movieTypeInput = ConsoleIOManager.readInt();
							if (movieTypeInput < 0) {
								ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
							} else {
								movieType = MovieType.values()[movieTypeInput - 1];
								movies[choice - 1].setMovieType(movieType);
								break;
							}
						} while (true);
						MovieEditView.printEditMovie();
						break;
				case 10 : MovieEditView.printAddMovieRating();
						MovieRating movieRating;
						int movieRatingInput;

						do {
							movieRatingInput = ConsoleIOManager.readInt();
							if (movieRatingInput < 0) {
								ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
							} else {
								movieRating = MovieRating.values()[movieRatingInput - 1];
								movies[choice - 1].setMovieRating(movieRating);
								break;
							}
						} while (true);
						MovieEditView.printEditMovie();
						break;
				case 11 : NavigationController.getInstance().goBack();
				default : {
					ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
					}
				}
			}
		}

	public void deleteMovie() {
		Movie[] movies;
		while (true) {
			String name = SearchMovieView.searchOptions();
			movies = gotoSearchMoviesSystem(name);
			if (movies.length == 0) {
				ConsoleIOManager.printLine("Sorry movie not found! Please type the correct movie name");
			} else {
				break;
			}
		}
			String[] titles = new String[movies.length];
			for (int i = 0; i < movies.length; i++) {
				titles[i] = movies[i].getName();
			}
			SearchMovieView.listMovies(titles);
			int choice = ConsoleIOManager.readInt();
			if (choice != 0) {
				ConsoleIOManager.printMenu(movies[choice - 1].toString());
				//particular movie change to ENUM endofshowing
				movies[choice - 1].setMovieStatus(MovieStatus.END_SHOWING);
				MovieEditView.printDeleteMovie();
			}
		}


	public Movie[] gotoSearchMoviesSystem(String title) {
		return new SearchMovieController().searchMoviebyName(title);
	}
}