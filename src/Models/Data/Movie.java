package Models.Data;

import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.MovieType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Stores the data for a Movie object, extends a SingleInstancedSerializable
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 * @see Models.Data.SingleInstancedSerializable
 */
public class Movie extends SingleInstancedSerializable {

    private String name;
    private String synopsis;
    private Duration duration = Duration.ofHours(2);
    private MovieStatus movieStatus = MovieStatus.NOW_SHOWING;
    private MovieRating movieRating;
    private String language;
    private ArrayList<String> cast= new ArrayList<>();
    private ArrayList<String> movieGenre= new ArrayList<>();
    private ArrayList<String> director= new ArrayList<>();
    private ArrayList<MovieReview> movieReviews = new ArrayList<>();
    private MovieType movieType;
    private int ticketSales = 0;

    /**
     * @return Get Name of Movie
     */
    public String getName() {
        return name;
    }

    /**
     * @return Get current movie showing status
     */
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    /**
     * @return Get Movie Duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @return Get Movie Synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * @return Get Movie Language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return Get Movie Cast List
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * @return Get Movie Genre List
     */
    public ArrayList<String> getMovieGenre() {
        return movieGenre;
    }

    /**
     * @return Get Director List
     */
    public ArrayList<String> getDirector() {
        return director;
    }

    /**
     * @return Get list of reviews
     */
    public ArrayList<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    /**
     * @return Get Movie type
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * @return get movie age rating
     */
    public MovieRating getMovieRating() {
        return movieRating;
    }

    /**
     * Get average rating in string format
     * @return string of average rating
     */
    public String getAverageRatingString()
    {
        float sum = 0;
        for(int i = 0; i<movieReviews.size(); i++) {
            sum += movieReviews.get(i).getReviewScore();
        }
        return movieReviews.size() > 0 ? String.format("%.2f",(sum/((float)movieReviews.size()))) : "No reviews yet";
    }

    /**
     * Get average rating in float format
     * @return float of average rating
     */
    public float getAverageRatingFloat(){
        float sum = 0;
        for(int i = 0; i<movieReviews.size(); i++) {
            sum += movieReviews.get(i).getReviewScore();
        }
        return movieReviews.size() > 0 ? (sum/((float)movieReviews.size())) : 0;
    }

    /**
     * @return Get number of ticket sales per seat
     */
    public int getTicketSales() {
        return ticketSales;
    }


    /**
     * @param name Set Name of Moive
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param movieStatus Set current movie showing status
     */
    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * Set Current Movie Duration
     * @param hours Duration in hours
     * @param minutes Duration in minutes
     * @param seconds Duration in seconds
     */
    public void setDuration(int hours, int minutes, int seconds) {
        this.duration = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    /**
     * @param synopsis Set Movie Synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * @param language Set Movie Language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @param cast Set Movie Cast List
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * @param movieGenre Set Movie Genre List
     */
    public void setMovieGenre(ArrayList<String> movieGenre) {
        this.movieGenre = movieGenre;
    }

    /**
     * @param director Set Director List
     */
    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    /**
     * @param movieType Set Movie type
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * @param movieRating set movie age rating
     */
    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * Add a new movie review to this movie object
     * @param review review to add
     */
    public void addReview(MovieReview review) {
		movieReviews.add(review);
	}

    /**
     * @return Formatted string of all movie details
     */
    @Override
    public String toString() {
        return String.format("Movie Title:\n%s | %s\n\nLanguage:\n%s\n\nSynopsis:\n%s\n\nDuration of Movie:\n%02d hour %02d minutes\n\nAverage Rating:\n%s\n\nGenre:\n%s\n\nCasts:\n%s\nDirector:%s\n",
                name,
                movieStatus,
                language,
                synopsis,
                duration.toHoursPart(),
                duration.toMinutesPart(),
                getAverageRatingString(),
                movieGenre.stream().map(String::toString).collect(Collectors.joining(" | ")),
                cast.stream().map(String::toString).collect(Collectors.joining(" | ")),
                director.stream().map(String::toString).collect(Collectors.joining(" | ")));
    }

    /**
     * Increment the number of ticket sales by one
     */
    public void incrementTicketSales() {
        ++this.ticketSales;
    }
}
