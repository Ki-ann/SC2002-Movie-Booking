import Controllers.MainController;
import Controllers.NavigationController;
import Models.DataStoreManager;

class MainTestKiann {

    public static void main(String[] args) {
        //Test file
        System.out.println("Application Starting...");
        // Load DB
//        Admin obj = new Admin();
//        DataStoreManager.getInstance().addToStore(obj);
//        DataStoreManager.getInstance().addToStore(obj);
//        Movie mov = new Movie();
//        mov.setName("movie 1");
//
//        Cineplex cine = new Cineplex();
//        cine.setName("Cineplex Bishan");
//        Cinema cinema = new Cinema();
//        cinema.setName("Cinema 1");
//        String seatString = "XXXXX0000XXXXXX." +
//                "0000X0000X00000." +
//                "0000X0000X00000." +
//                "0000X0000X00000." +
//                "0000X0000X00000." +
//                "0000X0000X00000." +
//                "0000X0000X00000." +
//                "0000X0000X00000";
//        //String seatString = "0";
//		ArrayList<ArrayList<CinemaLayout>> layout = DataStoreManager.getInstance().parseLayout(seatString);
//        cinema.setSeatTemplate(layout);
//		//BookingView.PrintSeatLayout(layout);
//
//
//        cine.getCinemaList().add(cinema);
//        cinema = new Cinema();
//        cinema.setName("Cinema 2");
//        cine.getCinemaList().add(cinema);
//        cinema.setSeatTemplate(layout);
//        Screening screening = new Screening();
//        screening.setMovie(mov);
//        screening.setShowTime(new ShowTime());
//        screening.getShowTime().timeOfMovie = LocalTime.NOON;
//        screening.getShowTime().dateOfMovie = LocalDate.now();
//        Screening screening2 = new Screening();
//        screening2.setMovie(mov);
//        screening2.setShowTime(new ShowTime());
//        screening2.getShowTime().timeOfMovie = LocalTime.NOON.plusHours(3);
//        screening2.getShowTime().dateOfMovie = LocalDate.now();
//        cine.getCinemaByIndex(0).addToScreeningList(screening);
//        cine.getCinemaByIndex(0).addToScreeningList(screening2);
//        cine.getCinemaByIndex(1).addToScreeningList(screening);
//
//        DataStoreManager.getInstance().addToStore(cine);
//        DataStoreManager.getInstance().addToStore(mov);
//        mov = new Movie();
//        mov.setName("movie 2");
//        mov.setMovieStatus(MovieStatus.NOW_SHOWING);
//        DataStoreManager.getInstance().addToStore(mov);
        DataStoreManager.getInstance().loadAll();
//        ArrayList<Admin> adminList = DataStoreManager.getInstance().getStore(Admin.class);
//        DataStoreManager.getInstance().saveAll();
        // Load Initial Controller
        NavigationController.getInstance().load(new MainController());

    }


}

