package Controllers;

import Models.Data.Cinema;
import Models.Data.Cineplex;
import Models.Data.Enums.CinemaType;
import Models.Data.Movie;
import Models.DataStoreManager;

import javax.xml.crypto.Data;
import java.util.ArrayList;

/**
 * Cineplex Controller is a Navigation that manages the logic and flow for Cineplex functions.
 *
 * @author
 * @version 1.0
 * @since 2022-10-28
 */
public class CineplexController implements INavigation {

    /**
     * Start method implementation for initialization after loading with NavigationController.
     *
     * @see NavigationController
     * @see INavigation
     */
    public void start() {
    }

    /**
     * Retrieves the cineplex list from DataStore.
     * @see DataStoreManager
     * @return Arraylist of Cineplex in the DataStore.
     */
    public ArrayList<Cineplex> getCineplexList() {
        return DataStoreManager.getInstance().getStore(Cineplex.class);
    }

    /**
     * Filters the list of cineplex with the given movie.
     * @param movie the movie to filter for.
     * @return An ArrayList of Cineplex which currently have a screening for the given movie.
     */
    public ArrayList<Cineplex> findCineplexAndCinemaWithSelectedMovie(Movie movie){
        ArrayList<Cineplex> cineplexList = new ArrayList<>();
        // Foreach cineplex
        for (Cineplex cine : getCineplexList()) {
            // If the selected movie appears in one of the cinemas, add to list
            if(cine.getCinemasWithMovie(movie).size() > 0){
                cineplexList.add(cine);
            }
        }
        return cineplexList;
    }

}