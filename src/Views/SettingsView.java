package Views;

public class SettingsView {
    public static void DisplayMenu() {
		ConsoleIOManager.ClearScreen();
		ConsoleIOManager.PrintMenu("This is the setting page.",
                "I want to manage ticket price",
                "I want to manage holidays");
        ConsoleIOManager.PrintGoBack();
    }
}