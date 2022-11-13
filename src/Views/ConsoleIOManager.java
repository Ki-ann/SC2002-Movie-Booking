package Views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.time.LocalTime;

/**
 * Manager class to store an instance of Scanner and interface with console read and write.
 * <br>Wrappers allow for easy extension of logging if needed.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class ConsoleIOManager {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints 50 new lines to simulate a screen clear in console.
     */
    public static void clearScreen() {
        printLine(System.lineSeparator().repeat(2));
    }

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
     * Prints a choice menu with the given title and options. Usually followed by a readInt(). Max Length 100 words
     *
     * @param title       Title of the choice menu.
     * @param menuOptions Takes in a String Array of choices to be printed out sequentially.
     */
    public static void printMenu(String title, String... menuOptions) {
        printMenu(title, 100, menuOptions);
    }

    /**
     * Prints a choice menu with the given title and options. Usually followed by a readInt(). Customizable max Length
     *
     * @param title       Title of the choice menu.
     * @param maxLength   Max word length of the menu
     * @param menuOptions Takes in a String Array of choices to be printed out sequentially.
     */
    public static void printMenu(String title, int maxLength, String... menuOptions) {
        String choiceLine = "Please enter your choice:";
        // Get the largest per-line string length from either Title or menuOptions
        int largestMenuLength = Stream.concat(Arrays.stream(new String[]{title, choiceLine}), Arrays.stream(menuOptions))
                .flatMap(string -> Arrays.stream(string.split("\\r?\\n")))
                .toList().stream()
                // map all lengths to a stream
                .map(String::length)
                // find the largest string length
                .max(Comparator.comparingInt(s -> s))
                .orElse(choiceLine.length()); // Default to choiceLine Length
        largestMenuLength = Math.min(largestMenuLength, maxLength) + 10;
        // Get the length of a centered title string
        int centeredTitleLength = centerString(largestMenuLength, title.split("\\r?\\n")[0].trim(), ' ').length();
        // Split string by new line
        String[] centeredTitleArray = Arrays.stream(title.split("\\r?\\n"))
                // Split string again if it is longer than the max length (Word wrap)
                .flatMap(splitLine -> (splitLine.length() > maxLength ?
                        wordWrapper(splitLine, maxLength).stream()  // Split string into multiple smaller strings
                        : Arrays.stream(new String[]{splitLine}))) // Only 1 line string found
                .toList().stream()
                // Center all splits
                .map(splitTitle -> centerString(centeredTitleLength, splitTitle, ' '))
                .toArray(String[]::new);

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
     * Splits a string into several Strings if they exceed a max length while keeping word boundaries
     * @param splitLine the string to check for word wrapping
     * @param maxLength max length to check for word wrapping
     * @return an ArrayList of Strings separated according to max length
     */
    private static ArrayList<String> wordWrapper(String splitLine, int maxLength) {
        ArrayList<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile(String.format("(.{1,%d}(?:\\s|$))|(.{0,%<d})", maxLength), Pattern.DOTALL);
        Matcher regexMatcher = regex.matcher(splitLine);
        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        return matchList;
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
        return String.format("%s", String.valueOf(fillChar).repeat(paddingSize) + s + String.valueOf(fillChar).repeat((width - s.length()) % 2 == 0 ? paddingSize : paddingSize + 1));

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
            int input = Integer.parseInt(scanner.nextLine());

            return input;
        } catch (Exception exception) {
            //exception.printStackTrace();
            printLine("Invalid input! Please try again.");
        }
        // Try again
        return readInt();
    }

    /**
     * Wrapper for scanner.nextDouble() to allow for a shared instance of a single scanner class.
     * <br>Retries until given a valid integer. Clears the buffer after a successful double read.
     *
     * @return Inputted double
     */
    public static double readDouble() {
        double output;
        try {
            output = Double.parseDouble(scanner.nextLine());
            return output;
        } catch (InputMismatchException ex) {
            ConsoleIOManager.printLine("Invalid input, try again.");
            return readDouble();
        }
    }

    /**
     * Wrapper for scanner.nextLine() to allow for a shared instance of a single scanner class.
     * <br>Retries until given a valid string.
     *
     * @return Inputted String
     */
    public static String readString() {
        try {
            String str = scanner.nextLine();
            if(str.isEmpty()){
                throw new RuntimeException();
            }
            return str;
        } catch (Exception exception) {
            printLine("Invalid input! Please try again.");
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
        }
        // Try again
        return readConfirm();
    }

    /**
     * Reads for user inputted Date of MM/dd and tries to parse it
     * @return LocalDate instance of inputted Date
     */
    public static LocalDate readTimeMMdd() {
        return readTimeMMdd("");
    }

    /**
     * Parses an inputted Date string
     * @param x input Date string
     * @return LocalDate instance of inputted Date
     */
    public static LocalDate readTimeMMdd(String x) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            String input = x;
            if (input.equals("")) {
                input = ConsoleIOManager.readString();
            }
            input = String.format("%d/%s", LocalDate.now().getYear(), input);
            return LocalDate.parse(input, dateTimeFormatter);
        } catch (Exception ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMdd(x);
        }
    }

    /**
     * Reads for user inputted time of HH/MM and tries to parse it
     * @return LocalTime instance of inputted time
     */
    public static LocalTime readTimeHHMM() {
        return readTimeHHMM("");
    }

    /**
     * Parses an inputted time string
     * @param x input time string
     * @return LocalTime instance of inputted time
     */
    public static LocalTime readTimeHHMM(String x) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        try {
            String input = x;
            if (input.equals("")) {
                input = ConsoleIOManager.readString();
            }
            return LocalTime.parse(input, f);
        } catch (Exception ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeHHMM(x);
        }
    }
}