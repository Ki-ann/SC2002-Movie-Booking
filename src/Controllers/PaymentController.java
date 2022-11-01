package Controllers;

import Controllers.Payment.IPayment;
import Controllers.Payment.SimplePayment;
import Controllers.Payment.SimplePaymentAlwaysFailure;
import Models.Data.BookingTicket;
import Models.Data.DiscountCode;
import Models.Data.Enums.PaymentMethod;
import Models.Data.Movie;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.PaymentView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * PaymentController that handles the logic for processing payments
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-01
 */
public class PaymentController {

    /**
     * Stores the list of PaymentMethods Implementations available.
     */
    private final ArrayList<IPayment> paymentMethods = new ArrayList<>() {
        {
            add(new SimplePayment());
            add(new SimplePaymentAlwaysFailure());
        }
    };

    /**
     * Main logic for creating a new payment transaction.
     *
     * @param bookingTicket current booking session.
     * @return Transaction ID of successful transaction, or null if cancelled.
     */
    public String newPaymentTransaction(BookingTicket bookingTicket) {
        PaymentView.printPaymentStart();
        String newTID;
        // Does user want to input a discount code?
        if (ConsoleIOManager.readConfirm()) {
            // Get Discount Code
            getDiscountCode(bookingTicket);
        }

        // Each payment will have a transaction id (TID). The TID is of the format
        // XXXYYYYMMDDhhomm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
        // Set TID
        newTID = String.format("%s%ty%<tm%<td%tH%<tM%<tS", bookingTicket.getSelectedCinema().getName(), LocalDate.now(), LocalTime.now());

        // Pay
        do {
            if (this.pay(getUserSelectedPaymentMethod())) {
                return newTID;
            } else {
                PaymentView.printPaymentFailed();
                // Does user wish to retry payment?
                if (!ConsoleIOManager.readConfirm()) {
                    return null;
                }
            }
        } while (true);
    }

    /**
     * Loop for getting user input for a valid discount code.
     *
     * @param bookingTicket booking session ticket to apply new discounted price to.
     */
    private void getDiscountCode(BookingTicket bookingTicket) {
        do {
            PaymentView.printDiscountMessage();
            var inputCode = ConsoleIOManager.readString();
            var discountCodeList = getDiscountCodes();
            if (discountCodeList.size() > 0) {
                var discountCodeOptional = discountCodeList.stream().filter(code -> code.getCode().equals(inputCode)).findFirst();

                // Valid discount code applied
                if (discountCodeOptional.isPresent() && discountCodeOptional.get().isValid()) {
                    var discountCode = discountCodeOptional.get();
                    var oldPrice = bookingTicket.getPrice();
                    var newPrice = bookingTicket.getPrice() * (1.0 - discountCode.getDiscountPercentage());
                    PaymentView.printDiscountedPrice(oldPrice, newPrice, (int) (discountCode.getDiscountPercentage() * 100));
                    bookingTicket.setPrice(newPrice);
                    break;
                }
            }
            // Failed to find discount code
            PaymentView.printNoDiscountMessage();
            // Does user want to retry discount code input?
            if (!ConsoleIOManager.readConfirm()) {
                break;
            }
        } while (true);
    }

    /**
     * Get User selection for payment method.
     *
     * @return User's selected payment method.
     */
    private IPayment getUserSelectedPaymentMethod() {
        // Allow for multiple payment selection int the future
        PaymentView.printPaymentMenu(paymentMethods);
        IPayment selectedPayment;
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > paymentMethods.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                selectedPayment = paymentMethods.get(input - 1);
                break;
            }
        } while (true);
        return selectedPayment;
    }

    /**
     * Wrapper to call IPayment.pay().
     *
     * @param paymentMethodImpl the IPayment Implementation to run.
     * @see IPayment
     */
    public boolean pay(IPayment paymentMethodImpl) {
        if (paymentMethodImpl == null) {
            return false;
        }
        return paymentMethodImpl.pay();
    }

    /**
     * Retrieves the Discount Code list from DataStore
     *
     * @return Arraylist of DiscountCode in the DataStore
     * @see DataStoreManager
     */
    private ArrayList<DiscountCode> getDiscountCodes() {
        return DataStoreManager.getInstance().getStore(DiscountCode.class);
    }
}