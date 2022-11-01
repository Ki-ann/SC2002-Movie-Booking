package Models.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
public class Cineplex implements Serializable {

	private final ArrayList<Cinema> cinemaList = new ArrayList<>();
	private String name;

	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}

	public List<Cinema> getCinemasWithMovie(Movie movie) {
		return getCinemaList()
				// Foreach Cinema...
				.stream()
				// Find those Cinemas with the selected movie
				.filter(Cinema->Cinema.getMovieList()
								// Foreach movie screening in the cinema
								.stream()
								// Find the screenings which match
								.anyMatch(mov->mov.getName().equals(movie.getName())))
				.toList();
	}

	/**
	 * 
	 * @param index
	 */
	public Cinema getCinemaByIndex(int index) {
		return cinemaList.get(index);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}