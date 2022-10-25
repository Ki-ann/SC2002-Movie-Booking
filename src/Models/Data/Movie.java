package Models.Data;

import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.MovieType;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

	private String name;
	private String description;
	private String timeslots;
	private MovieStatus movieStatus;
	private String movieGenre;
	private MovieRating movieRating;
	private ArrayList<MovieReview> movieReviews;
	private String language;
	private float movieRatings;
	private ArrayList<String> cast;
	private MovieType movieType;
	private ArrayList<String> director;

}