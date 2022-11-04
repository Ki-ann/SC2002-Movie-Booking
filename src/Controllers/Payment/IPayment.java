package Controllers.Payment;

/**
 * Payment controller interface abstraction for payment method implementations
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 * @see Controllers.PaymentController
 */
public interface IPayment {
	boolean pay();
}