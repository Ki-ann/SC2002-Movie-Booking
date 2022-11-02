import Controllers.CineplexController;
import Controllers.MainController;
import Controllers.NavigationController;
import Models.Data.*;
import Models.Data.Enums.CinemaType;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class MainTestKiann {

    public static void main(String[] args) {
        //Test file
        System.out.println("Application Starting...");
        // Load DB
        Admin obj = new Admin("admin", "admin");
        DataStoreManager.getInstance().addToStore(obj);
        Movie mov = new Movie();
        mov.setName("movie 1");

        CineplexController cineplexController = new CineplexController();
        Cineplex cine = new Cineplex();
        cine.setName("Bishan");
        cine.addCinemaToCineplex(CinemaType.NORMAL);
        cine.addCinemaToCineplex(CinemaType.NORMAL);

        Screening screening = new Screening();
        screening.setMovie(mov);
        screening.setShowTime(new ShowTime());
        screening.getShowTime().setTimeOfMovie(LocalTime.NOON);
        screening.getShowTime().setDateOfMovie(LocalDate.now());
        Screening screening2 = new Screening();
        screening2.setMovie(mov);
        screening2.setShowTime(new ShowTime());
        screening2.getShowTime().setTimeOfMovie(LocalTime.NOON.plusHours(3));
        screening2.getShowTime().setDateOfMovie(LocalDate.now());
        cine.getCinemaByIndex(0).addToScreeningList(screening);
        cine.getCinemaByIndex(0).addToScreeningList(screening2);
        cine.getCinemaByIndex(1).addToScreeningList(screening);

        DataStoreManager.getInstance().addToStore(cine);
        DataStoreManager.getInstance().addToStore(mov);
        mov = new Movie();
        mov.setName("movie 2");
        mov.setMovieStatus(MovieStatus.NOW_SHOWING);
        String newTID = String.format("%s%ty%<tm%<td%tH%<tM%<tS","BI1", LocalDate.now(), LocalTime.now());

        DataStoreManager.getInstance().addToStore(new DiscountCode("DISC", 0.3));
        DataStoreManager.getInstance().addToStore(mov);
        DataStoreManager.getInstance().loadAll();
//        ArrayList<Admin> adminList = DataStoreManager.getInstance().getStore(Admin.class);
//        DataStoreManager.getInstance().saveAll();
        // Load Initial Controller
        NavigationController.getInstance().load(new MainController());

    }


}

