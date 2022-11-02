package Models.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import java.io.IOException;
import Views.ConsoleIOManager;
import Views.SettingsView;
import Models.Data.Enums.*;
import java.text.SimpleDateFormat;

public class Setting implements Serializable {
    private static final String FILENAME_HOLIDAY = "Data/Holiday.dat"; 
    public static HashMap<String, Holiday> holidayList = new HashMap<String, Holiday>();
    private static double standardPrice = 10;
    private static double weekendPrice = (int) standardPrice* 1.2;
    final static int PLATINUM =5;
    final static int IMAX =10;
    final static  double CHILDMULTIPLIER = 0.7;
	private Movie movie;
	public Movie getMovie() {
		return movie;
	}
    public void setMovie(Movie movie) {
		this.movie = movie;
	}
    public static double getStandardPrice() {
		return standardPrice;
	}
    public static double getStandardAdultPrice() {
		return standardPrice;
	}
    public static double getStandardStudentPrice() {
		return standardPrice*0.85;
	}
    public static double getStandardSeniorPrice() {
		return standardPrice*0.8;
	}
    public static double getStandardWeekendPrice() {
		return standardPrice*1.2;
	}
    public static double getStandardChildPrice() {
		return standardPrice*0.7;
	}
    public static double getPrice(String date,CinemaType c, MovieStatus m, TicketClass t){
        Date x = SettingsView.readTimeMMdd2(date);
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
            case ADULT: return temp;
            case CHILD: return temp*0.7;
            case STUDENT: return temp*0.85;
            case SENIOR: return temp*0.7;
            default: return temp;
        }
	}
    public static void setStandardPrice(double standardPrice) {
		Setting.standardPrice = standardPrice;
	}
    public static HashMap<String, Holiday> getHolidayList() {
        return holidayList;
    }
    public static void addHoliday(String date, Holiday holiday)  {
        Setting.holidayList.put(date, holiday);
        try {
            updateHolidayList();
        }
        catch(IOException ex) {
            ConsoleIOManager.printLine("error");
        }
        //DataStoreManager.getInstance().AddToStore(Setting.holidayList);
    }

    //This method is used to get the holiday with specified
    public static Holiday getHoliday(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(ConsoleIOManager.formatTimeMMdd(time));
    }

    //check wether it is wenkend or not
    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }
    private static void readHolidayList() throws IOException, ClassNotFoundException {
        if (ConsoleIOManager.readSerializedObject(FILENAME_HOLIDAY) == null) holidayList = new HashMap<>();
        else holidayList = (HashMap<String, Holiday>) ConsoleIOManager.readSerializedObject(FILENAME_HOLIDAY);
    }
    public static void updateHolidayList() throws IOException {
        ConsoleIOManager.writeSerializedObject(FILENAME_HOLIDAY, holidayList);
    }
    public static void initialize() {
        try {
            readHolidayList();  // may have class not found exception
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }
}