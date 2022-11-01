package Models.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import Views.SettingsView;
import Models.Data.Enums.*;
import java.text.SimpleDateFormat;
public class Setting implements Serializable {
    public static HashMap<String, Holiday> holidayList = new HashMap<String, Holiday>();
    private static double standardPrice = 10;
    private static double weekendPrice = (int) standardPrice* 1.2;
    final static int PLATINUM =5;
    final static int IMAX =10;
	private double senior,adult,child,student;
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
    public  double getSeniorPrice(String date,CinemaType c, MovieStatus m){
        Date x = SettingsView.readTimeMMdd(date);
        Holiday holiday = getHoliday(x);
        if (holiday != null) {
            senior  = holiday.getRate();
            if (c ==CinemaType.PLATINUM){
                senior += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            if (c ==CinemaType.IMAX){
                senior += IMAX;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            return senior*0.85;
        }
        else if(isWeekend(x)){
            senior  = weekendPrice;
            if (c ==CinemaType.PLATINUM){
                senior += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            if (c == CinemaType.IMAX){
                senior += IMAX;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            return senior*0.85;
        }
        else{
            senior  = standardPrice;
            if (c ==CinemaType.PLATINUM){
                senior += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            if (c == CinemaType.IMAX){
                senior += IMAX;
                if(m == MovieStatus.PREVIEW){
                    senior*=1.2;
                    return senior *0.8;
                }
                return senior*0.8;
            }
            return senior*0.85;
        }
	}
    public  double getStudentPrice(String date,CinemaType c, MovieStatus m){
        Date x = SettingsView.readTimeMMdd(date);
        Holiday holiday = getHoliday(x);
        if (holiday != null) {
            student  = holiday.getRate();
            if (c ==CinemaType.PLATINUM){
                student += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            if (c ==CinemaType.IMAX){
                student += IMAX;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            return student*0.85;
        }
        else if(isWeekend(x)){
            student  = weekendPrice;
            if (c ==CinemaType.PLATINUM){
                student += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            if (c == CinemaType.IMAX){
                student += IMAX;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            return student*0.85;
        }
        else{
            student  = standardPrice;
            if (c ==CinemaType.PLATINUM){
                student += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            if (c == CinemaType.IMAX){
                student += IMAX;
                if(m == MovieStatus.PREVIEW){
                    student*=1.2;
                    return student *0.85;
                }
                return student*0.85;
            }
            return student*0.85;
        }
	}
    public  double getChildPrice(String date,CinemaType c, MovieStatus m){
        Date x = SettingsView.readTimeMMdd(date);
        Holiday holiday = getHoliday(x);
        if (holiday != null) {
            child  = holiday.getRate();
            if (c ==CinemaType.PLATINUM){
                child += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            if (c ==CinemaType.IMAX){
               child += IMAX;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            return child*CHILDMULTIPLIER;
        }
        else if(isWeekend(x)){
            child  = weekendPrice;
            if (c ==CinemaType.PLATINUM){
                child += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            if (c == CinemaType.IMAX){
                child += IMAX;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            return child*CHILDMULTIPLIER;
        }
        else{
            child  = standardPrice;
            if (c ==CinemaType.PLATINUM){
                child += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            if (c == CinemaType.IMAX){
                child += IMAX;
                if(m == MovieStatus.PREVIEW){
                    child*=1.2;
                    return child *CHILDMULTIPLIER;
                }
                return child*CHILDMULTIPLIER;
            }
            return child*CHILDMULTIPLIER;
        }
	}



    public  double getAdultPrice(String date,CinemaType c, MovieStatus m){
        Date x = SettingsView.readTimeMMdd(date);
        Holiday holiday = getHoliday(x);
        if (holiday != null) {
            adult  = holiday.getRate();
            if (c ==CinemaType.PLATINUM){
                adult += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    adult*=1.2;
                    return adult;
                }
                return adult;
            }
            if (c ==CinemaType.IMAX){
                adult += IMAX;
                if(m == MovieStatus.PREVIEW){
                    adult*=1.2;
                    return adult;
                }
                return adult;
            }
            return adult;
        }
        else if(isWeekend(x)){
            adult  = weekendPrice;
            if (c ==CinemaType.PLATINUM){
                adult += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                    adult*=1.2;
                    return adult;
                }
                return adult;
            }
            if (c == CinemaType.IMAX){
                adult += IMAX;
                if(m == MovieStatus.PREVIEW){
                    adult*=1.2;
                    return adult;
                }
                return adult;
            }
            return adult;
        }
        else{
            adult  = standardPrice;
            if (c ==CinemaType.PLATINUM){
                adult += PLATINUM;
                if(m == MovieStatus.PREVIEW){
                   adult*=1.2;
                    return adult;
                }
                return adult;
            }
            if (c == CinemaType.IMAX){
                adult+= IMAX;
                if(m == MovieStatus.PREVIEW){
                    adult*=1.2;
                    return adult;
                }
                return adult;
            }
            return adult;
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
        //DataStoreManager.getInstance().AddToStore(Setting.holidayList);
    }

    //This method is used to get the holiday with specified
    public static Holiday getHoliday(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(SettingsView.formatTimeMMdd(time));
    }

    //check wether it is wenkend or not
    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }
}