package Models.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTime implements Serializable {
	private LocalTime timeOfMovie;
	private LocalDate dateOfMovie;

	public LocalTime getTimeOfMovie() {
		return timeOfMovie;
	}

	public void setTimeOfMovie(LocalTime timeOfMovie) {
		this.timeOfMovie = timeOfMovie;
	}

	public LocalDate getDateOfMovie() {
		return dateOfMovie;
	}

	public void setDateOfMovie(LocalDate dateOfMovie) {
		this.dateOfMovie = dateOfMovie;
	}
}