package Models.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import Models.DataStoreManager;
import Models.Data.Enums.*;

import static java.util.Map.entry;

public class Setting implements Serializable {
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

    private void saveSettings() {
        DataStoreManager.getInstance().save(Setting.class);
    }

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

    public double getStandardPrice() {
        return roundNearest50Cents(standardPrice);
    }

    public double getStandardPrice(AgeClass ageClass) {
        return roundNearest50Cents(ageMultiplier.get(ageClass) * getStandardPrice());
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
        saveSettings();
    }

    public double getWeekendPrice() {
        return roundNearest50Cents(getStandardPrice() * weekendMultiplier);
    }

    private double roundNearest50Cents(double input) {
        return Math.round(input * 2.0) / 2.0;
    }

    public double getPrice(BookingTicket newBookingTicket) {
        var dateOfMovie = newBookingTicket.getSelectedScreening().getShowTime().getDateOfMovie();
        var holiday = getHoliday(dateOfMovie);
        double specialDay = 1.0;
        if(holiday != null){
            specialDay =  holiday.getRate();
        }else if(isWeekend(dateOfMovie))
            specialDay = weekendMultiplier;

        return roundNearest50Cents(getStandardPrice(newBookingTicket.getCustomer().getAgeClass())
                * movieTypeMultiplier.get(newBookingTicket.getSelectedMovie().getMovieType())
                * cinemaClassMultiplier.get(newBookingTicket.getSelectedCinema().getCinemaType())
                * seatTypeMultiplier.get(newBookingTicket.getSelectedSeat().getSeatType())
                * specialDay
        );
    }

    //This method is used to get the holiday with specified
    private Holiday getHoliday(LocalDate selectedDate) {
        ArrayList<Holiday> holidayList = Holiday.getHolidayList();
        return holidayList.stream().filter(holiday -> holiday.getDate() == selectedDate).findFirst().orElse(null);

    }

    private boolean isWeekend(LocalDate selectedDate) {
        return selectedDate.getDayOfWeek() == DayOfWeek.SATURDAY || selectedDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public TopMovieViewingState getCurrentTopMovieViewingState() {
        return currentTopMovieViewingState;
    }

    public void setCurrentTopMovieViewingState(TopMovieViewingState currentTopMovieViewingState) {
        this.currentTopMovieViewingState = currentTopMovieViewingState;
    }
}