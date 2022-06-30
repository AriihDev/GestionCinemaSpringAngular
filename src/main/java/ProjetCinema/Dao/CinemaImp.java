package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Cinema;

@RepositoryRestResource
public interface CinemaImp extends JpaRepository<Cinema, Long> {

}
