package Controllers;

import Controllers.Authentication.AuthController;
import Controllers.Authentication.IAuthenticator;
import Models.Data.Admin;
import Views.ConsoleIOManager;
import Views.LoginView;

/**
 * LoginController is a Navigation that manages the logic and flow for login in of admin users
 *
 * @author Shreyas Pramod Hegde
 * @version 1.0
 * @since 2022-11-11
 */
public class LoginController implements INavigation{

    private IAuthenticator authenticator = new AuthController();

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start(){
        Admin admin = null;
        while(admin==null){
            LoginView.DisplayLoginMenu();
            switch(ConsoleIOManager.readInt()){
                case 1-> admin = authenticator.login();
                case 0-> NavigationController.getInstance().goBack();
            }
        }
        NavigationController.getInstance().load(new AdminController());
    }
}
