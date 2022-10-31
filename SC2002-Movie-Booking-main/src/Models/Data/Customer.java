package Models.Data;

import java.io.Serializable;

public class Customer implements Serializable {

	private String Name = "";
	private int Phone = 0;
	private String Email = "";

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean AllFilled(){
		return !Name.isEmpty() && !Email.isEmpty() && Phone != 0;
	}
}