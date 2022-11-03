package Views;

import java.io.Console;

public class AdminView {
    /**
     * Print Menu to display different functionalities of Admin
     */
    public static void DisplayMenu() {
        ConsoleIOManager.printMenu("This is the admin page","Edit Movies","Edit Settings","Edit Cineplexes");
        ConsoleIOManager.printF("[%d] %s\n", 0, "Logout");
    }
    /**
     * Print a message for login failure
     */
    public static void printLoginFailed(){
        ConsoleIOManager.printLine("Wrong username or password!");
    }
    /**
     * Print a message for successful loin
     */
    public static void printLoginSuccess(){
        ConsoleIOManager.printLine("Login successful");
    }

}