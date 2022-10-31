package Models.Data;
import java.io.Serializable;

public class Admin implements Serializable {

	private int ID;
	private String userName;
	private String password;

	public Admin(String username,String password){
		this.ID = 0;
		this.userName = username;
		this.password = password;
	}

	public String getUsername(){
		return this.userName;
	}
	public String getPassword(){
		return this.password;
	}
	public int getID(){
		return this.ID;
	}
	public void setID(int id){
		this.ID = id;
	}
}