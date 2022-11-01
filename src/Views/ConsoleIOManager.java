package Views;

import java.util.Locale;
import java.util.Scanner;

/**
 * Manager class to store an instance of Scanner and interface with console read and write.
 * <br>Wrappers allow for easy extension of logging if needed.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-29
 */
public class ConsoleIOManager {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Wrapper for System.out.println() to output an empty \n to console.
     */
    public static void printLine() {
        System.out.println();
    }

    /**
     * Overloaded Wrapper for System.out.println() to output a given String with \n appended.
     * @param line the String to print.
     */
    public static void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Wrapper for System.out.printf to output formatted Strings.
     * @param line formatted string to print.
     * @param args additional arguments to be referenced by the formatter specifiers.
     */
    public static void printF(String line, Object... args) {
        System.out.printf(line, args);
    }

    /**
     * Prints 50 new lines to simulate a screen clear in console.
     */
    public static void clearScreen() {
        printLine(System.lineSeparator().repeat(50));
    }

    /**
     * Prints a choice menu with the given title and options. Usually followed by a readInt().
     *
     * @param title Title of the choice menu.
     * @param menuOptions Takes in a String Array of choices to be printed out sequentially.
     */
    public static void printMenu(String title, String... menuOptions) {
        printLine(title);
        printLine("Please enter your choice:");

        for (int i = 0; i < menuOptions.length; ++i) {
            printF("[%d] %s\n", i + 1, menuOptions[i]);
        }
    }

    /**
     * To be used after a printMenu() method call to append a "Go Back" option.
     */
    public static void printGoBack() {
        printF("[%d] %s\n", 0, "Go back");
    }

    /**
     * Prints a template Yes/No confirmation message.
     */
    public static void printConfirm() {
        printLine("[Y/N] Confirm your choice?");
    }

    /**
     * Wrapper for scanner.nextInt() to allow for a shared instance of a single scanner class.
     * <br>Retries until given a valid integer. Clears the buffer after a successful integer read.
     *
     * @return Inputted integer
     */
    public static int readInt() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            return input;
        } catch (Exception exception) {
            //exception.printStackTrace();
            printLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return readInt();
    }

    /**
     * Wrapper for scanner.nextLine() to allow for a shared instance of a single scanner class.
     * <br>Retries until given a valid string.
     * @return Inputted String
     */
    public static String readString() {
        try {
            return scanner.nextLine();
        } catch (Exception exception) {
            printLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return readString();
    }

    /**
     * Reads scanner.nextLine() until given a valid input.
     * Valid inputs: "Y", "y", "N", "n"
     * @return Yes = True, No = False
     */
    public static boolean readConfirm() {
        try {
            String input = scanner.nextLine();

            if (input.toUpperCase(Locale.ROOT).equals("Y") || input.toUpperCase(Locale.ROOT).equals("N")) {
                return input.equals("Y");
            } else {
                printLine("Invalid input! Please try again.");
            }
        } catch (Exception exception) {
            printLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return readConfirm();
    }

}