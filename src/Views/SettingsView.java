package Views;
import Models.Data.*;
import java.util.*;

public class SettingsView {
    
    Scanner sc = new Scanner(System.in);
    public static void displayMenu() {
		ConsoleIOManager.clearScreen();
		ConsoleIOManager.printMenu("This is the setting page.",
                "I want to manage ticket price",
                "I want to manage holidays");
        ConsoleIOManager.printGoBack();
    }
    public static void printTicketPriceMenu(Setting setting) {
        ConsoleIOManager.clearScreen();
        displayTicketPrices(setting);
        ConsoleIOManager.printMenu("Manage Ticket Price",
                "Edit Standard Ticket Price");
        ConsoleIOManager.printGoBack();
    }

    public static void printHolidayMenu(){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Manage holidays",
        "List all holidays",
                "Add a holiday");
        ConsoleIOManager.printGoBack();
    }


    public static void displayTicketPrices(Setting setting){
        ConsoleIOManager.printMenu("     TICKET PRICE CHART     ");
		ConsoleIOManager.printF("Adult\t\t\t\t%.2f\n",setting.getStandardAdultPrice());
		ConsoleIOManager.printF("Student\t\t\t\t%.2f\n", setting.getStandardStudentPrice());
		ConsoleIOManager.printF("Senior\t\t\t\t%.2f\n",setting.getStandardSeniorPrice());
		ConsoleIOManager.printF("Children\t\t\t%.2f\n", setting.getStandardChildPrice());
		ConsoleIOManager.printF("Weekend\t\t\t\t%.2f\n", setting.getStandardWeekendPrice());
		ConsoleIOManager.printF("\n");
    }

    public static void printSetStandardPrice(Setting setting) {
        ConsoleIOManager.printLine("Current standard price: $" + setting.getStandardPrice());
        ConsoleIOManager.printLine("Enter new standard price: ");
    }

    public static void printSetStandardPriceSuccess() {
        ConsoleIOManager.printLine("Standard price updated.");
        ConsoleIOManager.printGoBack();
    }

    public static void printHolidayListMenu(String[] holidayString) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Holiday list option", holidayString);
        if (holidayString.length == 0) {
            ConsoleIOManager.printLine("No holiday exists");
        }
        ConsoleIOManager.printGoBack();
    }

    public static void printAddHolidaySuccess(){
        ConsoleIOManager.printLine("Successfully added the holiday.");
        ConsoleIOManager.printGoBack();
    }

    public static void displayHolidayDetails(Holiday holiday) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printLine(holiday.detailString());
        ConsoleIOManager.printLine();
        ConsoleIOManager.printLine("Please enter your choice:");
        ConsoleIOManager.printLine("[Y] Do you want to delete this holiday?");
        ConsoleIOManager.printLine("[N] Cancel and go back");
    }

    public static void printHolidayDeletionSuccess() {
        ConsoleIOManager.printLine("Successfully deleted the holiday.");
        ConsoleIOManager.printGoBack();
    }
}