package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Film;

@RepositoryRestResource
public interface FilmImp extends JpaRepository<Film, Long>{

}
