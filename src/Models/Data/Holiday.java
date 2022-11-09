package Models.Data;

import Models.DataStoreManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


// This class contains all information of a holiday - including its name, date and price rate.

public class Holiday implements Serializable {
    private String name;
    private LocalDate date;
    private double rate;

    public Holiday(String name, LocalDate date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }
    
    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public String detailString() {
        return String.format("Holiday name: %s\nDate: %tm/%<td\nPrice Increase : %.2f%%",name,date, rate);
    }
    public String toString() {
        return String.format("%s (%tm/%<td)",name ,date);
    }
    public static ArrayList<Holiday> getHolidayList() {
        return DataStoreManager.getInstance().getStore(Holiday.class);
    }
}