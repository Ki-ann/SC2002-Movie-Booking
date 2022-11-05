package Views;

import Models.Data.Movie;

public class MovieEditView {

    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the Movie Editing Page", "Create a Movie", "Update existing Movies",
                "Delete existing Movies");
        ConsoleIOManager.printGoBack();
    }

    public static void printAddName(){
        ConsoleIOManager.printLine("Please input the Name of the movie: ");
    }

    public static void printAddMovieStatus(){
        ConsoleIOManager.printLine("Please input the movie status: \n[1] Coming Soon\n[2] Preview\n[3] Now Showing");
    }

    public static void printAddDuration(){
        ConsoleIOManager.printLine("Please input the Duration of Movie (in minutes): ");
    }

    public static void printAddSynopsis(){
        ConsoleIOManager.printLine("Please input the Synopsis: ");
    }

    public static void printAddLanguage(){
        ConsoleIOManager.printLine("Please input the Language of Movie:");
    }

    public static void printNumCast(){
        ConsoleIOManager.printLine("Please input the number of Casts: ");
    }

    public static void printAddCast(){
        ConsoleIOManager.printLine("Please input the Cast(s): ");
    }

    public static void printNumGenre(){
        ConsoleIOManager.printLine("Please input the number of Genre(s): ");
    }

    public static void printAddGenre(){
        ConsoleIOManager.printLine("Please input the Genre(s): ");
    }

    public static void printNumDirector(){
        ConsoleIOManager.printLine("Please input the number of Director(s): ");
    }

    public static void printAddDirector(){
        ConsoleIOManager.printLine("Please input the Director(s): ");
    }

    public static void printAddMovieType(){
        ConsoleIOManager.printLine("Please input the movie type: \n[1] DIGITAL3D\n[2] IMAX3D\n[3] BLOCKBUSTER\n[4] LOCAL");
    }

    public static void printAddMovieRating(){
        ConsoleIOManager.printLine("Please input the movie rating: \n[1] G\n[2] PG\n[3] PG13\n[4] NC16\n[5] M18\n[6] R21");
    }
    public static void printAddSuccess(){
        ConsoleIOManager.printLine("Movie successfully added!");
    }
}