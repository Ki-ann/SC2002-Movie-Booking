package Models.Data;

import Models.Data.Enums.SeatType;

public class CoupleSeat extends Seat {
    private CoupleSeat otherPair;

    public CoupleSeat(int row, int col) {
        super(SeatType.COUPLE, row, col);
    }

    public void setCouplePair(CoupleSeat otherPair) {
        this.otherPair = otherPair;
    }

    private void coupleHelpedAssignedSeat(boolean assigned) {
        this.assigned = assigned;
    }

    private String coupleGetString() {
        return this.seatString;
    }

    private boolean isLeftSideCouple() {
        return getColumn() < otherPair.getColumn();
    }

    @Override
    public Seat deepCopy() {
        return new CoupleSeat(this.getRow(), this.getColumn());
    }

    @Override
    public void setAssigned(boolean assigned) {
        otherPair.coupleHelpedAssignedSeat(assigned);
        super.setAssigned(assigned);
    }

    @Override
    public String getSeatString() {
        if (isLeftSideCouple())
            return super.getSeatString() + " " + otherPair.coupleGetString();
        else {
            return otherPair.coupleGetString() + " " + super.getSeatString();

        }
    }
}
