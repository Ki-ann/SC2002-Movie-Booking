package Views;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleIOManager {

    private static Scanner scanner = new Scanner(System.in);

    public static void ClearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    public static void PrintLine(String line) {
        System.out.println(line);
    }

    public static void PrintF(String line, Object... args) {
        System.out.printf(line, args);
    }

    public static void PrintMenu(String title, String... menuOptions) {
        PrintLine(title);
        PrintLine("Please enter your choice:");

        for (int i = 0; i < menuOptions.length; ++i) {
            PrintF("[%d] %s\n", i + 1, menuOptions[i]);
        }
    }

    public static void PrintGoBack() {
        PrintF("[%d] %s\n", 0, "Go back");
    }

    public static void PrintConfirm() {
        PrintLine("[Y/N] Confirm your choice?");
    }

    public static int ReadInt() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            return input;
        } catch (Exception exception) {
            //exception.printStackTrace();
            PrintLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return ReadInt();
    }

    public static String ReadString() {
        try {
            return scanner.nextLine();
        } catch (Exception exception) {
            PrintLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return ReadString();
    }

    public static boolean ReadConfirm() {
        try {
            while (true) {
                String input = scanner.nextLine();

                if (input.toUpperCase(Locale.ROOT).equals("Y") || input.toUpperCase(Locale.ROOT).equals("N")) {
                    return input.equals("Y");
                } else {
                    PrintLine("Invalid input! Please try again.");
                    break;
                }
            }
        } catch (Exception exception) {
            PrintLine("Invalid input! Please try again.");
            scanner.nextLine(); // Clear buffer
        }
        // Try again
        return ReadConfirm();
    }

}