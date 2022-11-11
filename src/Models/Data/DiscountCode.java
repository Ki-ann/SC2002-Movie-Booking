package Models.Data;

import java.io.Serializable;

/**
 * Stores the data for a Discount Code object.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class DiscountCode implements Serializable {
    private String code;
    private double discountPercentage;
    private boolean isValid;

    /**
     * Constructor for creating a new discount code
     * @param newCode Name of the code
     * @param discountPercentage Percentage of discount in double e.g. 0.3
     */
    public DiscountCode(String newCode, double discountPercentage){
        this.code = newCode;
        this.discountPercentage = discountPercentage;
        this.isValid = true;
    }

    /**
     * @return Get Code String
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Get discount percentage
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * @return is the discount code still valid
     */
    public boolean isValid() {
        return isValid;
    }


    /**
     * @param code Set Code string
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param discountPercentage Set discount percentage
     */
    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * @param valid Sets the validity status of the code
     */
    public void setValid(boolean valid) {
        isValid = valid;
    }
}
