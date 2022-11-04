package Controllers.Payment;

/**
 * Simple payment class that always returns unsuccessful payment
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class SimplePaymentAlwaysFailure implements IPayment {
	/**
	 * Logic for processing payment
	 * @return unsuccessful payment
	 */
	public boolean pay() {
		// Always unsuccessfully pay
		return false;
	}

}