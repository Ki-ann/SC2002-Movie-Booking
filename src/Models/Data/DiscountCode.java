package Models.Data;

import java.io.Serializable;

public class DiscountCode implements Serializable {
    private String code;
    private double discountPercentage;
    private boolean isValid;

    public DiscountCode(String newCode, double discountPercentage){
        this.code = newCode;
        this.discountPercentage = discountPercentage;
        this.isValid = true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
