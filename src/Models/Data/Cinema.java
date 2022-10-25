package Models.Data;

import Models.Data.Enums.CinemaType;

import java.util.ArrayList;

public class Cinema {

	private String name;
	private CinemaLayout seatTemplate;
	private CinemaType cinemaType;
	private ArrayList<Screening> screeningList;

	public ArrayList<Movie> GetMovieList() {
		// TODO - implement Models.Data.Cinema.GetMovieList
		throw new UnsupportedOperationException();
	}

}