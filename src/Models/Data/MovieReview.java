package Models.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class MovieReview implements Serializable {

	private String timestamp;
	private Movie movie;
	private float reviewScore;
	private String userName;
	private String review;

	public MovieReview(String name, float reviewScore, String review, Movie movie){
		this.timestamp = String.format("%td/%<tm/%<ty | %tH:%<tM", LocalDate.now(), LocalTime.now());
		this.userName = name;
		this.reviewScore = reviewScore;
		this.review = review;
		this.movie = movie;
	}
	//setters
	public void setTimeStamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setReviewScore(float reviewScore) {
		this.reviewScore = reviewScore;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	//getters
	public String getTimeStamp() {
		return timestamp;
	}
	public Movie getMovie() {
		return movie;
	}
	public float getReviewScore() {
		return reviewScore;
	}
	public String getUserName() {
		return userName;
	}
	public String getReview() {
		return review;
	}
	
}
