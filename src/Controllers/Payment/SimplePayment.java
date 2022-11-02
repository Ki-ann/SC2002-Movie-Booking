package Controllers.Payment;

public class SimplePayment implements IPayment {
	public boolean pay() {
		// Always successfully pay
		return true;
	}

}