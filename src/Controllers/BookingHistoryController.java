package Controllers;

import Models.Data.BookingTicket;
import Models.Data.Customer;
import Models.DataStoreManager;
import Views.BookingHistoryView;
import Views.ConsoleIOManager;

import java.util.ArrayList;

/**
 * BookingController is a Navigation that manages the logic and flow for booking of movie tickets
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-01
 */
public class BookingHistoryController implements INavigation {

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        BookingHistoryView.displayMenu();
        viewBookingHistory();
    }

    /**
     * The function that handles the flow of viewing a User's booking history
     */
    private void viewBookingHistory() {
        var bookingList = getBookingHistoryList();

        if (bookingList != null) {
            var customer = getCustomer(bookingList);

            if (customer == null) {
                // No customer found/Cancelled
                NavigationController.getInstance().goBack();
            }

            var filteredBookingList = bookingList.stream().filter(booking -> booking.getCustomer().getEmail().equals(customer.getEmail().trim())).toList();
            BookingHistoryView.printBookingHistoryList(filteredBookingList, customer);

        }else{
            BookingHistoryView.printNoBookingHistoryFound();
        }

        ConsoleIOManager.printGoBack();
        // Go back on user input
        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack();
                break;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * Requests for User to input an Email string to check for against the BookingTicketList.
     *
     * @param bookingList The BookingTicket list to check with customer Email.
     * @return Customer that has at least one BookingTicket tagged or null if none matches.
     */
    private Customer getCustomer(ArrayList<BookingTicket> bookingList) {
        do {
            BookingHistoryView.printGetCustomer();
            String input = ConsoleIOManager.readString();
            var customer = bookingList.stream().filter(ticket -> ticket.getCustomer().getEmail().equals(input.trim())).findAny();

            if (customer.isEmpty()) {
                //No customer found, Retry?
                BookingHistoryView.printNoCustomerFound();
                if (ConsoleIOManager.readConfirm()) {
                    continue;
                }
                return null;
            } else {
                return customer.get().getCustomer();
            }
        } while (true);
    }

    /**
     * Retrieves the booking list from DataStore
     *
     * @return Arraylist of BookingTickets in the DataStore
     * @see DataStoreManager
     */
    private ArrayList<BookingTicket> getBookingHistoryList() {
        return DataStoreManager.getInstance().getStore(BookingTicket.class);
    }
}
