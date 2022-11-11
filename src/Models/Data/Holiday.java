package Models.Data;

import Models.DataStoreManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores the data for a Holiday object.
 *
 * @author Han Zhiguang, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class Holiday implements Serializable {
    private String name;
    private LocalDate date;
    private double rate;

    /**
     * Constructor for a new Holiday
     * @param name Name of holiday
     * @param date Date of the holiday
     * @param rate Percentage increase of ticket prices on the holiday
     */
    public Holiday(String name, LocalDate date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }

    /**
     * @return Name of holiday
     */
    public String getName() {
        return name;
    }

    /**
     * @return Date of holiday
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return Percentage Rate increase of ticket prices
     */
    public double getRate() {
        return rate;
    }

    /**
     * @return Formatted string of the holiday details
     */
    public String detailString() {
        return String.format("Holiday name: %s\nDate: %tm/%<td\nPrice Increase : %.2f%%",name,date,rate);
    }

    /**
     * @return Formatted string of holiday overview
     */
    public String toString() {
        return String.format("%s (%tm/%<td)",name ,date);
    }

    /**
     * @return Retrieves the list of holidays from the DataStore
     */
    public static ArrayList<Holiday> getHolidayList() {
        return DataStoreManager.getInstance().getStore(Holiday.class);
    }
}