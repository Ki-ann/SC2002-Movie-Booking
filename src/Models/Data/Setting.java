package Models.Data;
import java.io.Serializable;
import java.util.HashMap;
public class Setting implements Serializable {
    private static double standardPrice = 10;
	private double holidayPrice,senior,adult,child,student;
	private Movie movie;
    public static HashMap<String, String> Holiday = new HashMap<String, String>();
    public Setting(){
        adult = standardPrice;
        senior = standardPrice*0.8;
        child =standardPrice*0.8;
        student = standardPrice*0.8;
        holidayPrice = standardPrice*0.5;
    }

	public Movie getMovie() {
		return movie;
	}
    public void setMovie(Movie movie) {
		this.movie = movie;
	}
    public static double getStandardPrice() {
		return standardPrice;
	}
    

	public double getHolidayPrice() {
        return holidayPrice;
	}
    public  double getSeniorPrice() {
        return senior;
	}
    public double getChildPrice() {
        return child;
	}
    public  double getStudentPrice() {
        return  student;
	}
    public double getAdultPrice() {
        return adult;
	}
    public static void setStandardPrice(double standardPrice) {
		Setting.standardPrice = standardPrice;
	}

}
