package Models.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores the data for a screening.
 *
 * @author Han Zhiguang
 * @version 1.0
 * @since 2022-11-04
 */
public class Screening implements Serializable {

	private Movie movie;
	private ShowTime showTime;
	private ArrayList<ArrayList<Seat>> sessionLayout;

	/**
	 * @return movie for the show time.
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @return selected show time.
	 */
	public ShowTime getShowTime() {
		return showTime;
	}

	/**
	 * @return the current seats layout
	 */
	public ArrayList<ArrayList<Seat>> getSessionLayout() {
		return sessionLayout;
	}


	/**
	 * @param movie Admin selected Movie to set.
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @param showTime Admin select show time to set.
	 */
	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	/**
	 * @param sessionLayout new seats layout to set
	 */
	public void setSessionLayout(ArrayList<ArrayList<Seat>> sessionLayout) {
		this.sessionLayout = sessionLayout;
	}
}
