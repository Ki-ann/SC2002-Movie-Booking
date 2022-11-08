package Views;

/**
 * MainView class used by MainController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-28
 * @see Controllers.MainController
 * @see Views.ConsoleIOManager
 */
public class MainView {

    /**
     * Prints the selection menu
     */
    public static void displayMenu(){
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu(
                       "888b     d888  .d88888b.  888888b.   888      8888888 888b     d888        d8888 \n" +
                               "8888b   d8888 d88P\" \"Y88b 888  \"88b  888        888   8888b   d8888       d88888 \n" +
                               "88888b.d88888 888     888 888  .88P  888        888   88888b.d88888      d88P888 \n" +
                               "888Y88888P888 888     888 8888888K.  888        888   888Y88888P888     d88P 888 \n" +
                               "888 Y888P 888 888     888 888  \"Y88b 888        888   888 Y888P 888    d88P  888 \n" +
                               "888  Y8P  888 888     888 888    888 888        888   888  Y8P  888   d88P   888 \n" +
                               "888   \"   888 Y88b. .d88P 888   d88P 888        888   888   \"   888  d8888888888 \n" +
                               "888       888  \"Y88888P\"  8888888P\"  88888888 8888888 888       888 d88P     888 \n\n" +
                "MOvie Booking and LIsting Management Application\n",
                "Admin Module",
                "Customer Module");
    }
}
