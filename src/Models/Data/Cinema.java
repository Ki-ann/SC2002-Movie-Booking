package Models.Data;

import Models.Data.Enums.CinemaType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable{

	private String name = "Cinema";
	private CinemaLayout seatTemplate;
	private CinemaType cinemaType;
	public ArrayList<Screening> screeningList = new ArrayList<>();

	public List<Movie> GetMovieList() {
		return screeningList.stream().map(screening -> screening.movie).toList();
	}

	public List<Screening> GetScreeningsByMovie(Movie movie) {
		return screeningList
				.stream()
				.filter(screening -> screening.movie.getName().equals(movie.getName()))
				.toList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}