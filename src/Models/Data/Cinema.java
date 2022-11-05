package Models.Data;

import Models.Data.Enums.CinemaType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable{

	private String name;
	private CinemaType cinemaType;
	private ArrayList<ArrayList<Seat>>  seatTemplate;
	private final ArrayList<Screening> screeningList = new ArrayList<>();

	public Cinema(String cinemaName, CinemaType cinemaType,ArrayList<ArrayList<Seat>> seatTemplate){
		this.name = cinemaName;
		this.cinemaType = cinemaType;
		this.seatTemplate = seatTemplate;
	}
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

	public ArrayList<ArrayList<Seat>> getSeatTemplate() {
		return seatTemplate;
	}

	public void setSeatTemplate(ArrayList<ArrayList<Seat>> seatTemplate) {
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

	public CinemaType getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	public boolean removeScreeningList(Screening screening) {
		for (Screening i : screeningList){
			if((i.getShowTime()).getTimeOfMovie() == screening.getShowTime().getTimeOfMovie()){
				screeningList.remove(i);
				return true;
			}
		}return false;
	}
}