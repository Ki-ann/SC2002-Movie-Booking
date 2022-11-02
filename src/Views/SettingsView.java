package Views;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Models.Data.*;
import java.util.*;

public class SettingsView {
    
    Scanner sc = new Scanner(System.in);
    public static void DisplayMenu() {
		ConsoleIOManager.clearScreen();
		ConsoleIOManager.printMenu("This is the setting page.",
                "I want to manage ticket price",
                "I want to manage holidays");
        ConsoleIOManager.printGoBack();
    }
    public static void ManageTicketPrice() {
        ConsoleIOManager.printLine("____________ MANAGE TICKET PRICE __________\n" 
							+ "												\n"
							+ " 1. Edit Standard Ticket Price     	      	\n"
							+ " 0. Go Back      		      	\n"
							+ "____________________________________________	\n"
							+ " Enter your choice here: ");
			
    };

    public static void printMenu(){
        ConsoleIOManager.printMenu("Manage holidays",
        "1. List all holidays",
                "2. Add a holiday");
        ConsoleIOManager.printGoBack();
    }
    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = ConsoleIOManager.readInt();
        } catch (InputMismatchException ex) {
            ConsoleIOManager.printF("Invalid input, try again.\n");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            ConsoleIOManager.printLine("Invalid input, try again.");
            return readChoice(i, j);
        }
        return choice;
    }



    public static Date readTimeMMdd2(String x) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = x;
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMdd2(x);
        }
    }


    public static void displayTicketPrices(){
        ConsoleIOManager.printF("____________ TICKET PRICE CHART ___________\n");
		ConsoleIOManager.printF("Adult\t\t\t\t%.2f\n",Setting.getStandardAdultPrice());
		ConsoleIOManager.printF("Student\t\t\t\t%.2f\n", Setting.getStandardStudentPrice());
		ConsoleIOManager.printF("Senior\t\t\t\t%.2f\n",Setting.getStandardSeniorPrice());
		ConsoleIOManager.printF("Children\t\t\t%.2f\n", Setting.getStandardChildPrice());
		ConsoleIOManager.printF("Weekend\t\t\t\t%.2f\n", Setting.getStandardWeekendPrice());
		ConsoleIOManager.printF("\n");
    }
}