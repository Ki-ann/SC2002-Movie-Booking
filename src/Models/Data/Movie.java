package Models.Data;

import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.MovieType;
import Models.DataStoreManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(int hours, int minutes, int seconds) {
        this.duration = Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(ArrayList<String> movieGenre) {
        this.movieGenre = movieGenre;
    }

    public ArrayList<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public ArrayList<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(ArrayList<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }
    
    public void addReview(MovieReview review) {
		
		
		if(movieReviews == null) {
			movieReviews = new ArrayList<MovieReview>();
		}
		
		movieReviews.add(review);
	}
	
	public ArrayList<MovieReview> getReview() {
		
		return movieReviews;
	}
	
	public float getAvgRating()
	{
		float sum = 0;
		for(int i = 0; i<movieReviews.size(); i++) {
			
			
			sum += movieReviews.get(i).getReviewScore();
			
		}
		return sum/((float)movieReviews.size());
	}

    @Override
    public String toString() {
        return String.format("Movie Title:\n%s | %s\n\nLanguage:\n%s\n\nSynopsis:\n%s\n\nDuration of Movie:\n%02d hour %02d minutes\n\nGenre:\n%s\n\nCasts:\n%s\n\nDirector:%s\n\n%s\n",
                name,
                movieStatus,
                language,
                synopsis,
                duration.toHoursPart(),
                duration.toMinutesPart(),
                movieGenre.stream().map(String::toString).collect(Collectors.joining(" | ")),
                cast.stream().map(String::toString).collect(Collectors.joining(" | ")),
                director.stream().map(String::toString).collect(Collectors.joining(" | ")),
                movieReviews.stream().map(Object::toString).collect(Collectors.joining(" | ")));
    }

    public int getTicketSales() {
        return ticketSales;
    }

    public void incrementTicketSales() {
        ++this.ticketSales;
    }

    /**
     * Retrieves the Movie list from DataStore
     *
     * @return Arraylist of Movies in the DataStore
     * @see DataStoreManager
     */
    public static ArrayList<Movie> getMovieList() {
        return DataStoreManager.getInstance().getStore(Movie.class);
    }
}
