package Views;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleIOManager {

    private static Scanner scanner = new Scanner(System.in);

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
			PrintF("[%d] %s.\n", i + 1, menuOptions[i]);
		}

	}

	public static void PrintGoBack() {
		PrintF("[%d] %s.\n", 0, "Go back");
	}

    public static int ReadInt(){
		try{
			return scanner.nextInt();
		}catch (Exception exception){
			//exception.printStackTrace();
			PrintLine("Invalid input! Please try again.");
			scanner.next(); // Clear buffer
		}
		// Try again
		return ReadInt();
	}

}