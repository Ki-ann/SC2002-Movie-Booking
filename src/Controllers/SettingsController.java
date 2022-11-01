package Controllers;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Models.Data.*;
import Views.SettingsView;
import java.util.*;
import java.util.Date;
import java.util.HashMap;

public class SettingsController implements INavigation {
<<<<<<< HEAD
	public Setting setting = new Setting();
	private static Scanner sc = new Scanner(System.in);
	public int initialMenuSelection = -1;
	public void Start() {
        SettingsView.DisplayMenu();
        do {
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.ReadInt();
            }
            switch (initialMenuSelection) {
                case 1 -> manageTicketPrice();
                case 2 -> manageHolidays();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    
	}
			
	private void manageTicketPrice() {
		//display original price chart
		displayTicketPrices();
		int choice;
		
		do {
			System.out.println("____________ MANAGE TICKET PRICE __________\n" 
							+ "												\n"
							+ " 1. Edit Standard Ticket Price     	      	\n"
							+ " 0. Go Back      		      	\n"
							+ "____________________________________________	\n"
							+ " Enter your choice here: ");
			
			choice = ConsoleIOManager.ReadInt();
			if (choice < 0 || choice >1) {
                ConsoleIOManager.PrintLine("Invalid input! Please select an item from the menu!");
			}
			else if (choice == 1) {
				ConsoleIOManager.PrintLine("Current standard price: $" + Setting.getStandardPrice());
				ConsoleIOManager.PrintLine("Enter new standard price: ");
				double newSP = sc.nextDouble();
				Setting.setStandardPrice(newSP);
				DataStoreManager.getInstance().AddToStore(setting);
				ConsoleIOManager.PrintLine("\nStandard price updated.");
				displayTicketPrices();

			}
			else{
				NavigationController.getInstance().goBack(1);
				break;
			}		
		} while (choice >= 0 || choice <= 2);
	}
	
	
	//displayTicketPrices() : Displays ticket price chart
	private void displayTicketPrices() {
		ConsoleIOManager.PrintF("____________ TICKET PRICE CHART ___________\n");
		ConsoleIOManager.PrintF("Adult\t\t\t\t%.2f\n",Setting.getStandardAdultPrice());
		ConsoleIOManager.PrintF("Student\t\t\t\t%.2f\n", Setting.getStandardStudentPrice());
		ConsoleIOManager.PrintF("Senior\t\t\t\t%.2f\n",Setting.getStandardSeniorPrice());
		ConsoleIOManager.PrintF("Children\t\t\t%.2f\n", Setting.getStandardChildPrice());
		ConsoleIOManager.PrintF("Weekend\t\t\t\t%.2f\n", Setting.getStandardChildPrice());
		ConsoleIOManager.PrintF("\n");
	}
	private void manageHolidays() {
        SettingsView.printMenu();
        int choice = SettingsView.readChoice(0, 2);

        switch (choice) {
            case 1 -> displayHolidayList(); 
            case 2 -> addHoliday();
            case 0 -> NavigationController.getInstance().goBack(1);
        }

    }

	private void displayHolidayList() {
        ConsoleIOManager.PrintF("Holiday list\n");
        HashMap<String, Holiday> holidayList = Setting.getHolidayList();
        HashMap<Integer, Holiday> searchIndex = new HashMap<>();
        if (holidayList.isEmpty()) {
            ConsoleIOManager.PrintF("No holiday exists\n");
			manageHolidays();
        }
        else {
            int index  = 0;
            for (String date : holidayList.keySet()) {
                ConsoleIOManager.PrintF(++index + ". " + holidayList.get(date)+"\n");
                searchIndex.put(index, holidayList.get(date));
            }
            ConsoleIOManager.PrintF("Press "+(++index) +" go back,else press corresponding holiday index for more details");
			System.out.println();
            int choice = SettingsView.readChoice(1, index);
            if (choice == index) manageHolidays();
            else displayHolidayDetail(searchIndex.get(choice));
        }
    }

	// add holiday and corresponding price
	private void addHoliday() {
        String name;
        Date date;
        double price;

        name = SettingsView.readString("Enter the name of the holiday:").toLowerCase();
        date = SettingsView.readTimeMMdd("Enter the date of the holiday",
                "Format: MM-DD (e.g. 12-25)");
        price = SettingsView.readDouble("Enter the standard price on that day:",
                "e.g. holiday price is 70% of standard price");

        Holiday holiday = new Holiday(name, date, price*0.7);
		Setting.addHoliday(SettingsView.formatTimeMMdd(date), holiday);
        /*try {
            Setting.addHoliday(SettingsView.formatTimeMMdd(date), holiday);
            System.out.println("Successfully added the holiday.");
        } catch (IOException ex) {
            System.out.println("Failed to add the holiday.");
        }
		*/
        displayHolidayList();
=======

	public void start() {
		// TODO - implement Controllers.SettingsController.Start
		throw new UnsupportedOperationException();
>>>>>>> refs/remotes/origin/main
	}

	//display holiday detail and also remove option is provided
	private void displayHolidayDetail(Holiday holiday) {
		Date x;
        ConsoleIOManager.PrintF("Holiday name: "+holiday.getName()+"\n");
        ConsoleIOManager.PrintF(holiday.printDetail(), "");
        if (SettingsView.askConfirm("\nEnter Y if you want to delete the holiday",
                "Enter N to go back:")) {
				x = SettingsView.readTimeMMdd("Enter the time you want to remove",
                "Format: MM-DD (e.g. 12-25)");
            	Setting.getHolidayList().remove(SettingsView.formatTimeMMdd(x));
				try {
					//DataStoreManager.getInstance().AddToStore(Setting.holidayList);
					ConsoleIOManager.PrintF("Successfully deleted the holiday.");
				} catch (Exception e) {
					ConsoleIOManager.PrintF("Failed to delete the holiday.");
				}
            }
			displayHolidayList();
    }
}