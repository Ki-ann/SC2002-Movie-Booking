package Views;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.text.ParseException;
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
     *
     * @param line the String to print.
     */
    public static void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Wrapper for System.out.printf to output formatted Strings.
     *
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
     * @param title       Title of the choice menu.
     * @param menuOptions Takes in a String Array of choices to be printed out sequentially.
     */
    public static void printMenu(String title, String... menuOptions) {
        String choiceLine = "Please enter your choice:";
        // Get the largest length string from either Title or menuOptions
        int largestMenuLength = Arrays.stream(menuOptions).map(string -> string.split("\\r?\\n")[0].length()).max(Comparator.comparingInt(s -> s)).orElse(choiceLine.length()) + 30;
        // Get the length of a centered title string
        int centeredTitleLength = centerString(largestMenuLength, title.trim(), ' ').length();
        // Split the title if given a new line and center all splits
        String[] centeredTitleArray = Arrays.stream(title.split("\\r?\\n")).map(splitTitle -> centerString(centeredTitleLength, splitTitle.trim(), ' ')).toArray(String[]::new);

        //====Print Title
        // Top border
        printF("|%s|\n", "=".repeat(centeredTitleLength));
        // Print all centered split titles
        for (String centeredTitle : centeredTitleArray) {
            printF("|%s|\n", centeredTitle);
        }
        // Bottom border
        printF("|%s|\n", "=".repeat(centeredTitleLength));

        // If given any menu options
        if (menuOptions.length > 0) {
            printLine(choiceLine);
            for (int i = 0; i < menuOptions.length; ++i) {
                printF("[%d] %s\n", i + 1, menuOptions[i]);
            }
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
     * Prints a template Yes/No retry message.
     */
    public static void printRetry() {
        printLine("[Y/N] Do you wish to retry?");
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
     *
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
     *
     * @return Yes = True, No = False
     */
    public static boolean readConfirm() {
        try {
            String input = scanner.nextLine();

            if (input.toUpperCase(Locale.ROOT).equals("Y") || input.toUpperCase(Locale.ROOT).equals("N")) {
                return input.toUpperCase(Locale.ROOT).equals("Y");
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

    /**
     * Helper function to center a string with paddings.
     *
     * @param width Final width required for the return string to be.
     * @param s     The string to be centered.
     * @return a centered string with paddings on the left and right of the given string to reach the required width.
     */
    public static String centerString(int width, String s, char fillChar) {
        int paddingSize = (width - s.length()) / 2;
        if (paddingSize <= 0)
            paddingSize = 2; // Default padding
        // If string is of odd length, append an extra character at the end.
        return String.format("%s", String.valueOf(fillChar).repeat(paddingSize) + s + String.valueOf(fillChar).repeat(s.length() % 2 == 0 ? paddingSize : paddingSize + 1));

    }

    //return string 
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        return scanner.nextLine();
    }
    
    public static Date readTimeMMdd(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = ConsoleIOManager.readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            ConsoleIOManager.printLine("Wrong format. Try again.");
            return readTimeMMdd(message);
        }
    }
    //retun newformat of date
    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MMMM, dd").format(time);
    }

    public static double readDouble(String... message) {
        for (String m : message) System.out.println(m);
        double output;
        try {
            output = scanner.nextDouble();
            return output;
        } catch (InputMismatchException ex) {
            ConsoleIOManager.printLine("Invalid input, try again.");
            scanner.nextLine();  // flush scanner
            return readDouble(message);
        }
    }

    public static boolean askConfirm(String... message) {
        for (String m : message) System.out.println(m);
        if (scanner.next().toUpperCase().equals("Y")) {scanner.nextLine();
            return true;}
        else return false;
    }

    /**
     * This method is to read serialized object from a file.
     * @param filename the file address to read from
     * @param data the data to read from the file
     * @throws IOException when the file address is invalid
     */
    public static Object readSerializedObject(String filename) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e) {
            return null;
        }

        return data;
    }

    /**
     * This method is to write serialized object to a file.
     * @param filename the file address to write to
     * @param data the data to be written to the file
     * @throws IOException when the file address is invalid
     */
    public static void writeSerializedObject(String filename, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filename);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}