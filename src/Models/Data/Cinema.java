package Models.Data;

import Models.Data.Enums.CinemaType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable{

	private String name = "Cinema";
	private ArrayList<ArrayList<CinemaLayout>>  seatTemplate;
	private CinemaType cinemaType;
	private final ArrayList<Screening> screeningList = new ArrayList<>();

	public List<Movie> getMovieList() {
		return screeningList.stream().map(Screening::getMovie).toList();
	}

	public List<Screening> GetScreeningsByMovie(Movie movie) {
		return screeningList
				.stream()
				.filter(screening -> screening.getMovie().getName().equals(movie.getName()))
				.toList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ArrayList<CinemaLayout>> getSeatTemplate() {
		return seatTemplate;
	}

	public void setSeatTemplate(ArrayList<ArrayList<CinemaLayout>> seatTemplate) {
		this.seatTemplate = seatTemplate;
	}
	public ArrayList<Screening> getScreeningList(){
		return screeningList;
	}

	// TODO: Override ArrayList<Screening> to prevent people from getList and calling .Add()
	public void addToScreeningList(Screening screening){
		screening.setSessionLayout(getSeatTemplate());
		screeningList.add(screening);
	}
}