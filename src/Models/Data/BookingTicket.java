package Models.Data;

import java.io.Serializable;

public class BookingTicket implements Serializable {

	private int bookingID;
	private Customer customer;
	private Cineplex selectedCineplex;
	private Cinema selectedCinema;
	private Movie selectedMovie;
	private Screening selectedScreening;
	private CinemaLayout selectedSeat;
	private double price;

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public Cineplex getSelectedCineplex() {
		return selectedCineplex;
	}

	public void setSelectedCineplex(Cineplex selectedCineplex) {
		this.selectedCineplex = selectedCineplex;
	}

	public Cinema getSelectedCinema() {
		return selectedCinema;
	}

	public void setSelectedCinema(Cinema selectedCinema) {
		this.selectedCinema = selectedCinema;
	}

	public Screening getSelectedScreening() {
		return selectedScreening;
	}

	public void setSelectedScreening(Screening selectedScreening) {
		this.selectedScreening = selectedScreening;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CinemaLayout getSelectedSeat() {
		return selectedSeat;
	}

	public void setSelectedSeat(CinemaLayout selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
}