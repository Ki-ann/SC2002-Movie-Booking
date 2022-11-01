package Controllers.Authentication;

import Models.Data.Admin;

public interface IAuthenticator {

	Admin login();

	void logout();

}