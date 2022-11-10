package Controllers;

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
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                }
            }
        } while (MovieEditSelection != -1);
    }

    public void createMovie() {
        Movie movie = new Movie();

        //PROMPTS
        ConsoleIOManager.printMenu("Create Movie Details");

        //Name
        addMovieName(movie);

		//movieStatusENUM
        addMovieStatus(movie);

        //DurationD
        addMovieDuration(movie);

        //SynopsisD
        addMovieSynopsis(movie);

        //LanguageD
        addMovieLanguages(movie);

        //Cast
        addMovieCasts(movie);

        //Genre
        addMovieGenres(movie);

        //Director
        addMovieDirectors(movie);

        //MovieTypeENUM
        addMovieType(movie);

        //MovieRatingENUM
        addMovieRating(movie);

        MovieEditView.printAddSuccess(movie.toString());
        DataStoreManager.getInstance().addToStore(movie);

        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input != 0) {
                ConsoleIOManager.printLine("Invalid input! ");
            } else {
                break;
            }
        } while (true);
    }

    public void updateMovie() {
        SearchMovieView.searchOptions();
        Movie[] movies = gotoSearchMoviesSystem();

        SearchMovieView.listMovies(movies);
        int choice = ConsoleIOManager.readInt();

        boolean valid = true;
        int MovieEditPromptSelect;
        do {
            MovieEditView.printEditMoviePrompt(movies[choice - 1].toString());
            MovieEditPromptSelect = ConsoleIOManager.readInt();

            switch (MovieEditPromptSelect) {
                case 1 -> editName(movies, choice);
                case 2 -> editMovieStatus(movies, choice);
                case 3 -> editMovieDuration(movies, choice);
                case 4 -> editSynopsis(movies, choice);
                case 5 -> editLanguage(movies, choice);
                case 6 -> editCast(movies, choice);
                case 7 -> editGenre(movies, choice);
                case 8 -> editDirector(movies, choice);
                case 9 -> editMovieType(movies, choice);
                case 10 -> editRatingDetail(movies, choice);
                case 0 -> {
                    NavigationController.getInstance().goBack();
                    valid = false;
                }
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                }
            }

            DataStoreManager.getInstance().saveAll();
        } while (valid);
    }

    private void addMovieName(Movie movie) {
        MovieEditView.printAddName();
        String name = ConsoleIOManager.readString();
        movie.setName(name);
    }

    private void addMovieStatus(Movie movie) {
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
    }

    private void addMovieDuration(Movie movie) {
        MovieEditView.printAddDuration();
        int minutes = ConsoleIOManager.readInt();
        movie.setDuration(0, minutes, 0);
    }

    private void addMovieSynopsis(Movie movie) {
        MovieEditView.printAddSynopsis();
        String synopsis = ConsoleIOManager.readString();
        movie.setSynopsis(synopsis);
    }

    private void addMovieLanguages(Movie movie) {
        MovieEditView.printAddLanguage();
        String language = ConsoleIOManager.readString();
        movie.setLanguage(language);
    }

    private void addMovieCasts(Movie movie) {
        MovieEditView.printNumCast();
        int numCasts = ConsoleIOManager.readInt();
        ArrayList<String> cast = new ArrayList<>();
        for (int i = 0; i < numCasts; i++) {
            MovieEditView.printAddCast();
            String castMember = ConsoleIOManager.readString();
            cast.add(castMember);
        }
        movie.setCast(cast);
    }

    private void addMovieGenres(Movie movie) {
        MovieEditView.printNumGenre();
        int numGenre = ConsoleIOManager.readInt();
        ArrayList<String> genre = new ArrayList<>();
        for (int i = 0; i < numGenre; i++) {
            MovieEditView.printAddGenre();
            String genreType = ConsoleIOManager.readString();
            genre.add(genreType);
        }
        movie.setMovieGenre(genre);
    }

    private void addMovieDirectors(Movie movie) {
        MovieEditView.printNumDirector();
        int numDirector = ConsoleIOManager.readInt();
        ArrayList<String> director = new ArrayList<>();
        for (int i = 0; i < numDirector; i++) {
            MovieEditView.printAddDirector();
            String directorMember = ConsoleIOManager.readString();
            director.add(directorMember);
        }
        movie.setDirector(director);
    }

    private void addMovieType(Movie movie) {
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
    }

    private void addMovieRating(Movie movie) {
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
    }

    private void editRatingDetail(Movie[] movies, int choice) {
        MovieEditView.printAddMovieRating();
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
    }

    private void editMovieType(Movie[] movies, int choice) {
        MovieEditView.printAddMovieType();
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
    }

    private void editDirector(Movie[] movies, int choice) {
        MovieEditView.printNumDirector();
        int numDirector = ConsoleIOManager.readInt();
        ArrayList<String> director = new ArrayList<>();
        for (int i = 0; i < numDirector; i++) {
            MovieEditView.printAddDirector();
            String directorMember = ConsoleIOManager.readString();
            director.add(directorMember);
        }
        movies[choice - 1].setDirector(director);
        MovieEditView.printEditMovie();
    }

    private void editGenre(Movie[] movies, int choice) {
        MovieEditView.printNumGenre();
        int numGenre = ConsoleIOManager.readInt();
        ArrayList<String> genre = new ArrayList<>();
        for (int i = 0; i < numGenre; i++) {
            MovieEditView.printAddGenre();
            String genreType = ConsoleIOManager.readString();
            genre.add(genreType);
        }
        movies[choice - 1].setMovieGenre(genre);
        MovieEditView.printEditMovie();
    }

    private void editCast(Movie[] movies, int choice) {
        MovieEditView.printNumCast();
        int numCasts = ConsoleIOManager.readInt();
        ArrayList<String> cast = new ArrayList<>();
        for (int i = 0; i < numCasts; i++) {
            MovieEditView.printAddCast();
            String castMember = ConsoleIOManager.readString();
            cast.add(castMember);
        }
        movies[choice - 1].setCast(cast);
        MovieEditView.printEditMovie();
    }

    private void editLanguage(Movie[] movies, int choice) {
        MovieEditView.printAddLanguage();
        String language = ConsoleIOManager.readString();
        movies[choice - 1].setLanguage(language);
        MovieEditView.printEditMovie();
    }

    private void editSynopsis(Movie[] movies, int choice) {
        MovieEditView.printAddSynopsis();
        String synopsis = ConsoleIOManager.readString();
        movies[choice - 1].setSynopsis(synopsis);
        MovieEditView.printEditMovie();
    }

    private void editMovieDuration(Movie[] movies, int choice) {
        MovieEditView.printAddDuration();
        int minutes = ConsoleIOManager.readInt();
        movies[choice - 1].setDuration(0, minutes, 0);
        MovieEditView.printEditMovie();
    }

    private void editMovieStatus(Movie[] movies, int choice) {
        MovieEditView.printAddMovieStatus();
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
    }

    private void editName(Movie[] movies, int choice) {
        MovieEditView.printAddName();
        String name = ConsoleIOManager.readString();
        movies[choice - 1].setName(name);
        MovieEditView.printEditMovie();
    }

    public void deleteMovie() {
        SearchMovieView.searchOptions();
        Movie[] movies = gotoSearchMoviesSystem();

        SearchMovieView.listMovies(movies);
        int choice = ConsoleIOManager.readInt();
        if (choice != 0) {
            ConsoleIOManager.printMenu(movies[choice - 1].toString());
            //particular movie change to ENUM endofshowing
            movies[choice - 1].setMovieStatus(MovieStatus.END_SHOWING);
            MovieEditView.printDeleteMovie();
        }
    }


    public Movie[] gotoSearchMoviesSystem() {
        return new SearchMovieController().searchMovieByName();
    }
}