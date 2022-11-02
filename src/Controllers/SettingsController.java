package Controllers;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Models.Data.*;
import Views.SettingsView;
import java.util.Date;
import java.util.HashMap;

public class SettingsController implements INavigation  {
	public Setting setting = new Setting();
	public int initialMenuSelection = -1;
	@Override
	public void start() {
        Setting.initialize();
        SettingsView.DisplayMenu();
        do {
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.readInt();
            }
            switch (initialMenuSelection) {
                case 1 -> manageTicketPrice();
                case 2 -> manageHolidays();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    
	}
			
	private void manageTicketPrice() {
		//display original price chart
		SettingsView.displayTicketPrices();
		int choice;
		do {
			SettingsView.ManageTicketPrice();
			choice = ConsoleIOManager.readInt();
			if (choice < 0 || choice >1) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
			}
			else if (choice == 1) {
				ConsoleIOManager.printLine("Current standard price: $" + Setting.getStandardPrice());
				double newSP= ConsoleIOManager.readDouble("Enter new standard price: ");
				
				Setting.setStandardPrice(newSP);
				DataStoreManager.getInstance().addToStore(setting);
				ConsoleIOManager.printLine("\nStandard price updated.");
				SettingsView.displayTicketPrices();

			}
			else{
				NavigationController.getInstance().goBack(1);
				break;
			}		
		} while (choice >= 0 || choice <= 2);
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
        ConsoleIOManager.printF("Holiday list option\n");
        HashMap<String, Holiday> holidayList = Setting.getHolidayList();
        HashMap<Integer, Holiday> searchIndex = new HashMap<>();
        if (holidayList.isEmpty()) {
            ConsoleIOManager.printF("No holiday exists\n");
			manageHolidays();
        }
        else {
            int index  = 0;
            for (String date : holidayList.keySet()) {
                ConsoleIOManager.printF("[" + ++index + "]" + holidayList.get(date)+" - more details"+"\n");
                searchIndex.put(index, holidayList.get(date));
            }
			ConsoleIOManager.printGoBack();
            int choice = SettingsView.readChoice(0, index);
            if (choice == 0) manageHolidays();
            else displayHolidayDetail(searchIndex.get(choice));
        }
    }

	// add holiday and corresponding price
	private void addHoliday() {
        String name;
        Date date;
        double price;

        name = ConsoleIOManager.readString("Enter the name of the holiday:").toLowerCase();
        date = ConsoleIOManager.readTimeMMdd("Enter the date of the holiday",
                "Format: MM-DD (e.g. 12-25)");
        price = ConsoleIOManager.readDouble("Enter the standard price on that day:",
                "e.g. holiday price is 70% of standard price");

        Holiday holiday = new Holiday(name, date, price*0.7);
		Setting.addHoliday(ConsoleIOManager	.formatTimeMMdd(date), holiday);
        /*try {
            Setting.addHoliday(SettingsView.formatTimeMMdd(date), holiday);
            System.out.println("Successfully added the holiday.");
        } catch (IOException ex) {
            System.out.println("Failed to add the holiday.");
        }
		*/
        displayHolidayList();
	}

	//display holiday detail and also remove option is provided
	private void displayHolidayDetail(Holiday holiday) {
		Date x;
        ConsoleIOManager.printF("Holiday name: "+holiday.getName()+"\n");
        ConsoleIOManager.printF(holiday.printDetail(), "");
        if (ConsoleIOManager.askConfirm("\nEnter Y if you want to delete the holiday",
                "Enter N to go back:")) {
				x = ConsoleIOManager.readTimeMMdd("Enter the time you want to remove",
                "Format: MM-DD (e.g. 12-25)");
            	Setting.getHolidayList().remove(ConsoleIOManager.formatTimeMMdd(x));
				try {
                    Setting.updateHolidayList();
					//DataStoreManager.getInstance().AddToStore(Setting.holidayList);
					ConsoleIOManager.printF("Successfully deleted the holiday.");
				} catch (Exception e) {
					ConsoleIOManager.printF("Failed to delete the holiday.");
				}
            }
			displayHolidayList();
    }
}