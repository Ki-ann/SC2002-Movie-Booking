package Controllers.Payment;

/**
 * Simple payment class that always returns successful payment
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class SimplePayment implements IPayment {
	/**
	 * Logic for processing payment
	 * @return successful payment
	 */
	public boolean pay() {
		// Always successfully pay
		return true;
	}

}