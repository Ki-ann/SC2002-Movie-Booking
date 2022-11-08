package Controllers;

import Controllers.Authentication.AuthController;
import Controllers.Authentication.IAuthenticator;
import Models.Data.Admin;
import Models.Data.Setting;
import Views.ConsoleIOManager;
import Views.LoginView;

public class LoginController implements INavigation{
    private IAuthenticator authenticator = new AuthController();

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
