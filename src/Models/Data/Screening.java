package Models.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores the data for a screening.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class Screening implements Serializable {

	private Movie movie;
	private ShowTime showTime;
	private ArrayList<ArrayList<Seat>> sessionLayout;

	/**
	 *
	 * @return movie for the show time.
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 *
	 * @param selectedMovie Admin selected Movie to set.
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 *
	 * @return selected show time.
	 */
	public ShowTime getShowTime() {
		return showTime;
	}

	/**
	 *
	 * @param showTime Admin select show time to set.
	 */
	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	/**
	 *
	 * @return the current seats layout 
	 */
	public ArrayList<ArrayList<Seat>> getSessionLayout() {
		return sessionLayout;
	}

	/**
	 *
	 * @param sessionLayout new seats layout to set
	 */
	public void setSessionLayout(ArrayList<ArrayList<Seat>> sessionLayout) {
		this.sessionLayout = sessionLayout;
	}
}