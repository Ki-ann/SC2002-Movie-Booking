package Views;

import Models.Data.Movie;

public class MovieEditView {

    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("This is the Movie Editing Page", "Create a Movie", "Update existing Movies",
                "Delete existing Movies",
                "Go Back");
        ConsoleIOManager.printGoBack();
    }

    public static void printAddName(){
        ConsoleIOManager.printLine("Please input the Name of the movie: ");
    }

    public static void printAddMovieStatus(){
        ConsoleIOManager.printLine("Please input the movie status: \n1: Coming Soon\n2: Preview\n3: Now Showing\n");
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
        ConsoleIOManager.printLine("Movie Type: ");
    }

    public static void printAddMovieRating(){
        ConsoleIOManager.printLine("Movie Rating: ");
    }


}