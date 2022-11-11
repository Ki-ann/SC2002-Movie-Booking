package Models.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Stores the data for a Showtime object.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class ShowTime implements Serializable {
	private LocalTime timeOfMovie;
	private LocalDate dateOfMovie;

	/**
	 * @return Get time of movie screening
	 */
	public LocalTime getTimeOfMovie() {
		return timeOfMovie;
	}

	/**
	 * @return Get date of movie screening
	 */
	public LocalDate getDateOfMovie() {
		return dateOfMovie;
	}

	/**
	 * @param timeOfMovie Set time of movie screening
	 */
	public void setTimeOfMovie(LocalTime timeOfMovie) {
		this.timeOfMovie = timeOfMovie;
	}

	/**
	 * @param dateOfMovie Set date of movie screening
	 */
	public void setDateOfMovie(LocalDate dateOfMovie) {
		this.dateOfMovie = dateOfMovie;
	}
}