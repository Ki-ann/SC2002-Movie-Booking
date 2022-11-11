package Models.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Stores the data for a Reivew object
 *
 * @author Anapana Dinesh Kumar, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class MovieReview implements Serializable {

	private String timestamp;
	private Movie movie;
	private float reviewScore;
	private String userName;
	private String review;

	/**
	 * Constructor for a new review object
	 * @param name Name of reviewer
	 * @param reviewScore Score of review
	 * @param review Description of the review
	 * @param movie Selected Movie for the review
	 */
	public MovieReview(String name, float reviewScore, String review, Movie movie){
		this.timestamp = String.format("%td/%<tm/%<ty | %tH:%<tM", LocalDate.now(), LocalTime.now());
		this.userName = name;
		this.reviewScore = reviewScore;
		this.review = review;
		this.movie = movie;
	}

	/**
	 * @return Get movie the review is tagged to
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return Get Score of review
	 */
	public float getReviewScore() {
		return reviewScore;
	}

	/**
	 * @return Get Name of reviewer
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return Get Description of review
	 */
	public String getReview() {
		return review;
	}


	/**
	 * @param movie Sets the user selected movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @param review Sets the user selected review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * @return Get review timestamp
	 */
	public String getTimeStamp() {
		return timestamp;
	}

}
