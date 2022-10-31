package Controllers;

import Models.Data.Cinema;
import Models.Data.Cineplex;
import Models.Data.Movie;
import Models.Data.Screening;
import Models.DataStoreManager;

import java.util.ArrayList;
import java.util.List;

public class CineplexController implements INavigation {

    @Override
    public void Start() {
        ArrayList<Cineplex> e = GetCineplexList();
    }

    public ArrayList<Cineplex> GetCineplexList() {
        return DataStoreManager.getInstance().GetStore(Cineplex.class);
    }

    public ArrayList<Cineplex> FindCineplexAndCinemaWithSelectedMovie(Movie movie){
        ArrayList<Cineplex> cineplexList = new ArrayList<>();
        // Foreach cineplex
        for (Cineplex cine : GetCineplexList()) {
            // If the selected movie appears in one of the cinemas, add to list
            if(cine.GetFilteredCinemaList(movie).size() > 0){
                cineplexList.add(cine);
            }
        }
        return cineplexList;
    }

}