package Models.Data;
import java.io.Serializable;
import java.util.*;

import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Models.Data.Enums.*;
import java.text.SimpleDateFormat;

public class Setting implements Serializable {
    public static Setting getSettings(){
        DataStoreManager dataStoreInstance = DataStoreManager.getInstance();
        ArrayList<Setting> settingStore =  dataStoreInstance.getStore(Setting.class);
        if(settingStore.size() == 0){
            Setting newSettings = new Setting();
            dataStoreInstance.addToStore(newSettings);
            return newSettings;
        }
        // Get the only stored setting object
        return settingStore.get(0);
    }

    private void saveSettings(){
        DataStoreManager.getInstance().save(Setting.class);
    }

    private double standardPrice = 10;
    private double weekendPrice = (int) standardPrice* 1.2;
    final static int PLATINUM =5;
    final static int IMAX =10;
    final static  double CHILDMULTIPLIER = 0.7;
    public double getStandardPrice() {
		return standardPrice;
	}
    public double getStandardAdultPrice() {
		return standardPrice;
	}
    public double getStandardStudentPrice() {
		return standardPrice*0.85;
	}
    public double getStandardSeniorPrice() {
		return standardPrice*0.8;
	}
    public double getStandardWeekendPrice() {
		return standardPrice*1.2;
	}
    public double getStandardChildPrice() {
		return standardPrice*0.7;
	}
    public double getPrice(String date,CinemaType c, MovieStatus m, TicketClass t){
        Date x = ConsoleIOManager.readTimeMMdd(date);
        Holiday holiday = getHoliday(x);
        double temp;
        if (holiday != null) {
            temp = holiday.getRate();
            switch (c){
                case NORMAL:{
                    if (m == MovieStatus.PREVIEW){
                        temp*=1.2;
                    }
                    break;
                }
	            case PLATINUM:{
                    temp +=4;
                    if (m == MovieStatus.PREVIEW){
                        temp*=1.2;
                    }
                    break;
                }
	            case IMAX:{
                    temp +=8;
                    if (m == MovieStatus.PREVIEW){
                        temp*=1.2;
                    }
                    break;
                }
            }
        }
        else if(isWeekend(x)){
            temp  = weekendPrice;
            switch (c){
                case NORMAL: break;
	            case PLATINUM:{
                    temp +=4;
                    break;
                }
	            case IMAX:{
                    temp +=8;
                    break;
                }  
            }
            if (m == MovieStatus.PREVIEW){
                temp*=1.2;
            }
        }
        else{
            temp  = standardPrice;
            switch (c){
                case NORMAL: break;
	            case PLATINUM:{
                    temp +=4;
                    break;
                }
	            case IMAX:{
                    temp +=8;
                    break;
                }  
            }
            if (m == MovieStatus.PREVIEW){
                temp*=1.2;
            }
        }
        switch(t){
            case CHILD:
            case SENIOR:
                return temp*0.7;
            case STUDENT:
                return temp*0.85;
            case ADULT:
            default: return temp;
        }
	}

    public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
        saveSettings();
	}

    //This method is used to get the holiday with specified
    private Holiday getHoliday(Date selectedDate) {
        ArrayList<Holiday> holidayList = Holiday.getHolidayList();
        return holidayList.stream().filter(holiday -> holiday.getDate() == selectedDate).findFirst().orElse(null);
    }

    //check wether it is wenkend or not
    private boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        return whatDay.equals("Saturday") || whatDay.equals("Sunday");
    }
}