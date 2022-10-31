package Views;

public class CustomerView {

    public static void DisplayMenu() {
		ConsoleIOManager.ClearScreen();
		ConsoleIOManager.PrintMenu("This is the customer page.",
                "I want to book a ticket",
                "I want to search for a movie",
                "List top rated movies",
                "I want to see my booking history");
        ConsoleIOManager.PrintGoBack();
    }

}