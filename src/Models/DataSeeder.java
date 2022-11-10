package Models;

import Models.Data.*;
import Models.Data.Enums.CinemaType;
import Models.Data.Enums.MovieRating;
import Models.Data.Enums.MovieStatus;
import Models.Data.Enums.MovieType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DataSeeder {

    public static void initializeData(){
        if(!DataStoreManager.getInstance().isEmptyDataFolder()){
            return;
        }

        seedAdmin();
        seedCineplex();
        seedDiscounts();
    }

    private static void seedDiscounts() {
        DataStoreManager.getInstance().addToStore(new DiscountCode("DISC", 0.3));
        DataStoreManager.getInstance().addToStore(new DiscountCode("DISC2", 0.5));

    }

    private static void seedCineplex() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie mov1 = new Movie();
        mov1.setName("Black Adam (English Sub)");
        mov1.setCast(new ArrayList<>(Arrays.asList("Dwayne Johnson", "Aldis Hodge", "Pierce Brosnan", "Noah Centineo", "Sarah Shahi"," Marwan Kenzari"," Quintessa Swindell", "Bodhi Sabongui")));
        mov1.setDirector(new ArrayList<>(Arrays.asList("Jaume Collet-Serra")));
        mov1.setSynopsis("Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.");
        mov1.setMovieGenre(new ArrayList<>(Arrays.asList("Action", "Adventure", "Fantasy")));
        mov1.setLanguage("English");
        mov1.setMovieType(MovieType.LOCAL);
        mov1.setMovieRating(MovieRating.PG13);
        mov1.setDuration(0,125,0);
        mov1.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov1);

        Movie mov2 = new Movie();
        mov2.setName("Black Adam (English Sub)");
        mov2.setCast(new ArrayList<>(Arrays.asList("Dwayne Johnson", "Aldis Hodge", "Pierce Brosnan", "Noah Centineo", "Sarah Shahi"," Marwan Kenzari"," Quintessa Swindell", "Bodhi Sabongui")));
        mov2.setDirector(new ArrayList<>(Arrays.asList("Jaume Collet-Serra")));
        mov2.setSynopsis("Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.");
        mov2.setMovieGenre(new ArrayList<>(Arrays.asList("Action", "Adventure", "Fantasy")));
        mov2.setLanguage("English");
        mov2.setMovieRating(MovieRating.PG13);
        mov2.setDuration(0,125,0);
        mov2.setMovieType(MovieType.IMAX3D);
        mov2.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov2);

        Movie mov3 = new Movie();
        mov3.setName("Deleted");
        mov3.setCast(new ArrayList<>(Arrays.asList("Jack Neo","Fattah Amin","Zheng Ge Ping","Vincent Ng","Dato Rosyam Nor","Tien Hsin","Zhu Hou Ren","Henley Hii","Pablo Amirul")));
        mov3.setDirector(new ArrayList<>(Arrays.asList("Ken Ng Lai Huat")));
        mov3.setSynopsis("The story starts with a Malaysian police detective - Chia Zhong Yi. In his desperate search for his daughter Hazel who was being kidnapped by child traffickers. He unintentionally caused grievous hurt to a male suspect in a moment of rashness. As a consequence of his actions, he was convicted and sentenced to 3 years in prison. Nevertheless, he never gave up hope in finding his daughter. Exploiting his status as an ex-convict, he infiltrated the crime syndicate and befriended a human trafficker Ghost, to find out about his daughter’s whereabouts. On the other side of the fence, we have Vincent Yong who was part of the Singapore Police Force - Star Team. He leads a successful raid against Four Faced Buddha, but was unable to apprehend him and his son. Meanwhile, in order to gather a large quantity of human organs for trafficking, Four Faced Buddha instructed his son, a dangerous hacker who goes by the name of Saviour, to steal the medical records from all the major hospitals in the regions. To escape from detection by the Interpol, Savior has remained hidden overseas, and it wasn’t until 3 years later that he was caught by the Malaysia Police Inspector Aron. Vincent was being ordered to extradite Savior back to Singapore and to persuade him to turn as a key prosecuting witness against Four Faced Buddha. At the same time, Ghost has been ordered by his boss Four Faced Buddha to rescue his son Savior during the extradition process. The loyalties of these few men are being severely tested. In an intense gun fight, Ghost was killed by Zhong Yi and his identity came under the suspicion of Vincent, creating conflicts between the two of them. In a wicked twist of fate, Vincent also accidentally discovers that his former Star Team instructor Jusuf whom he deeply respects has broken the law, and exploited the sting operation as a decoy to steal a smuggled heart to save his very own sick daughter.");
        mov3.setMovieGenre(new ArrayList<>(Arrays.asList("Action","Crime","Drama")));
        mov3.setLanguage("English, Mandarin, Malay");
        mov3.setMovieRating(MovieRating.NC16);
        mov3.setDuration(0,86 ,0);
        mov3.setMovieType(MovieType.LOCAL);
        mov3.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov3);

        Movie mov4 = new Movie();
        mov4.setName("Talbis Iblis");
        mov4.setCast(new ArrayList<>(Arrays.asList("Zul Ariffin","Azira Shafinaz","Nasha Aziz","Amir Nafis","Jay Iswazir")));
        mov4.setDirector(new ArrayList<>(Arrays.asList("Khabir Bhatia")));
        mov4.setSynopsis("To protect their family’s name from being scarred, Hajar who is pregnant out of wedlock, is being forced by her mother to have the baby far away from Kuala Lumpur. After several tries to aborting the child, her baby is alive, but now with a cleft lip, which only caused Hajar to further despise her unborn child. Hajar and her husband Arshad meet a midwife, Mak Ju who specializes in providing the best care before and after childbirth to fulfil her family’s request. After spending a few days under her care, Hajar finds in Mak Ju the mother she never had – someone loving and motherly. Eventually, Hajar begins to feel love for her baby, inspired by the motherly love Mak Ju had shown to Hajar herself. But unbeknownst to Hajar, Mak Ju’s ill intentions would soon come to harm Hajar and her precious child.");
        mov4.setMovieGenre(new ArrayList<>(Arrays.asList("Horror")));
        mov4.setLanguage("Malay");
        mov4.setMovieRating(MovieRating.NC16);
        mov4.setDuration(0,98 ,0);
        mov4.setMovieType(MovieType.LOCAL);
        mov4.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov4);

        Movie mov5 = new Movie();
        mov5.setName("Talbis Iblis");
        mov5.setCast(new ArrayList<>(Arrays.asList("Zul Ariffin","Azira Shafinaz","Nasha Aziz","Amir Nafis","Jay Iswazir")));
        mov5.setDirector(new ArrayList<>(Arrays.asList("Khabir Bhatia")));
        mov5.setSynopsis("To protect their family’s name from being scarred, Hajar who is pregnant out of wedlock, is being forced by her mother to have the baby far away from Kuala Lumpur. After several tries to aborting the child, her baby is alive, but now with a cleft lip, which only caused Hajar to further despise her unborn child. Hajar and her husband Arshad meet a midwife, Mak Ju who specializes in providing the best care before and after childbirth to fulfil her family’s request. After spending a few days under her care, Hajar finds in Mak Ju the mother she never had – someone loving and motherly. Eventually, Hajar begins to feel love for her baby, inspired by the motherly love Mak Ju had shown to Hajar herself. But unbeknownst to Hajar, Mak Ju’s ill intentions would soon come to harm Hajar and her precious child.");
        mov5.setMovieGenre(new ArrayList<>(Arrays.asList("Horror")));
        mov5.setLanguage("Malay");
        mov5.setMovieRating(MovieRating.NC16);
        mov5.setDuration(0,98 ,0);
        mov5.setMovieType(MovieType.BLOCKBUSTER);
        mov5.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov5);

        Movie mov6 = new Movie();
        mov6.setName("The Quintessential Quintuplets");
        mov6.setCast(new ArrayList<>(Arrays.asList("Yoshitsugu Matsuoka","Inori Minase","Ayana Taketatsu","Ayane Sakura","Kana Hanazawa","Miku Ito")));
        mov6.setDirector(new ArrayList<>(Arrays.asList("Masato Jinbo")));
        mov6.setSynopsis("Hired as a private tutor to the five identical quintuplets for the Nakano's family, high school student Futaro Uesugi has led the quintuplets: Ichika, Nino, Miku, Yotsuba and Itsuki Nakano to a point where they can graduate and pursue their own dreams. At last, it is the final school festival and Futaro resolved to make this an occasion that they will not regret. He searched within himself for his feelings towards the quintuplets and invites them to meet him in the classroom for an important announcement… NOTE: This movie falls under special-priced ticketing. Please refer to the respective showtime for the price of each ticket. Student/senior privileges, complimentary passes, movie vouchers, member or bank card discounts are not applicable.");
        mov6.setMovieGenre(new ArrayList<>(Arrays.asList("Anime")));
        mov6.setLanguage("Japanese");
        mov6.setMovieRating(MovieRating.PG);
        mov6.setDuration(0,136 ,0);
        mov6.setMovieType(MovieType.BLOCKBUSTER);
        mov6.setMovieStatus(MovieStatus.NOW_SHOWING);
        movies.add(mov6);

        for (Movie movie:movies) {
            DataStoreManager.getInstance().addToStore(movie);
        }

        Cineplex cine = new Cineplex();
        cine.setName("Bishan");
        cine.addCinemaToCineplex(CinemaType.NORMAL);
        cine.addCinemaToCineplex(CinemaType.NORMAL);
        cine.addCinemaToCineplex(CinemaType.PLATINUM);
        cine.addCinemaToCineplex(CinemaType.IMAX);


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                Screening screening = new Screening();
                screening.setMovie(mov1);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(7,0).plusHours(j * 3));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(0).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov2);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(7,0).plusHours(j * 4));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(3).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov3);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(6,45).plusHours(j * 3));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(1).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov4);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(6,45).plusHours(j * 4));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(2).addToScreeningList(screening);

            }
        }

        DataStoreManager.getInstance().addToStore(cine);

        cine = new Cineplex();
        cine.setName("Ang Mo Kio");
        cine.addCinemaToCineplex(CinemaType.NORMAL);
        cine.addCinemaToCineplex(CinemaType.PLATINUM);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                Screening screening = new Screening();
                screening.setMovie(mov1);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(7,0).plusHours(j * 4));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(0).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov5);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(6,45).plusHours(j * 2));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(1).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov6);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(6,45).plusHours(j * 5));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(1).addToScreeningList(screening);

            }
        }
        DataStoreManager.getInstance().addToStore(cine);


        cine = new Cineplex();
        cine.setName("NEX");
        cine.addCinemaToCineplex(CinemaType.NORMAL);
        cine.addCinemaToCineplex(CinemaType.PLATINUM);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                Screening screening = new Screening();
                screening.setMovie(mov5);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(7,0).plusHours(j * 4));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(0).addToScreeningList(screening);

                screening = new Screening();
                screening.setMovie(mov6);
                screening.setShowTime(new ShowTime());
                screening.getShowTime().setTimeOfMovie(LocalTime.of(7,0).plusHours(j * 4));
                screening.getShowTime().setDateOfMovie(LocalDate.now().plusDays(i));
                cine.getCinemaByIndex(1).addToScreeningList(screening);
            }
        }
        DataStoreManager.getInstance().addToStore(cine);
    }

    private static void seedAdmin() {
        Admin admin1 = new Admin("admin", "admin");
        Admin admin2 = new Admin("admin2", "admin");
        DataStoreManager.getInstance().addToStore(admin1);
        DataStoreManager.getInstance().addToStore(admin2);
    }
}
