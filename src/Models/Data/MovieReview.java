package Models.Data;

import java.io.Serializable;

public class MovieReview implements Serializable {

	private String timestamp;
	private Movie movie;
	private Float reviewScore;
	private String userID;
	private String review;

	
	//setters
	public void setTimeStamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setReviewScore(Float reviewScore) {
		this.reviewScore = reviewScore;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public Float getReviewScore() {
		return reviewScore;
	}
	public String getUserID() {
		return userID;
	}
	public String getReview() {
		return review;
	}
	
}
