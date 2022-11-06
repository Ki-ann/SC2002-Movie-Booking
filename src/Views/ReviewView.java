package Views;

import Models.Data.Movie;
import Models.Data.MovieReview;

import java.util.ArrayList;

public class ReviewView {
    public static void displayMenu(Movie selectedMovie) {
        ConsoleIOManager.printMenu(selectedMovie.toString());

        ConsoleIOManager.printMenu("Reviews",
                "List all Reviews",
                "Leave a review");
        ConsoleIOManager.printGoBack();

    }

    public static void printReviewCreation() {
        ConsoleIOManager.printMenu("Creating a review");
    }

    public static void printGetReview() {
        ConsoleIOManager.printLine("Enter your review: ");
    }

    public static void printGetRating() {
        ConsoleIOManager.printLine("Enter rating score (0.0 - 5.0): ");
    }

    public static void printReviewCreationSuccess() {
        ConsoleIOManager.printLine("Added your review!");
    }

    public static void printGetName() {
        ConsoleIOManager.printLine("Enter your name: ");
    }

    public static void printReviews(ArrayList<MovieReview> reviews) {
        if(reviews.size()> 0) {
            ConsoleIOManager.printMenu("Reviews for " + reviews.get(0).getMovie().getName());
            for (MovieReview review : reviews) {
                ConsoleIOManager.printMenu(String.format("%-30s%s", review.getUserName() + "'s Review:", review.getTimeStamp()));
                ConsoleIOManager.printLine("Rating: " + review.getReviewScore());
                ConsoleIOManager.printLine("Comments: " + review.getReview());
            }
        }else{
            ConsoleIOManager.printMenu("No reviews found");
        }
    }
}
