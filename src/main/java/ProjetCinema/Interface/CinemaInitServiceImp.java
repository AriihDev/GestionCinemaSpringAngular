package ProjetCinema.Interface;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProjetCinema.Dao.CategorieImp;
import ProjetCinema.Dao.CinemaImp;
import ProjetCinema.Dao.FilmImp;
import ProjetCinema.Dao.PlaceImp;
import ProjetCinema.Dao.ProjectionImp;
import ProjetCinema.Dao.SalleImp;
import ProjetCinema.Dao.SeanceImp;
import ProjetCinema.Dao.TicketImp;
import ProjetCinema.Dao.VilleImp;
import ProjetCinema.model.Categorie;
import ProjetCinema.model.Cinema;
import ProjetCinema.model.Film;
import ProjetCinema.model.Place;
import ProjetCinema.model.Projection;
import ProjetCinema.model.Salle;
import ProjetCinema.model.Seance;
import ProjetCinema.model.Ticket;
import ProjetCinema.model.Ville;

@Service
@Transactional
public class CinemaInitServiceImp  implements ICinemaInitService{
	
	@Autowired
	private VilleImp villeImp;
	@Autowired
	private CinemaImp cinemaImp;
	@Autowired
	private SalleImp salleImp;
	@Autowired
	private PlaceImp placeImp;
	@Autowired
	private SeanceImp seanceImp;
	@Autowired
	private FilmImp filmImp;
	@Autowired
	private ProjectionImp projectionImp;
	@Autowired
	private CategorieImp categorieImp;
	@Autowired
	private TicketImp ticketImp;

	@Override
	public void initVilles() {
		Stream.of("Paris","Massy","Evry","Saint-Denis").forEach(nameVille-> {
			Ville ville = new Ville();
			ville.setName(nameVille);
			villeImp.save(ville);
		});	
	}
	
	@Override
	public void initCinemas() {
		villeImp.findAll().forEach(v->{
			Stream.of("UGC","Gaumont Pathé","MK2")
			.forEach(nameCinema->{
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(4+(int)Math.random()*7);
				cinema.setVille(v);
				cinemaImp.save(cinema);
			});
		});	
	}

	@Override
	public void initSalles() {
		cinemaImp.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle salle = new Salle();
				salle.setName("Salle " + (i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(20+(int)(Math.random()*100));
				salleImp.save(salle);
			}
		});
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("15:45", "17:30","19:15","21:00","23:50").forEach(s-> {
			Seance seance = new Seance();
			seanceImp.save(seance);
			try {
				seance.setHeureDebut(dateFormat.parse(s));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initPlaces() {
		salleImp.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeImp.save(place);
			}
		});
	}

	@Override
	public void initCategorie() {
		Stream.of("Action","Aventure","Animation","Comédie").forEach(cat->{
			Categorie categorie = new Categorie();
			categorie.setName(cat);
			categorieImp.save(categorie);
		});
	}

	@Override
	public void initFilms() {
		double[] durees = new double[] {1,1.5,2,2.5,3};
		List<Categorie> categorie = categorieImp.findAll();
		Stream.of("Docteur Strange 2","Uncharted","One Piece RED ", "Les Bad Guys","The Batman","Top Gun Maverick",
				"The Northman","Les Animaux Fantastiques").forEach(titreFilm-> {
					Film film = new Film();
					film.setTitre(titreFilm);
					film.setDuree(durees[new Random().nextInt(durees.length)]);
					film.setPhoto(titreFilm.replaceFirst(" ", " ")+".jpg");
					film.setCategorie(categorie.get(new Random().nextInt(categorie.size())));
					filmImp.save(film);
				});
	}

	@Override
	public void initProjections() {
		double[] prix = new double[] {5.90,7.90,12.00,19.00};
		villeImp.findAll().forEach(ville-> {
			ville.getCinemas().forEach(cinema-> {
				cinema.getSalles().forEach(salle -> {
					filmImp.findAll().forEach(film-> {
						seanceImp.findAll().forEach(seance-> {
							Projection projection = new Projection();
							projection.setDateProjection(new Date(0));
							projection.setFilm(film);
							projection.setPrix(prix[new Random().nextInt(prix.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionImp.save(projection);
						});
					});
				});
			});
		});
	}

	@Override
	public void initTickets() {
		projectionImp.findAll().forEach(p-> {
			p.getSalle().getPlaces().forEach(place-> {
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservation(false);
				ticketImp.save(ticket);
				
			});
		});
	}
}
