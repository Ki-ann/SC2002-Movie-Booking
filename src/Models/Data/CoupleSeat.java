package Models.Data;

import Models.Data.Enums.SeatType;

/**
 * Stores the data for a CoupleSeat object. CoupleSeat extends a regular Seat as a special variant of a Seat.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class CoupleSeat extends Seat {
    private CoupleSeat otherPair;

    /**
     * Constructor to create a Couple Seat, sets the type of seat to Couple
     * @param row Seat Row
     * @param col Seat Column
     */
    public CoupleSeat(int row, int col) {
        super(SeatType.COUPLE, row, col);
    }

    /**
     * Sets Couple Pair when deep copying
     * @param otherPair Set the other seat that is paired with this seat without calling the Superclass method
     */
    public void setCouplePair(CoupleSeat otherPair) {
        this.otherPair = otherPair;
    }

    /**
     * Sets seat assignment when deep copying
     * @param assigned Set the other seat to be assigned without calling the Superclass method
     */
    private void coupleHelpedAssignedSeat(boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * Get the seat string when deep copying
     * @return Gets the other seat string without calling the Superclass method
     */
    private String coupleGetString() {
        return this.seatString;
    }

    /**
     *
     * @return whether this seat is the CoupleSeat on the left
     */
    private boolean isLeftSideCouple() {
        return getColumn() < otherPair.getColumn();
    }

    /**
     * Overrides the super method of creating a deep copy of the seat.
     * @return a new copy of this couple seat
     */
    @Override
    public Seat deepCopy() {
        return new CoupleSeat(this.getRow(), this.getColumn());
    }

    /**
     * Overrides the super method of assigning this seat, and assign the couple seat too.
     * @param assigned assign or de-assign
     */
    @Override
    public void setAssigned(boolean assigned) {
        otherPair.coupleHelpedAssignedSeat(assigned);
        super.setAssigned(assigned);
    }

    /**
     * Overrides the super method of retrieving the Seat String to include the couple seat
     * @return String of seat row and column
     */
    @Override
    public String getSeatString() {
        if (isLeftSideCouple())
            return super.getSeatString() + " " + otherPair.coupleGetString();
        else {
            return otherPair.coupleGetString() + " " + super.getSeatString();

        }
    }
}
