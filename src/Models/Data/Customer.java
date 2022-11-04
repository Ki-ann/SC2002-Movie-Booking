package Models.Data;

import Models.Data.Enums.AgeClass;

import java.io.Serializable;

public class Customer implements Serializable {

	private String name = "";
	private int phone = 0;
	private String email = "";
	private AgeClass ageClass = AgeClass.ADULT;

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

    public AgeClass getAgeClass() {
        return ageClass;
    }

    public void setAgeClass(AgeClass ageClass) {
        this.ageClass = ageClass;
    }
}