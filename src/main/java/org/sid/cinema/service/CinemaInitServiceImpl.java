package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.CategoryRepository;
import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.dao.CityRepository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.ProjectionRepository;
import org.sid.cinema.dao.RoomRepository;
import org.sid.cinema.dao.SeatRepository;
import org.sid.cinema.dao.SessionRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Category;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.City;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Projection;
import org.sid.cinema.entities.Room;
import org.sid.cinema.entities.Seat;
import org.sid.cinema.entities.Session;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {

	@Autowired
	private CategoryRepository categoryRep;
	@Autowired
	private CinemaRepository cinemaRep;
	@Autowired
	private CityRepository cityRep;
	@Autowired
	private FilmRepository filmRep;
	@Autowired
	private ProjectionRepository projectionRep;
	@Autowired
	private RoomRepository roomRep;
	@Autowired
	private SeatRepository seatRep;
	@Autowired
	private SessionRepository sessionRep;
	@Autowired
	private TicketRepository ticketRep;

	@Override
	public void initCities() {
		Stream.of("City1", "City2", "City3", "City4", "City5").forEach(cityName -> {
			City city = new City();
			city.setName(cityName);
			cityRep.save(city);
		});
	}

	@Override
	public void initCinemas() {
		cityRep.findAll().forEach(city -> {
			Stream.of("Cinema1", "Cinema2", "Cinema3", "Cinema4").forEach(cinemaName -> {
				Cinema cinema = new Cinema();
				city.setName(cinemaName);
				cinema.setNumRooms(3 + (int) Math.random() * 7);
				cinema.setCity(city);
				cinemaRep.save(cinema);
			});

		});
	}

	@Override
	public void initRooms() {
		cinemaRep.findAll().forEach(cinema -> {
			for (int index = 0; index < cinema.getNumRooms(); index++) {
				Room room = new Room();
				room.setName("Room" + (index + 1));
				room.setCinema(cinema);
				room.setNumSeats(10 + (int) Math.random() * 10);
				roomRep.save(room);
			}
		});
	}

	@Override
	public void initSessions() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Stream.of("12:00:00", "15:00:00", "17:00:00", "19:00:00", "21:00:00").forEach(time -> {
			Session session = new Session();
			try {
				session.setStartTime(dateFormat.parse(time));
				sessionRep.save(session);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initSeats() {
		roomRep.findAll().forEach(room -> {
			for (int i = 0; i < room.getNumSeats(); i++) {
				Seat seat = new Seat();
				seat.setName("" + (i + 1));
				seat.setRoom(room);
				room.setNumSeats(15 + (int) (Math.random() * 20));
				seatRep.save(seat);
			}
		});
	}

	@Override
	public void initFilms() {
		double[] durees = new double[] { 1, 1.5, 2, 2.5, 3 };
		List<Category> categories = categoryRep.findAll();
		Stream.of("Film1", "Film2", "Film3", "Film4", "Film5", "Film6").forEach(filmTitle -> {
			Film film = new Film();
			film.setTitle(filmTitle);
			film.setLenght(durees[new Random().nextInt(durees.length)]);
			film.setCover(filmTitle.replaceAll(" ", ""));
			film.setCategory(categories.get(new Random().nextInt(categories.size())));
			filmRep.save(film);
		});
	}

	@Override
	public void initProjections() {
		double[] prices = new double[] { 30, 50, 60, 70, 90, 100 };
		List<Film> films = filmRep.findAll();
		cityRep.findAll().forEach(city -> {
			city.getCinemas().forEach(cinema -> {
				cinema.getRooms().forEach(room -> {
					int index = new Random().nextInt(films.size());
					Film film = films.get(index);
					sessionRep.findAll().forEach(session -> {
						Projection projection = new Projection();
						projection.setProjectionDate(new Date());
						projection.setFilm(film);
						projection.setPrice(prices[new Random().nextInt(prices.length)]);
						projection.setRoom(room);
						projection.setSession(session);
						projectionRep.save(projection);
					});
				});
			});
		});
	}

	@Override
	public void initTickets() {
		projectionRep.findAll().forEach(projection -> {
			projection.getRoom().getSeats().forEach(seat -> {
				Ticket ticket = new Ticket();
				ticket.setSeat(seat);
				ticket.setPrice(projection.getPrice());
				ticket.setProjection(projection);
				ticket.setTaken(false);
				ticketRep.save(ticket);
			});
		});
	}

	@Override
	public void initCategories() {
		Stream.of("Category1", "Category2", "Category3", "Category4", "Category5").forEach(categoryName -> {
			Category category = new Category();
			category.setName(categoryName);
			categoryRep.save(category);
		});
	}
}
