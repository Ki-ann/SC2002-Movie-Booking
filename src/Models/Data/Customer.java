package Models.Data;

import Models.Data.Enums.AgeClass;
import java.io.Serializable;

/**
 * Stores the data for a Customer object.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class Customer implements Serializable {

	private String name = "";
	private int phone = 0;
	private String email = "";
	private AgeClass ageClass = AgeClass.ADULT;

	/**
	 * @return Get Customer Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Get Customer phone number
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @return Get Customer email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Get Customer's selected Age Class category
	 */
	public AgeClass getAgeClass() {
		return ageClass;
	}


	/**
	 * @param name Set Customer Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param phone Set Customer phone number
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @param email Set Customer email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param ageClass Set Customer's selected Age Class category
	 */
	public void setAgeClass(AgeClass ageClass) {
		this.ageClass = ageClass;
	}

	/**
	 * Check if the Customer object has a name, phone number, and email all set
	 * @return If name, email and phone are set.
	 */
	public boolean isAllFilled(){
		return !name.isEmpty() && !email.isEmpty() && phone != 0;
	}
}