package Views;

import Models.Data.BookingTicket;
import Models.Data.Customer;

import java.text.DecimalFormat;
import java.util.List;

/**
 * BookingHistoryView class used by BookingHistoryController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @see Controllers.BookingHistoryController
 * @see Views.ConsoleIOManager
 * @since 2022-11-01
 */
public class BookingHistoryView {

    /**
     * Print default title display when loaded in.
     */
    public static void displayMenu() {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Booking History Viewer");
    }

    /**
     * Print for prompt to get customer email.
     */
    public static void printGetCustomer() {
        ConsoleIOManager.printLine("Please enter the email you wish to check booking history for:");
    }

    /**
     * Print error message when no booking history is found with the given email input.
     */
    public static void printNoCustomerFound() {
        ConsoleIOManager.printLine("No booking history was found for the email requested.");
        ConsoleIOManager.printRetry();
    }

    /**
     * Prints a nicely formatted table of all booking ticket histories that are tagged to the customer.
     *
     * @param bookingTicketList list of BookingTickets that are tagged to the requested customer.
     * @param requestedCustomer the customer that the User requested to check history for.
     */
    public static void printBookingHistoryList(List<BookingTicket> bookingTicketList, Customer requestedCustomer) {
        ConsoleIOManager.printMenu(String.format("Booking History list for:\nName:%s Email:%s", requestedCustomer.getName(), requestedCustomer.getEmail()));
        DecimalFormat twoPlaces = new DecimalFormat("00.00");
        ConsoleIOManager.printMenu(String.format("%-5s | %-35s| %-6s| %-15s| %-15s| %-10s | %-5s\n",
                "Index", "Movie", "Seat", "Cinema", "Screening Time", "Screening Date", "Price"));
        for (int i = 0; i < bookingTicketList.size(); ++i) {
            BookingTicket ticket = bookingTicketList.get(i);
            ConsoleIOManager.printF("[    %-3d | %-35s  %-6s  %-15s  %-15s  %td/%<tm/%<ty       %8s  ]\n",
                    i + 1,
                    ticket.getSelectedMovie().getName() + " **" + ticket.getSelectedMovie().getMovieStatus() + "**",
                    ticket.getSelectedSeat().getSeatString(),
                    ticket.getSelectedCineplex().getName() + " - " + ticket.getSelectedCinema().getName(),
                    ticket.getSelectedScreening().getShowTime().getTimeOfMovie() + " - " + ticket.getSelectedScreening().getShowTime().getTimeOfMovie().plus(ticket.getSelectedMovie().getDuration()).toString(),
                    ticket.getSelectedScreening().getShowTime().getDateOfMovie(),
                    "$" + twoPlaces.format(ticket.getPrice()));
        }
    }
}
