package Views;

import Controllers.Payment.IPayment;
import Models.Data.Movie;

import java.util.ArrayList;

/**
 * PaymentView class used by PaymentController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @see Controllers.PaymentController
 * @see Views.ConsoleIOManager
 * @since 2022-11-01
 */
public class PaymentView {

    /**
     * Prints a message when entering discount code.
     */
    public static void printDiscountMessage() {
        ConsoleIOManager.printLine("Please enter your discount code.");
    }

    /**
     * Prints a message when no discounts were found for the requested code
     */
    public static void printNoDiscountMessage() {
        ConsoleIOManager.printLine("No valid discounts found.");
        ConsoleIOManager.printRetry();
    }

    /**
     * Prints when discount code is successfully applied and displays the price difference.
     *
     * @param oldPrice           Old price of the booking before discount is applied.
     * @param newPrice           New price of the booking after discount is applied.
     * @param discountPercentage Percentage of discount in decimal. 15% -> 0.15.
     */
    public static void printDiscountedPrice(double oldPrice, double newPrice, int discountPercentage) {
        ConsoleIOManager.printLine("Discount code applied successfully!");
        ConsoleIOManager.printF("%d%% OFF\n", discountPercentage);
        ConsoleIOManager.printF("Original Price: $%.2f --> Discounted Price: $%.2f\n", oldPrice, newPrice);
    }

    /**
     * Prints a message for payment failure
     */
    public static void printPaymentFailed() {
        ConsoleIOManager.printLine("Payment failed.");
        ConsoleIOManager.printRetry();
    }

    /**
     * Prints a message when starting payment
     */
    public static void printPaymentStart() {
        //ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu("Starting Payment Transaction");
        ConsoleIOManager.printLine("[Y/N] Do you wish to enter a discount code?");
    }

    /**
     * Prints a selection menu for all Interface implementations of IPayment
     *
     * @param paymentList List of available IPayment implementations for selection
     * @see IPayment
     */
    public static void printPaymentMenu(ArrayList<IPayment> paymentList) {
        String[] paymentStringList = paymentList.stream()
                .map(iPayment -> iPayment.getClass().getSimpleName())
                .toArray(String[]::new);

        ConsoleIOManager.printMenu("Currently supported payment methods",
                paymentStringList);
        ConsoleIOManager.printGoBack();
    }
}
