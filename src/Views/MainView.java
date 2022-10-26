package Views;

public class MainView {

    public static void DisplayMenu(){
        ConsoleIOManager.PrintMenu("This is the Main page.",
                "Admin Module",
                "Customer Module");
        ConsoleIOManager.PrintGoBack();
    }
}
