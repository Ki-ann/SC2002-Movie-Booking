package Models.Data.Enums;

/**
 * Enumerator class for storing the various states in the booking process flow.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public enum BookingState {
    GET_MOVIES, GET_CINEPLEX, GET_DATE, GET_ALL_SHOWTIME, GET_SHOWTIME_DETAILED, GET_SEAT, GET_CUSTOMER_INFO, GET_CUSTOMER_AGE, GET_CONFIRMATION, FINISHED;

    /**
     * Cache of Enum.values() array.
     */
    static final BookingState[] VALUES = values();

    /**
     * Provides the next enum from the current enum based on the ordinal order.
     * @return next booking state enum from the current.
     */
    public BookingState next() {
        return VALUES[Math.min(ordinal() + 1, VALUES.length - 1)];
    }

    /**
     * Provides the previous enum from the current enum based on the ordinal order.
     * @return previous booking state enum from the current.
     */
    public BookingState prev() {
        return VALUES[Math.max(ordinal() - 1, 0)];
    }
}

