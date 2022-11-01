package Models.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
public class Cineplex implements Serializable {

	public ArrayList<Cinema> cinemaList = new ArrayList<>();
	private String name;

	public ArrayList<Cinema> GetCinemaList() {
		return cinemaList;
	}

	public List<Cinema> GetFilteredCinemaList(Movie movie) {
		return GetCinemaList()
				// Foreach Cinema...
				.stream()
				// Find those Cinemas with the selected movie
				.filter(Cinema->Cinema.GetMovieList()
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
	public Cinema GetCinema(int index) {
		return cinemaList.get(index);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}