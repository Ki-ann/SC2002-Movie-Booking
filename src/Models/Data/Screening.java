package Models.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Screening implements Serializable {

	private Movie movie;
	private ShowTime showTime;
	private ArrayList<ArrayList<CinemaLayout>> sessionLayout;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ShowTime getShowTime() {
		return showTime;
	}

	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	public ArrayList<ArrayList<CinemaLayout>> getSessionLayout() {
		return sessionLayout;
	}

	public void setSessionLayout(ArrayList<ArrayList<CinemaLayout>> sessionLayout) {
		this.sessionLayout = sessionLayout;
	}
}