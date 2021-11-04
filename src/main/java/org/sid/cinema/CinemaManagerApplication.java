package org.sid.cinema;

import org.sid.cinema.entities.Film;
import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaManagerApplication implements CommandLineRunner{

	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class);
		cinemaInitService.initCities();
		cinemaInitService.initCinemas();
		cinemaInitService.initRooms();
		cinemaInitService.initSeats();
		cinemaInitService.initSessions();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
	}

}
