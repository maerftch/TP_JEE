package com.cine.demo;

import com.cine.demo.entities.Film;
import com.cine.demo.entities.Salle;
import com.cine.demo.entities.Ticket;
import com.cine.demo.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);

			cinemaInitService.initVilles();
			cinemaInitService.initCinemas();
			cinemaInitService.initSalles();
			cinemaInitService.initPlaces();
			cinemaInitService.initSeances();
			cinemaInitService.initCategories();
			cinemaInitService.initFilms();
			cinemaInitService.initProjections();
			cinemaInitService.initTickets();


	}
}
