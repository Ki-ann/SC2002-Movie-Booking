package Views;

import Models.Data.Admin;
import Models.Data.Enums.TopMovieViewingState;
import Models.Data.Movie;
import java.util.ArrayList;

public class TopMovieView {
    public static void displayMenu(TopMovieViewingState currentTopMovieViewingState, Admin currentAdmin) {
        ConsoleIOManager.clearScreen();
        ArrayList<String> displayString = new ArrayList<>();
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
}
