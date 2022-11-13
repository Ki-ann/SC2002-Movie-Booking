package Views;

import Models.Data.BookingTicket;
import Models.Data.Customer;
import Models.Data.Seat;

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
        ConsoleIOManager.printMenu(String.format("%-5s | %-50s| %-8s| %-10s | %-20s| %-15s| %-15s | %-8s| %-16s  \n",
                "Index", "Movie", "Seat", "Type", "Cinema", "Screening Time", "Screening Date", "Price", "Transaction ID"), 200);
        for (int i = 0; i < bookingTicketList.size(); ++i) {
            BookingTicket ticket = bookingTicketList.get(i);

            for(Seat seat : ticket.getSelectedSeats()) {
                ConsoleIOManager.printF("[       %-3d | %-50s| %-8s| %-10s | %-20s| %-15s| %-2td/%<-2tm/%<-2ty        | %-8s| %-16s        ]\n",
                        i + 1,
                        ticket.getSelectedMovie().getName() + " **" + ticket.getSelectedMovie().getMovieStatus() + "**",
                        seat.getSeatString(),
                        seat.getSeatType(),
                        ticket.getSelectedCineplex().getName() + " - " + ticket.getSelectedCinema().getName(),
                        ticket.getSelectedScreening().getShowTime().getTimeOfMovie() + " - " + ticket.getSelectedScreening().getShowTime().getTimeOfMovie().plus(ticket.getSelectedMovie().getDuration()).toString(),
                        ticket.getSelectedScreening().getShowTime().getDateOfMovie(),
                        "$" + twoPlaces.format(ticket.getPrice()),
                        ticket.getBookingID());
            }
        }
    }

    /**
     * Prints a message when no booking history is found at all
     */
    public static void printNoBookingHistoryFound(){
        ConsoleIOManager.printLine("No booking history was found in the system");
    }
}
