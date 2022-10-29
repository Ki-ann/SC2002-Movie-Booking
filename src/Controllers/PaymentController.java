package Controllers;

import Controllers.Payment.IPayment;

public class PaymentController {

	private double amount;
	private int paymentStatus;
	private int bookingID;
	private int paymentMethod;
	private int timeStamp;

	public void newPaymentTransaction() {
		// TODO - implement Controllers.PaymentController.NewPaymentTransaction
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PaymentMethod
	 */
	public boolean pay(IPayment PaymentMethod) {
		// TODO - implement Controllers.PaymentController.Pay
		throw new UnsupportedOperationException();
	}

}