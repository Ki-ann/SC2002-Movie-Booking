package Models.Data;

import Models.Data.Enums.SeatType;
import java.io.Serializable;

/**
 * Stores the data for a Base Seat object.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class Seat implements Serializable {

	private SeatType seatType;
	private int row;
	private int column;
	protected boolean assigned;
	protected final String seatString;

	/**
	 * Constructor to create a new seat
	 * @param seatType The type of seat
	 * @param row Seat Row
	 * @param col Seat Column
	 */
	public Seat(SeatType seatType, int row, int col){
		this.seatType = seatType;
		this.row = row;
		this.column = col;
		this.seatString = String.format ("%c%d",(char)(this.row+65), this.column);
	}

	/**
	 * Constructor to copy a seat
	 * @param seat seat to copy
	 */
	public Seat(Seat seat) {
		this.seatType = seat.getSeatType();
		this.row = seat.getRow();
		this.column = seat.getColumn();
		this.seatString = seat.getSeatString();
	}

	/**
	 * Deep Copies a seat to create a new instance
	 * @return new instance of a copied seat
	 */
	public Seat deepCopy(){
		return new Seat(this);
	}

	/**
	 * @return Get Row number
	 */
    public int getRow() {
		return row;
	}

	/**
	 * @return Get Column number
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return Get Seat type
	 */
	public SeatType getSeatType() {
		return seatType;
	}

	/**
	 * @return Get if seat is assigned
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * @return Get seat string e.g F10.
	 */
	public String getSeatString() {
		return seatString;
	}


	/**
	 * Set the seat to be assigned and occupied
	 * @param assigned assign or de-assign seat
	 */
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}