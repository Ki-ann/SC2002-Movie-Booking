package Models.Data;

import Models.Data.Enums.CinemaType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the data for a Cinema object
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-11-03
 */
public class Cinema implements Serializable {

    private String name;
    private CinemaType cinemaType;
    private ArrayList<ArrayList<Seat>> seatTemplate;
    private final ArrayList<Screening> screeningList = new ArrayList<>();

    /**
     * Constructor for creating a cinema
     * @param cinemaName Name of the cinema
     * @param cinemaType Type of the cinema
     * @param seatTemplate 2D array of seats that act as the template to be copied for each screening
     */
    public Cinema(String cinemaName, CinemaType cinemaType, ArrayList<ArrayList<Seat>> seatTemplate) {
        this.name = cinemaName;
        this.cinemaType = cinemaType;
        this.seatTemplate = seatTemplate;
    }

    /**
     * @return List of movies currently showing in the cinema
     */
    public List<Movie> getMovieList() {
        return screeningList.stream().map(Screening::getMovie).toList();
    }

    /**
     * @param movie user selected movie
     * @return List of screenings containing the movie
     */
    public List<Screening> GetScreeningsByMovie(Movie movie) {
        return screeningList
                .stream()
                .filter(screening -> screening.getMovie().getName().equals(movie.getName()))
                .toList();
    }

    /**
     * @return Name of the cinema
     */
    public String getName() {
        return name;
    }

    /**
     * @return Type of Cinema
     */
    public CinemaType getCinemaType() {
        return cinemaType;
    }

    /**
     * Deep copies a new instance of the cinema seat template for seperate screening instances
     * @return 2D ArrayList of seats
     */
    public ArrayList<ArrayList<Seat>> getSeatTemplate() {

        ArrayList<ArrayList<Seat>> tempList = new ArrayList<>();
        for (int i = 0; i < seatTemplate.size(); ++i) {
            tempList.add(new ArrayList<>());
            var row = seatTemplate.get(i);
            for (int j = 0; j < row.size(); ++j) {
                var newSeat = row.get(j).deepCopy();
				tempList.get(i).add(newSeat);
                if (newSeat instanceof CoupleSeat) {
                    var otherPair = row.get(++j).deepCopy();
                    if (otherPair instanceof CoupleSeat) {
                        ((CoupleSeat) newSeat).setCouplePair((CoupleSeat) otherPair);
                        ((CoupleSeat) otherPair).setCouplePair((CoupleSeat) newSeat);
						tempList.get(i).add(otherPair);
                    }
                }
            }
        }
        return tempList;
    }

    /**
     * @return List of current screenings
     */
    public ArrayList<Screening> getScreeningList() {
        return screeningList;
    }

    /**
     * Adds a new screening into the current screening list, and copies the seat template
     * @param screening screening to add to the cinema
     */
    public void addToScreeningList(Screening screening) {
        screening.setSessionLayout(getSeatTemplate());
        screeningList.add(screening);
    }

    /**
     * Remove a Screening from the ScreeningList
     * @param screening the screening instance to remove
     * @return success or failure
     */
    public boolean removeScreeningList(Screening screening) {
        for (Screening i : screeningList) {
            if ((i.getShowTime()).getTimeOfMovie() == screening.getShowTime().getTimeOfMovie()) {
                screeningList.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * @param name New name of the cinema to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param cinemaType Sets the type of cinema
     */
    public void setCinemaType(CinemaType cinemaType) {
        this.cinemaType = cinemaType;
    }
}