package Models.Data;
import Models.Data.Enums.CinemaType;
import Models.DataStoreManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	public void addCinemaToCineplex(CinemaType cinemaType){
		String cinemaCode = (this.getName().length() >= 2 ?
							this.getName().substring(0,2) + (this.getCinemaList().size() + 1) :
							this.getName() + "0" + (this.getCinemaList().size() + 1)).toUpperCase();
		ArrayList<ArrayList<Seat>> seatTemplate = null;
		String seatString = "XXXXX0000XXXXXX." +
				"0000X0000X00000." +
				"0000X0000X00000." +
				"0000X0000X00000." +
				"0000X0000X00000." +
				"0000X0000X00000." +
				"0000X0CC0X00000." +
				"0000X0000X00000";
		switch (cinemaType){
			case NORMAL -> seatTemplate =  DataStoreManager.getInstance().parseLayout(seatString);
			case IMAX -> seatTemplate =  DataStoreManager.getInstance().parseLayout(seatString);
			case PLATINUM -> seatTemplate =  DataStoreManager.getInstance().parseLayout(seatString);
		}
		Cinema newCinema = new Cinema(cinemaCode,cinemaType, seatTemplate);
		this.getCinemaList().add(newCinema);
		DataStoreManager.getInstance().save(Cineplex.class);
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