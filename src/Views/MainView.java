package Views;

public class MainView {

    public static void DisplayMenu(){
        ConsoleIOManager.PrintMenu("Please choose your Option",
                "Admin Module",
                "Customer Module");
        ConsoleIOManager.PrintGoBack();
    }
}
