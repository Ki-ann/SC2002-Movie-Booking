package Models.Data;

import java.io.Serializable;

public class Customer implements Serializable {

	private String name = "";
	private int phone = 0;
	private String email = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAllFilled(){
		return !name.isEmpty() && !email.isEmpty() && phone != 0;
	}
}