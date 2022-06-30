package ProjetCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ProjetCinema.Interface.ICinemaInitService;

@SpringBootApplication
public class ProjetCinemaSpringAngularApplication implements CommandLineRunner {
	
	@Autowired
	private ICinemaInitService cinemaInitService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetCinemaSpringAngularApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategorie();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
	}

}
