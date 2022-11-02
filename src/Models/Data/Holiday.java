package Models.Data;

import Models.DataStoreManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// This class contains all information of a holiday - including its name, date and price rate.

public class Holiday implements Serializable {
    private String name;
    private Date date;
    private double rate;

    public Holiday(String name, Date date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }

    
    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public String detailString() {
        return String.format("Holiday name: %s\nDate: %s\nPrice Increase : %.2f%%",name,formatTimeMMdd(date), rate);
    }
    public String toString() {
        return name + " (" + formatTimeMMdd(date) + ")";
    }
    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MM-dd").format(time);
    }
    public static ArrayList<Holiday> getHolidayList() {
        return DataStoreManager.getInstance().getStore(Holiday.class);
    }
}