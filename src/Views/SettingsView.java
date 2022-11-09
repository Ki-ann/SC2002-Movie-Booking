package Views;
import Models.Data.*;
import Models.Data.Enums.AgeClass;
import java.util.*;

/**
 * SettingsView class used by SettingsController for printing information to console using static functions
 *
 * @author Han Zhiguang
 * @version 1.0
 * @since 2022-10-30
 * @see Controllers.SettingsController
 * @see Views.ConsoleIOManager
 */
public class SettingsView {
    /**
     * Prints the selection menu.
     */
    Scanner sc = new Scanner(System.in);
    public static void displayMenu() {
		ConsoleIOManager.clearScreen();
		ConsoleIOManager.printMenu("This is the setting page.",
                "I want to manage ticket price",
                "I want to manage holidays",
                "I want to edit Customer Top Movie viewing states");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of price management
     * @param setting Default setting
     */
    public static void printTicketPriceMenu(Setting setting) {
        ConsoleIOManager.clearScreen();
        displayTicketPrices(setting);
        ConsoleIOManager.printMenu("Manage Ticket Price",
                "Edit Standard Ticket Price");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a selection menu of holiday management.
     */
    public static void printHolidayMenu(){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Manage holidays",
        "List all holidays",
                "Add a holiday");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints out all the ticket price
     * @param setting Price managed setting
     */
    public static void displayTicketPrices(Setting setting){
        ConsoleIOManager.printMenu("     TICKET PRICE CHART     ");
		ConsoleIOManager.printF("%18s   $%-8.2f\n","Adult",setting.getStandardPrice(AgeClass.ADULT));
		ConsoleIOManager.printF("%18s   $%-8.2f\n", "Student",setting.getStandardPrice(AgeClass.STUDENT));
		ConsoleIOManager.printF("%18s   $%-8.2f\n","Senior",setting.getStandardPrice(AgeClass.SENIOR));
		ConsoleIOManager.printF("%18s   $%-8.2f\n", "Children",setting.getStandardPrice(AgeClass.CHILD));
		ConsoleIOManager.printF("%18s   $%-8.2f\n", "Weekend",setting.getWeekendPrice());
		ConsoleIOManager.printLine();
    }

    /**
     * Prints out all the ticket price
     * @param setting Price managed setting
     */
    public static void printSetStandardPrice(Setting setting) {
        ConsoleIOManager.printLine("Current standard price: $" + setting.getStandardPrice());
        ConsoleIOManager.printLine("Enter new standard price: ");
    }

    /**
     * Prints a Standard price updated message
     */
    public static void printSetStandardPriceSuccess() {
        ConsoleIOManager.printLine("Standard price updated.");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a holiday list
     * @param holidayString holiday list
     */
    public static void printHolidayListMenu(String[] holidayString) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Holiday list option", holidayString);
        if (holidayString.length == 0) {
            ConsoleIOManager.printLine("No holiday exists");
        }
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a holiday added message
     */
    public static void printAddHolidaySuccess(){
        ConsoleIOManager.printLine("Successfully added the holiday.");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a choice of holiday deletion
     */
    public static void displayHolidayDetails(Holiday holiday) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printLine(holiday.detailString());
        ConsoleIOManager.printLine();
        ConsoleIOManager.printLine("Please enter your choice:");
        ConsoleIOManager.printLine("[Y] Do you want to delete this holiday?");
        ConsoleIOManager.printLine("[N] Cancel and go back");
    }

    /**
     * Prints a holiday deleted message
     */
    public static void printHolidayDeletionSuccess() {
        ConsoleIOManager.printLine("Successfully deleted the holiday.");
        ConsoleIOManager.printGoBack();
    }
}