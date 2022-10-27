import Controllers.MainController;
import Controllers.NavigationController;
import Models.Data.*;
import Models.Data.Enums.MovieStatus;
import Models.DataStoreManager;

import java.time.LocalDate;
import java.time.LocalTime;

class MainTestKiann {

	public static void main(String[] args) {
		//Test file
		System.out.println("Application Starting...");
		// Load DB
		Admin obj = new Admin();
        DataStoreManager.getInstance().AddToStore(obj);
		DataStoreManager.getInstance().AddToStore(obj);
		Movie mov = new Movie();
		mov.setName("movie 1");

		Cineplex cine = new Cineplex();
			cine.setName("Cineplex Bishan");
			Cinema cinema = new Cinema();
			cinema.setName("Cinema 1");
			cine.cinemaList.add(cinema);
			cinema = new Cinema();
			cinema.setName("Cinema 2");
			cine.cinemaList.add(cinema);
			Screening screening = new Screening();
			screening.movie = mov;
			screening.showTime = new ShowTime();
			screening.showTime.timeOfMovie = LocalTime.NOON;
			screening.showTime.dateOfMovie = LocalDate.now();
			Screening screening2 = new Screening();
			screening2.movie = mov;
			screening2.showTime = new ShowTime();
			screening2.showTime.timeOfMovie = LocalTime.NOON.plusHours(3);
			screening2.showTime.dateOfMovie = LocalDate.now();
			cine.GetCinema(0).screeningList.add(screening);
			cine.GetCinema(0).screeningList.add(screening2);
			cine.GetCinema(1).screeningList.add(screening);

		DataStoreManager.getInstance().AddToStore(cine);
		DataStoreManager.getInstance().AddToStore(mov);
		mov = new Movie();
		mov.setName("movie 2");
		mov.setMovieStatus(MovieStatus.NOW_SHOWING);
		DataStoreManager.getInstance().AddToStore(mov);
		DataStoreManager.getInstance().LoadAll();
		//ArrayList<Admin> adminList = DataStoreManager.getInstance().GetStore(Admin.class);
		//DataStoreManager.getInstance().SaveAll();
		// Load Initial Controller
		NavigationController.getInstance().Load(new MainController());

	}
}

