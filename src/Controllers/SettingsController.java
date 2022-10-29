package Controllers;

import Views.ConsoleIOManager;
import Models.Data.Setting;
import Views.SettingsView;
import java.util.*;
import java.util.ArrayList;

public class SettingsController implements INavigation {
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
			
				

	private static void manageTicketPrice() {
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
				System.out.println("Current standard price: $" + Setting.getStandardPrice());
				System.out.println("Enter new standard price: ");
				double newSP = sc.nextDouble();
				Setting.setStandardPrice(newSP);
				System.out.println("\nStandard price updated.");
				displayTicketPrices();

			}
			else{
				NavigationController.getInstance().goBack(1);
				break;
			}		
		} while (choice >= 0 || choice <= 2);
	}
	
	private static void manageHolidays() {
		String holidayDate;
		String holidayName;
		ConsoleIOManager.ClearScreen();
		do {
				System.out.println("Enter the holiday name else input 0 go back ");
				holidayName = sc.nextLine(); 
				if (holidayName.isEmpty()||holidayName.matches("0")){
					NavigationController.getInstance().goBack(1);
				}
				System.out.println("Enter the holiday date else input 0 go back ");
				holidayDate = sc.nextLine(); 
				if (holidayDate.isEmpty()||holidayDate.matches("0") ) {
					ConsoleIOManager.ClearScreen();
					NavigationController.getInstance().goBack(1);		
				}
				if(!holidayDate.isEmpty() && 
					holidayDate.toCharArray().length == 8) {
					String[] dtLine = holidayDate.split("-");
					Setting.Holiday.put(holidayName,holidayDate);
					System.out.println("Added date as holiday.");
					System.out.println(Setting.Holiday);
					
				}
				else {
					System.out.println("You have entered wrong input. Please re-enter again!");
					continue;
				}
			} while (!holidayDate.isEmpty());
		NavigationController.getInstance().goBack(1);
	}
	
	/**
	 * displayTicketPrices() : Displays ticket price chart
	 */
	private static void displayTicketPrices() {
		System.out.println("____________ TICKET PRICE CHART ___________\n");
		System.out.printf("Adult\t\t\t\t%.2f\n",Setting.getAdultPrice());
		System.out.printf("Student\t\t\t\t%.2f\n", Setting.getStudentPrice());
		System.out.printf("Senior\t\t\t\t%.2f\n",Setting.getSeniorPrice());
		System.out.printf("Children\t\t\t%.2f\n", Setting.getChildPrice());
		System.out.println("\n");
	}
}