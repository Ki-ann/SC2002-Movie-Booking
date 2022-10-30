package Models.Data.Enums;

public enum BookingState {
    GET_MOVIES, GET_CINEPLEX, GET_DATE, GET_ALL_SHOWTIME, GET_SHOWTIME_DETAILED, GET_SEAT, GET_CUSTOMER_INFO, GET_CONFIRMATION, FINISHED;

    static final Models.Data.Enums.BookingState[] VALUES = values();

    public Models.Data.Enums.BookingState next() {
        return VALUES[Math.min(ordinal() + 1, VALUES.length - 1)];
    }

    public Models.Data.Enums.BookingState prev() {
        return VALUES[Math.max(ordinal() - 1, 0)];
    }
}

