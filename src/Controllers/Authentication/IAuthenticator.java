package Controllers.Authentication;

import Models.Data.Admin;

public interface IAuthenticator {

	Admin Login();

	void Logout();

}