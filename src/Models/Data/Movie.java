package Models.Data;

import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.MovieType;

import java.time.Duration;
import java.util.ArrayList;

public class Movie extends SingleInstancedSerializable {

	private String name = "Movie";
	private String description;
	private Duration duration = Duration.ofHours(2);
	private MovieStatus movieStatus = MovieStatus.NOW_SHOWING;
	private String movieGenre;
	private MovieRating movieRating;
	private ArrayList<MovieReview> movieReviews;
	private String language;
	private float movieRatings;
	private ArrayList<String> cast;
	private MovieType movieType;
	private ArrayList<String> director;

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


}