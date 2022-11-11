package Models.Data;
import java.io.Serializable;

/**
 * Admin data class
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 */
public class Admin implements Serializable {

	private String userName;
	private String password;

	/**
	 * Constructor for creating an admin
	 * @param username Username String of the admin account
	 * @param password Password String of the admin account
	 */
	public Admin(String username,String password){
		this.userName = username;
		this.password = password;
	}

	/**
	 * @return Username String of the admin account
	 */
	public String getUsername(){
		return this.userName;
	}

	/**
	 * @return Password String of the admin account
	 */
	public String getPassword(){
		return this.password;
	}
}