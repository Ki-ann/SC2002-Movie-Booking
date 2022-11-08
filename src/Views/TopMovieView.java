package Views;

import Models.Data.Admin;
import Models.Data.Enums.AgeClass;
import Models.Data.Enums.TopMovieViewingState;
import Models.Data.Movie;
import Models.Data.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class TopMovieView {
    public static void displayMenu(TopMovieViewingState currentTopMovieViewingState, Admin currentAdmin) {
        ConsoleIOManager.clearScreen();
        ArrayList<String> displayString = new ArrayList<>();
        if(currentAdmin != null){
            ConsoleIOManager.printMenu("Admin: Showing all viewing modes");
        }
        switch (currentTopMovieViewingState) {
            case BY_SALES:
                displayString.add("List by ticket sales");
                break;
            case BY_BOTH: //By both, you can fall through switch case and add both
                displayString.add("List by ticket sales");
            case BY_RATINGS:
                displayString.add("List by reviewer ratings");
                break;
        }

        if(currentAdmin != null){
            displayString.add("Admin: Edit customer viewing mode");
        }
        ConsoleIOManager.printMenu("Show current Top 5 ranking movies",
                displayString.toArray(String[]::new));

        ConsoleIOManager.printGoBack();
    }

    public static void printTopSales(ArrayList<Movie> store) {
        ConsoleIOManager.clearScreen();
        ArrayList<Movie> sortedList = new ArrayList<>(store);
        sortedList.sort((m1, m2)-> Integer.compare(m2.getTicketSales(), m1.getTicketSales()));
        ConsoleIOManager.printMenu("Current Top 5 ranking movies by Ticket Sales");
        for(int i = 0; i < sortedList.size() && i < 5;++i){
            ConsoleIOManager.printF("[%d] %-40s Tickets Sold: %d\n",i+1, sortedList.get(i).getName(), sortedList.get(i).getTicketSales());
        }
        ConsoleIOManager.printGoBack();
    }

    public static void printTopReviews(ArrayList<Movie> store) {
        ConsoleIOManager.clearScreen();
        ArrayList<Movie> sortedList = new ArrayList<>(store);
        sortedList.sort((m1, m2)-> Float.compare(m2.getAverageRatingFloat(), m1.getAverageRatingFloat()));
        ConsoleIOManager.printMenu("Current Top 5 ranking movies by Ticket Reviews");
        for(int i = 0; i < sortedList.size() && i < 5;++i){
            ConsoleIOManager.printF("[%d] %-40s Review Score: %.2f\n",i+1, sortedList.get(i).getName(), sortedList.get(i).getAverageRatingFloat());
        }
        ConsoleIOManager.printGoBack();
    }

    public static void printEditStates() {
        TopMovieViewingState currentBookingState = Setting.getSettings().getCurrentTopMovieViewingState();
        String[] stateStringList = Arrays.stream(TopMovieViewingState.values())
                .map(e-> currentBookingState.name().equals(e.name()) ? e.name() + " (Current Mode)" : e.name())
                .toArray(String[]::new);

        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Please select the review mode for customers:",
                stateStringList);
        ConsoleIOManager.printGoBack();
    }

    public static void printUnauthorized() {
        ConsoleIOManager.printLine("Unauthorized attempt!");
    }

    public static void printEditSuccess() {
        ConsoleIOManager.printLine("Top movie viewing state updated!");
        ConsoleIOManager.printGoBack();
    }
}
