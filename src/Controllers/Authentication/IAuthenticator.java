package Controllers.Authentication;

import Models.Data.Admin;

/**
 * IAuthentication interface that abstracts the authentication logic
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 */
public interface IAuthenticator {

	Admin login();

	void logout();

}