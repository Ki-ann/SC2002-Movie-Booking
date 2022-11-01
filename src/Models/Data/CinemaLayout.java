package Models.Data;

import Models.Data.Enums.SeatType;
import java.io.Serializable;

public class CinemaLayout implements Serializable {

	private SeatType seatType;
	private boolean assigned;
	private int row;
	private int column;
	private final String seatString;
	public CinemaLayout(SeatType seatType,int row, int col){
		this.seatType = seatType;
		this.row = row;
		this.column = col;
		this.seatString = String.format ("%c%d",(char)(this.row+65), this.column);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public String getSeatString() {
		return seatString;
	}
}