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

/**
 * MovieEditController is a Navigation that manages the logic and flow for booking of movie tickets
 *
 * @author Shawn Yap Zheng Yi
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieEditController implements INavigation {

    private int MovieEditSelection = -1;

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
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

    /**
     * The function that handles the flow for creation of movies.
     */
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

    /**
     * The function that handles the flow for updating of movies
     */
    public void updateMovie() {
        SearchMovieView.searchOptions();
        Movie[] movies = gotoSearchMoviesSystem();

        while (true) {
            SearchMovieView.listMovies(movies);
            int choice = ConsoleIOManager.readInt();
            boolean valid = true;
            int MovieEditPromptSelect;
            if (choice == 0) {
                break;
            }
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
                        valid = false;
                        continue;
                    }
                    default -> {
                        ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    }
                }

                DataStoreManager.getInstance().saveAll();

                // Wait for user ready
                int input;
                do {
                    input = ConsoleIOManager.readInt();

                    if (input != 0) {
                        ConsoleIOManager.printLine("Invalid input!");
                    } else {
                        break;
                    }
                } while (true);

            } while (valid);
        }
    }

    /**
     * The function that handles the flow for deletion of movies
     */
    public void deleteMovie() {
        SearchMovieView.searchOptions();
        Movie[] movies = gotoSearchMoviesSystem();

        SearchMovieView.listMovies(movies);
        int choice = ConsoleIOManager.readInt();
        if (choice != 0) {
            DataStoreManager.getInstance().removeFromStore(movies[choice - 1]);
            movies[choice - 1].setMovieStatus(MovieStatus.END_SHOWING);
            ConsoleIOManager.printMenu(movies[choice - 1].toString());
            //particular movie change to ENUM endofshowing
            DataStoreManager.getInstance().addToStore(movies[choice - 1]);
            MovieEditView.printDeleteMovie();

            // Wait for user ready
            int input;
            do {
                input = ConsoleIOManager.readInt();

                if (input != 0) {
                    ConsoleIOManager.printLine("Invalid input!");
                } else {
                    break;
                }
            } while (true);
        }
    }

    /**
     * Retrieves User input for new movie name
     *
     * @param movie current temporary new movie object
     */
    private void addMovieName(Movie movie) {
        MovieEditView.printAddName();
        String name = ConsoleIOManager.readString();
        if (name.length() == 0) {
            MovieEditView.printEmptyError();
            addMovieName(movie);
        } else {
            movie.setName(name);
        }
    }

    /**
     * Retrieves User input for new movie name
     *
     * @param movie current temporary new movie object
     */
    private void addMovieStatus(Movie movie) {
        MovieEditView.printAddMovieStatus();
        MovieStatus movieStatus;
        int movieStatusInput;

        do {
            movieStatusInput = ConsoleIOManager.readInt();
            if (movieStatusInput < 0 || movieStatusInput > MovieStatus.values().length - 1) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieStatus = MovieStatus.values()[movieStatusInput - 1];
                movie.setMovieStatus(movieStatus);
                break;
            }
        } while (true);
    }

    /**
     * Retrieves User input for new movie duration
     *
     * @param movie current temporary new movie object
     */
    private void addMovieDuration(Movie movie) {
        MovieEditView.printAddDuration();
        int minutes = ConsoleIOManager.readInt();
        movie.setDuration(0, minutes, 0);
    }

    /**
     * Retrieves User input for new movie synopsis
     *
     * @param movie current temporary new movie object
     */
    private void addMovieSynopsis(Movie movie) {
        MovieEditView.printAddSynopsis();
        String synopsis = ConsoleIOManager.readString();
        if (synopsis.length() == 0) {
            MovieEditView.printEmptyError();
            addMovieSynopsis(movie);
        } else {
            movie.setSynopsis(synopsis);
        }
    }

    /**
     * Retrieves User input for new movie language
     *
     * @param movie current temporary new movie object
     */
    private void addMovieLanguages(Movie movie) {
        MovieEditView.printAddLanguage();
        String language = ConsoleIOManager.readString();
        if (language.length() == 0) {
            MovieEditView.printEmptyError();
            addMovieLanguages(movie);
        } else {
            movie.setLanguage(language);
        }
    }

    /**
     * Retrieves User input for multiple new movie casts
     *
     * @param movie current temporary new movie object
     */
    private void addMovieCasts(Movie movie) {
        MovieEditView.printNumCast();
        int numCasts = ConsoleIOManager.readInt();

        if (numCasts == 0) {
            MovieEditView.printEmptyError();
        } else {
            ArrayList<String> cast = new ArrayList<>();
            for (int i = 0; i < numCasts; i++) {
                MovieEditView.printAddCast();
                String castMember = ConsoleIOManager.readString();
                cast.add(castMember);
            }
            movie.setCast(cast);
        }
    }

    /**
     * Retrieves User input for multiple new movie genres
     *
     * @param movie current temporary new movie object
     */
    private void addMovieGenres(Movie movie) {
        MovieEditView.printNumGenre();
        int numGenre = ConsoleIOManager.readInt();
        if (numGenre == 0) {
            MovieEditView.printEmptyError();
            addMovieGenres(movie);
        } else {
            ArrayList<String> genre = new ArrayList<>();
            for (int i = 0; i < numGenre; i++) {
                MovieEditView.printAddGenre();
                String genreType = ConsoleIOManager.readString();
                genre.add(genreType);
            }
            movie.setMovieGenre(genre);
        }

    }

    /**
     * Retrieves User input for multiple new movie directors
     *
     * @param movie current temporary new movie object
     */
    private void addMovieDirectors(Movie movie) {
        MovieEditView.printNumDirector();
        int numDirector = ConsoleIOManager.readInt();
        if (numDirector == 0) {
            MovieEditView.printEmptyError();
            addMovieDirectors(movie);
        } else {
            ArrayList<String> director = new ArrayList<>();
            for (int i = 0; i < numDirector; i++) {
                MovieEditView.printAddDirector();
                String directorMember = ConsoleIOManager.readString();
                director.add(directorMember);
            }
            movie.setDirector(director);
        }

    }

    /**
     * Retrieves User input for new movie type
     *
     * @param movie current temporary new movie object
     */
    private void addMovieType(Movie movie) {
        MovieEditView.printAddMovieType();
        MovieType movieType;
        int movieTypeInput;

        do {
            movieTypeInput = ConsoleIOManager.readInt();
            if (movieTypeInput < 0 || movieTypeInput > MovieType.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieType = MovieType.values()[movieTypeInput - 1];
                movie.setMovieType(movieType);
                break;
            }
        } while (true);
    }

    /**
     * Retrieves User input for new movie rating type
     *
     * @param movie current temporary new movie object
     */
    private void addMovieRating(Movie movie) {
        MovieEditView.printAddMovieRating();
        MovieRating movieRating;
        int movieRatingInput;

        do {
            movieRatingInput = ConsoleIOManager.readInt();
            if (movieRatingInput < 0 || movieRatingInput > MovieRating.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieRating = MovieRating.values()[movieRatingInput - 1];
                movie.setMovieRating(movieRating);
                break;
            }
        } while (true);
    }

    /**
     * Updates the rating setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editRatingDetail(Movie[] movies, int choice) {
        MovieEditView.printAddMovieRating();
        MovieRating movieRating;
        int movieRatingInput;

        do {
            movieRatingInput = ConsoleIOManager.readInt();
            if (movieRatingInput < 0 || movieRatingInput > MovieRating.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieRating = MovieRating.values()[movieRatingInput - 1];
                movies[choice - 1].setMovieRating(movieRating);
                break;
            }
        } while (true);
        MovieEditView.printEditMovie();
    }

    /**
     * Updates the movie type setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editMovieType(Movie[] movies, int choice) {
        MovieEditView.printAddMovieType();
        MovieType movieType;
        int movieTypeInput;

        do {
            movieTypeInput = ConsoleIOManager.readInt();
            if (movieTypeInput < 0 || movieTypeInput > MovieType.values().length) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieType = MovieType.values()[movieTypeInput - 1];
                movies[choice - 1].setMovieType(movieType);
                break;
            }
        } while (true);
        MovieEditView.printEditMovie();
    }

    /**
     * Updates the director setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editDirector(Movie[] movies, int choice) {
        MovieEditView.printNumDirector();
        int numDirector = ConsoleIOManager.readInt();
        if (numDirector == 0) {
            MovieEditView.printEmptyError();
            editDirector(movies, choice);
        } else {
            ArrayList<String> director = new ArrayList<>();
            for (int i = 0; i < numDirector; i++) {
                MovieEditView.printAddDirector();
                String directorMember = ConsoleIOManager.readString();
                director.add(directorMember);
            }
            movies[choice - 1].setDirector(director);
            MovieEditView.printEditMovie();
        }
    }

    /**
     * Updates the genre setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editGenre(Movie[] movies, int choice) {
        MovieEditView.printNumGenre();
        int numGenre = ConsoleIOManager.readInt();
        if (numGenre == 0) {
            MovieEditView.printEmptyError();
            editGenre(movies, choice);
        } else {
            ArrayList<String> genre = new ArrayList<>();
            for (int i = 0; i < numGenre; i++) {
                MovieEditView.printAddGenre();
                String genreType = ConsoleIOManager.readString();
                genre.add(genreType);
            }
            movies[choice - 1].setMovieGenre(genre);
            MovieEditView.printEditMovie();
        }

    }

    /**
     * Updates the cast setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editCast(Movie[] movies, int choice) {
        MovieEditView.printNumCast();
        int numCasts = ConsoleIOManager.readInt();

        if (numCasts == 0) {
            MovieEditView.printEmptyError();
            editCast(movies, choice);
        } else {
            ArrayList<String> cast = new ArrayList<>();
            for (int i = 0; i < numCasts; i++) {
                MovieEditView.printAddCast();
                String castMember = ConsoleIOManager.readString();
                cast.add(castMember);
            }
            movies[choice - 1].setCast(cast);
            MovieEditView.printEditMovie();
        }
    }

    /**
     * Updates the language setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editLanguage(Movie[] movies, int choice) {
        MovieEditView.printAddLanguage();
        String language = ConsoleIOManager.readString();
        if (language.length() == 0) {
            MovieEditView.printEmptyError();
            editLanguage(movies, choice);
        } else {
            movies[choice - 1].setLanguage(language);
            MovieEditView.printEditMovie();
        }
    }

    /**
     * Updates the synopsis setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editSynopsis(Movie[] movies, int choice) {
        MovieEditView.printAddSynopsis();
        String synopsis = ConsoleIOManager.readString();
        if (synopsis.length() == 0) {
            MovieEditView.printEmptyError();
            editSynopsis(movies, choice);
        } else {
            movies[choice - 1].setSynopsis(synopsis);
            MovieEditView.printEditMovie();
        }
    }

    /**
     * Updates the duration setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editMovieDuration(Movie[] movies, int choice) {
        MovieEditView.printAddDuration();
        int minutes = ConsoleIOManager.readInt();
        movies[choice - 1].setDuration(0, minutes, 0);
        MovieEditView.printEditMovie();
    }

    /**
     * Updates the status setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editMovieStatus(Movie[] movies, int choice) {
        MovieEditView.printAddMovieStatus();
        MovieStatus movieStatus;
        int movieStatusInput;

        do {
            movieStatusInput = ConsoleIOManager.readInt();
            if (movieStatusInput < 0 || movieStatusInput > MovieStatus.values().length - 1) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else {
                movieStatus = MovieStatus.values()[movieStatusInput - 1];
                movies[choice - 1].setMovieStatus(movieStatus);
                break;
            }
        } while (true);
        MovieEditView.printEditMovie();
    }

    /**
     * Updates the name setting of the movie using a new User input
     *
     * @param movies Entire movie list array
     * @param choice User selected choice
     */
    private void editName(Movie[] movies, int choice) {
        MovieEditView.printAddName();
        String name = ConsoleIOManager.readString();
        if (name.length() == 0) {
            MovieEditView.printEmptyError();
            editName(movies, choice);
        } else {
            movies[choice - 1].setName(name);
        }
        MovieEditView.printEditMovie();
    }

    /**
     * Calls SearchMovieController to retrieve a list of movies by name
     *
     * @return list of movies filtered by the substring of a given string input
     */
    public Movie[] gotoSearchMoviesSystem() {
        return new SearchMovieController().searchMovieByName();
    }
}