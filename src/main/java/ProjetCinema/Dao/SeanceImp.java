package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Seance;

@RepositoryRestResource
public interface SeanceImp extends JpaRepository<Seance, Long>{

}
