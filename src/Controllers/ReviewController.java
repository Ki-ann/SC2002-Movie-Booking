package Controllers;

import Models.Data.Movie;
import Models.Data.MovieReview;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.ReviewView;
import java.util.ArrayList;

/**
 * ReviewController is a Navigation that manages the logic and flow for managing reviews, called after searching for movies
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class ReviewController implements INavigation {

    private Movie selectedMovie;

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
        if (selectedMovie == null) {
            NavigationController.getInstance().goBack(2);
        }

        ReviewView.displayMenu(selectedMovie);
        boolean valid = true;
        do {
            switch (ConsoleIOManager.readInt()) {
                case 1 -> listReviews(selectedMovie);
                case 2 -> createReview(selectedMovie);
                case 0 -> NavigationController.getInstance().goBack(2);
                default -> {
                    ConsoleIOManager.printLine("Invalid input! Please select an item from the menu!");
                    valid = false;
                }
            }
        } while (!valid);

    }

    /**
     * Sets the current movie to manage reviews for. To be called before entering this navigation.
     * @param selectedMovie the movie to manage reviews for
     */
    public void setMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    /**
     * Manages the flow for creating a review
     * @param selectedMovie selected movie to create a review for
     */
    public void createReview(Movie selectedMovie) {
        ReviewView.printReviewCreation();

        String userName = getUserName();
        String review = getReview();
        float rating = getRating();

        MovieReview newReview = new MovieReview(userName, rating, review, selectedMovie);

        ReviewView.printCreateConfirm(newReview);
        boolean confirm = ConsoleIOManager.readConfirm();
        if(confirm){
            selectedMovie.addReview(newReview);
            ReviewView.printReviewCreationSuccess();
            DataStoreManager.getInstance().saveAll();

        }else{
            ReviewView.printReviewCreationCancelled();
        }

        waitForReturnInput();

        NavigationController.getInstance().goBack(0);
    }

    /**
     * Lists all reviews for the selected movie
     * @param selectedMovie selected movie to list reviews
     */
    public void listReviews(Movie selectedMovie) {
        ArrayList<MovieReview> reviews = selectedMovie.getMovieReviews();
        ReviewView.printReviews(reviews);

        waitForReturnInput();
        NavigationController.getInstance().goBack(0);
    }

    /**
     * Retrieves user input for rating score
     * @return rating score float from 0.0 to 5.0
     */
    private float getRating() {
        ReviewView.printGetRating();

        float rating;
        do {
            rating = (float) ConsoleIOManager.readDouble();

            if (rating < 0 || rating > 5) {
                ConsoleIOManager.printLine("Invalid input! Please give a score of 0.0-5.0!");
            } else {
                break;
            }
        } while (true);
        return rating;
    }

    /**
     * Retrieves the user message for the review
     * @return rating review message
     */
    private String getReview() {
        ReviewView.printGetReview();
        return ConsoleIOManager.readString();
    }

    /**
     * Retrieves the name to display for the review
     * @return name of the user who made the review
     */
    private String getUserName() {
        ReviewView.printGetName();
        return ConsoleIOManager.readString();
    }

    /**
     * Waits for an input of [0] to go back to previous operation.
     */
    private void waitForReturnInput() {
        int input;
        do {
            input = ConsoleIOManager.readInt();

            if (input != 0) {
                ConsoleIOManager.printLine("Invalid input! Please give a score of 0.0-5.0!");
            } else {
                break;
            }
        } while (true);
    }



}
