package Views;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readChoice(i, j);
        }
        return choice;
    }
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static Date readTimeMMdd(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMdd(message);
        }
    }
    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MMMM, dd").format(time);
    }
    public static double readDouble(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        double output;

        try {
            output = sc.nextDouble();
            return output;
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readDouble(message);
        }
    }
    public static boolean askConfirm(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        if (sc.next().toUpperCase().equals("Y")) return true;
        else return false;
    }
}