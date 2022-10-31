package Models.Data;

import java.io.Serializable;

public class MovieReview implements Serializable {

	private String timestamp;
	private Movie movie;
	private Float reviewScore;
	private String userID;
	private String review;

}