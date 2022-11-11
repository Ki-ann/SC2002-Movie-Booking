package Views;

/**
 * MovieEditView class used by MovieEditController for printing information to console using static functions
 *
 * @author Shawn Yap Zheng Yi
 * @version 1.0
 * @see Controllers.MovieEditController
 * @see Views.ConsoleIOManager
 * @since 2022-11-01
 */
public class MovieEditView {
    /**
     * Prints the selection menu.
     */
    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the Movie Editing Page", "Create a Movie", "Update existing Movies",
                "Delete existing Movies");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints message asking for name input
     */
    public static void printAddName(){
        ConsoleIOManager.printLine("Please input the Name of the movie: ");
    }
    /**
     * Prints message asking for movie status selection input
     */
    public static void printAddMovieStatus(){
        ConsoleIOManager.printLine("Please input the movie status: \n[1] Coming Soon\n[2] Preview\n[3] Now Showing");
    }

    /**
     * Prints message asking for duration input
     */
    public static void printAddDuration(){
        ConsoleIOManager.printLine("Please input the Duration of Movie (in minutes): ");
    }

    /**
     * Prints message asking for synopsis input
     */
    public static void printAddSynopsis(){
        ConsoleIOManager.printLine("Please input the Synopsis: ");
    }

    /**
     * Prints message asking for language input
     */
    public static void printAddLanguage(){
        ConsoleIOManager.printLine("Please input the Language of Movie:");
    }

    /**
     * Prints message asking for number of casts in the movie
     */
    public static void printNumCast(){
        ConsoleIOManager.printLine("Please input the number of Casts: ");
    }

    /**
     * Prints message asking for cast name input
     */
    public static void printAddCast(){
        ConsoleIOManager.printLine("Please input the Cast(s): ");
    }

    /**
     * Prints message asking for number of genres in the movie
     */
    public static void printNumGenre(){
        ConsoleIOManager.printLine("Please input the number of Genre(s): ");
    }

    /**
     * Prints message asking for genre name input
     */
    public static void printAddGenre(){
        ConsoleIOManager.printLine("Please input the Genre(s): ");
    }

    /**
     * Prints message asking for number of directors in the movie
     */
    public static void printNumDirector(){
        ConsoleIOManager.printLine("Please input the number of Director(s): ");
    }

    /**
     * Prints message asking for director name input
     */
    public static void printAddDirector(){
        ConsoleIOManager.printLine("Please input the Director(s): ");
    }

    /**
     * Prints message asking for movie type input
     */
    public static void printAddMovieType(){
        ConsoleIOManager.printLine("Please input the movie type: \n[1] DIGITAL3D\n[2] IMAX3D\n[3] BLOCKBUSTER\n[4] LOCAL");
    }

    /**
     * Prints message asking for movie rating input
     */
    public static void printAddMovieRating(){
        ConsoleIOManager.printLine("Please input the movie rating: \n[1] G\n[2] PG\n[3] PG13\n[4] NC16\n[5] M18\n[6] R21");
    }

    /**
     * Prints a preview of the movie details and creation success message
     * @param movie movie that was added to DataStore
     */
    public static void printAddSuccess(String movie){
        ConsoleIOManager.printMenu(movie);
        ConsoleIOManager.printLine("Movie successfully added!");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints message for movie deletion success
     */
    public static void printDeleteMovie(){
        ConsoleIOManager.printLine("Movie Deleted!");
    }

    /**
     * Prints message for movie edit success
     */
    public static void printEditMovie(){
        ConsoleIOManager.printLine("Movie Edit Success!");
    }

    /**
     * Prints selection menu for editing a movie
     * @param movieString movie details
     */
    public static void printEditMoviePrompt(String movieString){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu(movieString);
        ConsoleIOManager.printMenu("This is the Movie Editing Page",
                "Movie Name",
                "Movie Status",
                "Duration of Movie",
                "Synopsis",
                "Language of Movie",
                "Cast Details",
                "Genre Details",
                "Director Details",
                "Movie Type",
                "Movie Rating Details");
        ConsoleIOManager.printGoBack();
    }
}