package Controllers;

import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Models.Data.*;
import Views.SettingsView;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * SettingsController that handles the logic for modifying system configuration
 *
 * @author Han Zhiguang, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class SettingsController implements INavigation {

    private final Setting setting = Setting.getSettings();
    private int initialMenuSelection = -1;

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        SettingsView.displayMenu();

        do {
            if (initialMenuSelection == -1) {
                initialMenuSelection = ConsoleIOManager.readInt();
            }
            switch (initialMenuSelection) {
                case 1 -> manageTicketPrice();
                case 2 -> manageHolidays();
                case 3 -> manageTopMovieStates();
                case 4 -> manageDiscounts();
                case 0 -> NavigationController.getInstance().goBack();
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    initialMenuSelection = -1;
                }
            }
        } while (initialMenuSelection == -1);
    }

    /**
     * a selection to manage discount codes
     */
    private void manageDiscounts() {
        SettingsView.printDiscountMenu();
        do {

            switch (ConsoleIOManager.readInt()) {
                case 1 -> listDiscountCodes();
                case 2 -> addDiscountCode();
                case 0 -> {
                    initialMenuSelection = -1;
                    NavigationController.getInstance().goBack(0);
                    return;
                }
                default -> ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * logic for adding a discount code to the DataStore
     */
    private void addDiscountCode() {
        SettingsView.printAddDiscountCode();
        String code = ConsoleIOManager.readString();

        SettingsView.printAddDiscountCodePercentage();
        double discount = ConsoleIOManager.readDouble();

        SettingsView.printAddDiscountCodeSuccess();
        DataStoreManager.getInstance().addToStore(new DiscountCode(code,discount));

        if (ConsoleIOManager.readInt() == 0) {
            NavigationController.getInstance().goBack(0);
        } else {
            ConsoleIOManager.printLine("Invalid input!");
        }
    }

    /**
     * lists all currently available discount codes and their discount percentages
     */
    private void listDiscountCodes() {
        ArrayList<DiscountCode> discountList = DataStoreManager.getInstance().getStore(DiscountCode.class);

        var codeStringList = discountList.stream()
                .map(discountCode -> String.format("%-10s %.2f%% OFF",discountCode.getCode(),discountCode.getDiscountPercentage() * 100))
                .toArray(String[]::new);
        SettingsView.printDiscountCodeList(codeStringList);

        if (ConsoleIOManager.readInt() == 0) {
            NavigationController.getInstance().goBack(0);
        } else {
            ConsoleIOManager.printLine("Invalid input!");
        }
    }

    /**
     * Call NavigationController to load new instance of TopMovieController object.
     * 
     * @see TopMovieController
     * @see NavigationController
     */
    private void manageTopMovieStates() {
        initialMenuSelection = -1;
        TopMovieController controller = new TopMovieController();
        controller.adminSetEditMode();
        NavigationController.getInstance().load(controller);
    }

    /**
     * a selection for manage ticket price.
     */
    private void manageTicketPrice() {
        SettingsView.printTicketPriceMenu(setting);
        do {

            switch (ConsoleIOManager.readInt()) {
                case 1 -> setStandardPrice();
                case 0 -> {
                    initialMenuSelection = -1;
                    NavigationController.getInstance().goBack(0);
                    return;
                }
                default -> ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * a selection to set standard price.
     */
    private void setStandardPrice() {
        SettingsView.printSetStandardPrice(setting);
        setting.setStandardPrice(ConsoleIOManager.readDouble());
        SettingsView.printSetStandardPriceSuccess();

        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * a selection to manage holidays.
     */
    private void manageHolidays() {
        do {
            SettingsView.printHolidayMenu();

            switch (ConsoleIOManager.readInt()) {
                case 1 -> displayHolidayList();
                case 2 -> addHoliday();
                case 0 -> {
                    initialMenuSelection = -1;
                    NavigationController.getInstance().goBack(0);
                    return;
                }
                default -> ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /**
     * display the holiday list and manage holiday list.
     */
    private void displayHolidayList() {
        ArrayList<Holiday> holidayList = Holiday.getHolidayList();
        var holidayString = holidayList.stream().map(holiday -> String.format("%s %tm/%<td  %.2f%%", holiday.getName() ,holiday.getDate(), holiday.getRate())).toArray(String[]::new);
        SettingsView.printHolidayListMenu(holidayString);


        Holiday selectedHoliday = getSelectedHoliday(holidayList);

        if (selectedHoliday == null) {
            NavigationController.getInstance().goBack(0);
        } else {
            displayHolidayDetail(selectedHoliday);
        }
    }

    /**
     * gets the admin's desired holiday selection input .
     *
     * @param holidayList The list of holidays for the admin to choose from.
     * @return Admin selected movie.
     */
    private Holiday getSelectedHoliday(ArrayList<Holiday> holidayList) {
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input < 0 || input > holidayList.size()) {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            } else if (input == 0) {
                return null;
            } else {
                return holidayList.get(input - 1);
            }
        } while (true);
    }

    /**
     * set the admin's desired date to holiday.
     *
     */
    private void addHoliday() {
        ConsoleIOManager.printLine("Enter the name of the holiday:");
        String name = ConsoleIOManager.readString().toLowerCase();
        ConsoleIOManager.printLine("Enter the date of the holiday\n" +
                "Format: MM/DD (e.g. 12/25)");
        LocalDate date = ConsoleIOManager.readTimeMMdd();
        ConsoleIOManager.printLine("Enter the % price increase on that day:\n" +
                "e.g. holiday price is 10% more expensive standard price");
        double pricePercentage = ConsoleIOManager.readDouble();

        Holiday holiday = new Holiday(name, date, pricePercentage);
        DataStoreManager.getInstance().addToStore(holiday);

        SettingsView.printAddHolidaySuccess();

        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }

    /** 
     * display the details for admin's desired holiday.
     *
     * @param holiday a holiday selected by admin.
     */
    private void displayHolidayDetail(Holiday holiday) {
        SettingsView.displayHolidayDetails(holiday);
        if (ConsoleIOManager.readConfirm()) {
            deleteHoliday(holiday);
        } else {
            NavigationController.getInstance().goBack(0);
        }
    }

    /**
     * delete admin's desired holiday.
     *
     * @param holiday a holiday selected by admin.
     */
    private void deleteHoliday(Holiday holiday) {
        DataStoreManager.getInstance().removeFromStore(holiday);
        SettingsView.printHolidayDeletionSuccess();

        do {
            if (ConsoleIOManager.readInt() == 0) {
                NavigationController.getInstance().goBack(0);
                return;
            } else {
                ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
            }
        } while (true);
    }
}