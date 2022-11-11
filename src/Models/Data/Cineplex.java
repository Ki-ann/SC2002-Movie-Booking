package Models.Data;

import Models.Data.Enums.CinemaType;
import Models.DataStoreManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the data for a Cineplex object
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-11
 */
public class Cineplex implements Serializable {

    private final ArrayList<Cinema> cinemaList = new ArrayList<>();
    private String name;

    /**
     * @return List of Cinemas in this Cineplex
     */
    public ArrayList<Cinema> getCinemaList() {
        return cinemaList;
    }

    /**
     * Filters the Cinema list and finds any Cinemas that have screenings for the selected movie
     * @param movie user selected Movie
     * @return filtered list of Cinemas
     */
    public List<Cinema> getCinemasWithMovie(Movie movie) {
        return getCinemaList()
                // Foreach Cinema...
                .stream()
                // Find those Cinemas with the selected movie
                .filter(Cinema -> Cinema.getMovieList()
                        // Foreach movie screening in the cinema
                        .stream()
                        // Find the screenings which match
                        .anyMatch(mov -> mov.equals(movie)))
                .toList();
    }

    /**
     * Adds a new Cinema to the Cineplex, and sets the seat template accordingly to the Cinema Type
     * @param cinemaType Type of Cinema to add
     */
    public void addCinemaToCineplex(CinemaType cinemaType) {
        String cinemaCode = (this.getName().length() >= 2 ?
                this.getName().substring(0, 2) + (this.getCinemaList().size() + 1) :
                this.getName() + "0" + (this.getCinemaList().size() + 1)).toUpperCase();
        ArrayList<ArrayList<Seat>> seatTemplate = null;
        String seatString = "";
        switch (cinemaType) {
            case NORMAL -> {
                seatString = "XXXXX0000XXXXX." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "XCCXXXCCXXXCCX";
            }
            case PLATINUM -> {
                seatString = "XXXXXX0000XXXXXX." +
                        "CCXCCX0000XCCXCC." +
                        "XXXXXX0000XXXXXX." +
                        "CCXCCX0000XCCXCC." +
                        "XXXXXX0000XXXXXX." +
                        "CCXCCX0000XCCXCC";
            }
            case IMAX -> {
                seatString = "XXXXX0000XXXXX." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000." +
                        "0000X0000X0000";
            }
        }
        seatTemplate = DataStoreManager.getInstance().parseLayout(seatString);
        Cinema newCinema = new Cinema(cinemaCode, cinemaType, seatTemplate);
        this.getCinemaList().add(newCinema);
        DataStoreManager.getInstance().save(Cineplex.class);
    }

    /**
     * Retrieves the Cinema from the cinema list via its index
     * @param index index to select
     * @return Cinema by index
     */
    public Cinema getCinemaByIndex(int index) {
        return cinemaList.get(index);
    }

    /**
     * @return Name of the Cineplex
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Sets new name of the Cineplex
     */
    public void setName(String name) {
        this.name = name;
    }
}