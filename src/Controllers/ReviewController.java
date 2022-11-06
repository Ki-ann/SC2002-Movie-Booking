package Controllers;

import Models.Data.Movie;
import Models.Data.MovieReview;
import Models.DataStoreManager;
import Views.ConsoleIOManager;
import Views.ReviewView;

import java.util.ArrayList;

public class ReviewController implements INavigation {

    private Movie selectedMovie;

    @Override
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

    public void setMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    /**
     * @param selectedMovie
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

    private String getReview() {
        ReviewView.printGetReview();
        return ConsoleIOManager.readString();
    }

    private String getUserName() {
        ReviewView.printGetName();
        return ConsoleIOManager.readString();
    }

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

    /**
     * @param selectedMovie
     */
    public void listReviews(Movie selectedMovie) {
        ArrayList<MovieReview> reviews = selectedMovie.getMovieReviews();
        ReviewView.printReviews(reviews);

        waitForReturnInput();
        NavigationController.getInstance().goBack(0);
    }



}
