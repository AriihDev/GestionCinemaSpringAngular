package ProjetCinema.Controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ProjetCinema.Dao.FilmImp;
import ProjetCinema.Dao.TicketImp;
import ProjetCinema.model.Film;
import ProjetCinema.model.Ticket;
import lombok.Data;


@RestController
@CrossOrigin("*")
public class CinemaController {
	@Autowired
	private FilmImp filmImp;
	@Autowired
	private TicketImp ticketImp;
	@GetMapping(path="/imageFilm/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id")Long id) throws Exception{
		Film f = filmImp.findById(id).get();
		String photoName = f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/ProjetCinema/images/"+photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

	@PostMapping("/payement")
	@Transactional
	public List<Ticket> payementTicket(@RequestBody TicketForm ticketForm) {
		List<Ticket> listTickets = new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket-> {
			Ticket ticket = ticketImp.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReservation(true);
			ticketImp.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}
}

@Data
class TicketForm{
	private String nomClient;
	private int MoyPayement;
	private List<Long> tickets = new ArrayList<>();
}
