package Controllers.Payment;

public class SimplePaymentAlwaysFailure implements IPayment {
	public boolean pay() {
		// Always unsuccessfully pay
		return false;
	}

}