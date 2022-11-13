package Models.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import Models.DataStoreManager;
import Models.Data.Enums.*;

import static java.util.Map.entry;

/**
 * Stores the data for a Setting object.
 *
 * @author Han Zhiguang, Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class Setting implements Serializable {

    private Admin currentAdmin;

    private double standardPrice = 10;
    private TopMovieViewingState currentTopMovieViewingState = TopMovieViewingState.BY_BOTH;
    private final Map<AgeClass, Double> ageMultiplier = Map.ofEntries(
            entry(AgeClass.ADULT, 1.0),
            entry(AgeClass.STUDENT, 0.75),
            entry(AgeClass.SENIOR, 0.45),
            entry(AgeClass.CHILD, 0.30)
    );

    private final Map<MovieType, Double> movieTypeMultiplier = Map.ofEntries(
            entry(MovieType.LOCAL, 1.0),
            entry(MovieType.BLOCKBUSTER, 1.1),
            entry(MovieType.DIGITAL3D, 1.1),
            entry(MovieType.IMAX3D, 1.2)
    );

    private final Map<CinemaType, Double> cinemaClassMultiplier = Map.ofEntries(
            entry(CinemaType.NORMAL, 1.0),
            entry(CinemaType.PLATINUM, 1.1),
            entry(CinemaType.IMAX, 1.2)
    );

    private final Map<SeatType, Double> seatTypeMultiplier = Map.ofEntries(
            entry(SeatType.NORMAL, 1.0),
            entry(SeatType.SPECIAL_NEEDS, 1.0),
            entry(SeatType.COUPLE, 2.0)
    );
    private final double weekendMultiplier = 1.2;

    /**
     * Static method to retrieve the first and only setting object in the DataStore
     * @return current Setting object
     */
    public static Setting getSettings() {
        DataStoreManager dataStoreInstance = DataStoreManager.getInstance();
        ArrayList<Setting> settingStore = dataStoreInstance.getStore(Setting.class);
        if (settingStore.size() == 0) {
            Setting newSettings = new Setting();
            dataStoreInstance.addToStore(newSettings);
            return newSettings;
        }
        // Get the only stored setting object
        return settingStore.get(0);
    }

    /**
     * Saves the settings in the DataStore
     */
    private void saveSettings() {
        DataStoreManager.getInstance().save(Setting.class);
    }


    /**
     * @return Get Standard Ticket Price
     */
    public double getStandardPrice() {
        return roundNearest50Cents(standardPrice);
    }

    /**
     * Get Standard Ticket Price by Age class
     * @param ageClass user selected Age class
     * @return Standard Price * age multiplier, rounded off to the nearest 50 cents
     */
    public double getStandardPrice(AgeClass ageClass) {
        return roundNearest50Cents(ageMultiplier.get(ageClass) * getStandardPrice());
    }

    /**
     * @return Standard Price * weekend multiplier, rounded off to the nearest 50 cents
     */
    public double getWeekendPrice() {
        return roundNearest50Cents(getStandardPrice() * weekendMultiplier);
    }

    /**
     * Get final price of the current booking by all factors
     * @param newBookingTicket booking session
     * @return final price of booking
     */
    public double getPrice(BookingTicket newBookingTicket) {
        var dateOfMovie = newBookingTicket.getSelectedScreening().getShowTime().getDateOfMovie();
        var holiday = getHoliday(dateOfMovie);
        double specialDay = 1.0;
        if(holiday != null){
            specialDay =  (holiday.getRate() / 100) + 1;
        }else if(isWeekend(dateOfMovie))
            specialDay = weekendMultiplier;

        double sum = 0;
        for(Seat seat : newBookingTicket.getSelectedSeats()){
            sum += roundNearest50Cents(getStandardPrice(newBookingTicket.getCustomer().getAgeClass())
                    * movieTypeMultiplier.get(newBookingTicket.getSelectedMovie().getMovieType())
                    * cinemaClassMultiplier.get(newBookingTicket.getSelectedCinema().getCinemaType())
                    * seatTypeMultiplier.get(seat.getSeatType())
                    * specialDay
            );
        }

        return sum;
    }

    /**
     * Get the holiday of the selected Date
     * @param selectedDate User selected Date
     * @return Holiday if found, null otherwise
     */
    private Holiday getHoliday(LocalDate selectedDate) {
        ArrayList<Holiday> holidayList = Holiday.getHolidayList();
        return holidayList.stream().filter(holiday -> holiday.getDate().isEqual(selectedDate)).findFirst().orElse(null);

    }

    /**
     * @return Get the current admin user instance
     */
    public Admin getCurrentAdmin(){
        return this.currentAdmin;
    }

    /**
     * @return Get the current Top Movie Viewing State
     */
    public TopMovieViewingState getCurrentTopMovieViewingState() {
        return currentTopMovieViewingState;
    }


    /**
     * Set the new standard price and save settings
     * @param standardPrice new Price to set
     */
    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
        saveSettings();
    }

    /**
     * @param currentTopMovieViewingState Set the current Top Movie Viewing State
     */
    public void setCurrentTopMovieViewingState(TopMovieViewingState currentTopMovieViewingState) {
        this.currentTopMovieViewingState = currentTopMovieViewingState;
    }

    /**
     * @param admin Set the current admin user instance
     */
    public void setCurrentAdmin(Admin admin) {
        this.currentAdmin = admin;
    }


    /**
     * Check if the selected date is a weekend
     * @param selectedDate User selected Date
     * @return True if the date is a weekend, False if it is not
     */
    private boolean isWeekend(LocalDate selectedDate) {
        return selectedDate.getDayOfWeek() == DayOfWeek.SATURDAY || selectedDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Round input to nearest 50 cents
     * @param input input price to round off
     * @return new rounded price
     */
    private double roundNearest50Cents(double input) {
        return Math.round(input * 2.0) / 2.0;
    }
}