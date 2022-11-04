package Controllers;

import Models.Data.Movie;

public class ReviewController {

	/**
	 * 
	 * @param selectedMovie
	 */
	public void createReview(Movie selectedMovie) {
		// TODO - implement Controllers.ReviewController.CreateReview
		MovieReview temp = new MovieReview();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Review: ");
		
		String userInput = sc.nextLine();
		
		System.out.println("Enter Rating: ");
		
		Float rating = sc.nextFloat();
		
		temp.setReview(userInput);
		temp.setReviewScore(rating);
		temp.setTimeStamp(String.format("%tc", LocalDate.now()));
		
		//Get Current Logged In User ID
		//temp.setUserID(userInput);
		
		selectedMovie.addReview(temp);
		
	}

	/**
	 * 
	 * @param selectedMovie
	 */
	public void listReviews(Movie selectedMovie) {
		// TODO - implement Controllers.ReviewController.ListReviews
		ArrayList<MovieReview> reviews = selectedMovie.getReview();
		
		System.out.println("Overall Rating: " + selectedMovie.getAvgRating());
		
		for(int i = 0; i<reviews.size(); i++) {
			MovieReview review = reviews.get(i);
	
			
			System.out.println("Comments: " + review.getReview());
			System.out.println("Rating: " + review.getReviewScore());
			System.out.println("Time: " + review.getTimeStamp());
			System.out.println("User: " + review.getUserID());
		}
		
	}

}
