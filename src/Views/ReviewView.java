package Views;

import Models.Data.Movie;
import Models.Data.MovieReview;
import java.util.ArrayList;

/**
 * ReviewView class used by ReviewController for printing information to console using static functions
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 * @see Controllers.ReviewController
 * @see Views.ConsoleIOManager
 */
public class ReviewView {
    /**
     * Prints the selection menu.
     */
    public static void displayMenu(Movie selectedMovie) {
        ConsoleIOManager.clearScreen();
        ConsoleIOManager.printMenu(selectedMovie.toString());

        ConsoleIOManager.printMenu("Reviews",
                "List all Reviews",
                "Leave a review");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a title for starting a review creation
     */
    public static void printReviewCreation() {
        ConsoleIOManager.printMenu("Creating a review");
    }

    /**
     * Prints a message to get name input
     */
    public static void printGetName() {
        ConsoleIOManager.printLine("Enter your name: ");
    }

    /**
     * Prints a message to get review input
     */
    public static void printGetReview() {
        ConsoleIOManager.printLine("Enter your review: ");
    }

    /**
     * Prints a message to get score input
     */
    public static void printGetRating() {
        ConsoleIOManager.printLine("Enter rating score (0.0 - 5.0): ");
    }

    /**
     * Prints a message for review creation success
     */
    public static void printReviewCreationSuccess() {
        ConsoleIOManager.printLine("Added your review!");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a message for review creation cancelled
     */
    public static void printReviewCreationCancelled() {
        ConsoleIOManager.printMenu("Cancelled creation.");
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints the list of reviews formatted
     * @param reviews list of reviews tagged to the movie
     */
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
        ConsoleIOManager.printGoBack();
    }

    /**
     * Prints a confirmation message for review creation with a preview of the review
     */
    public static void printCreateConfirm(MovieReview newReview) {
        ConsoleIOManager.printMenu("Review preview");
        ConsoleIOManager.printMenu(String.format("%-30s%s", newReview.getUserName() + "'s Review:", newReview.getTimeStamp()));
        ConsoleIOManager.printLine("Rating: " + newReview.getReviewScore());
        ConsoleIOManager.printLine("Comments: " + newReview.getReview());
        ConsoleIOManager.printConfirm();
    }
}
