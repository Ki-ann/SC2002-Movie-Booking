package Models.Data;

import java.util.ArrayList;
import java.util.HashMap;
public class Setting {
    private String movieName, movieType, cinemaType;
    private static double standardPrice = 10;
	private double holidayPrice;
	private int day, month, year;
	private Movie movie;
	private Cineplex cineplex;
	private Cinema cinema;
    public static HashMap<String, String> Holiday = new HashMap<String, String>();

	public Setting(String movieName, String movieType, String cinemaType, double standardPrice,Movie movie, Cineplex cineplex, Cinema cinema,int day,int month,int year) {
		this.movieName = movieName;
		this.movieType = movieType;
		this.cinemaType = cinemaType;
        Setting.standardPrice = standardPrice;
        this.movie = movie;
		this.cinema = cinema;
		this.cineplex = cineplex;
		this.day =day;
        this.month = month;
        this.year = year;
	}
	public String getMovieName() {
		return movieName;
	}

	public Movie getMovie() {
		return movie;
	}

	public String getMovieType() {
		return movieType;
	}

	public String getCinemaType() {
		return cinemaType;
	}
    public static double getStandardPrice() {
		return standardPrice;
	}
    public void setHolidayPrice(int p) {
        holidayPrice = p;
	}
	public double getHolidayPrice() {
        return  holidayPrice;
	}
    public static double getSeniorPrice() {
        return  standardPrice*0.8;
	}
    public static double getChildPrice() {
        return  standardPrice*0.6;
	}
    public static double getStudentPrice() {
        return  standardPrice*0.8;
	}
    public static double getAdultPrice() {
        return  standardPrice;
	}
	public Cineplex getCineplex() {
		return cineplex;
	}
	public Cinema getCinema() {
		return cinema;
	}
    public boolean isDuplicateTime(Setting showTime) {
		if(this.year==showTime.year && 
				this.month == showTime.month &&
                this.day==showTime.day
                ) {
			return true;
		}
		return false;
	}
    public static void setStandardPrice(double standardPrice) {
		Setting.standardPrice = standardPrice;
	}

}
