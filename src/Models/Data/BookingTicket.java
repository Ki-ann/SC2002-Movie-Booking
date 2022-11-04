package Models.Data;

import java.io.Serializable;
/**
 * Stores the data for a booking transaction
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class BookingTicket implements Serializable {

	private String bookingID;
	private Customer customer;
	private Cineplex selectedCineplex;
	private Cinema selectedCinema;
	private Movie selectedMovie;
	private Screening selectedScreening;
	private Seat selectedSeat;
	private double price;

	/**
	 *
	 * @return Transaction ID string for the payment
	 */
	public String getBookingID() {
		return bookingID;
	}

	/**
	 *
	 * @param bookingID Transaction ID string to set
	 */
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}

	/**
	 *
	 * @return User selected Movie
	 */
	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	/**
	 *
	 * @param selectedMovie User selected Movie to set
	 */
	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	/**
	 *
	 * @return User selected Cineplex object
	 */
	public Cineplex getSelectedCineplex() {
		return selectedCineplex;
	}

	/**
	 *
	 * @param selectedCineplex User selected Cineplex to set
	 */
	public void setSelectedCineplex(Cineplex selectedCineplex) {
		this.selectedCineplex = selectedCineplex;
	}

	/**
	 *
	 * @return User selected Cinema object
	 */
	public Cinema getSelectedCinema() {
		return selectedCinema;
	}

	/**
	 *
	 * @param selectedCinema User selected Cinema to set
	 */
	public void setSelectedCinema(Cinema selectedCinema) {
		this.selectedCinema = selectedCinema;
	}

	/**
	 *
	 * @return User selected Screening session
	 */
	public Screening getSelectedScreening() {
		return selectedScreening;
	}

	/**
	 *
	 * @param selectedScreening User selected Screening session to set
	 */
	public void setSelectedScreening(Screening selectedScreening) {
		this.selectedScreening = selectedScreening;
	}

	/**
	 *
	 * @return User selected Seat
	 */
	public Seat getSelectedSeat() {
		return selectedSeat;
	}

	/**
	 *
	 * @param selectedSeat User selected Seat to set
	 */
	public void setSelectedSeat(Seat selectedSeat) {
		this.selectedSeat = selectedSeat;
	}

	/**
	 *
	 * @return User tagged to this booking transaction
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 *
	 * @param customer User to tag to this booking transaction
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 *
	 * @return price of the entire booking
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *
	 * @param price booking price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}