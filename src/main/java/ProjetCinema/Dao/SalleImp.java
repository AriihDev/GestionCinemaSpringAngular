package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Salle;

@RepositoryRestResource
public interface SalleImp extends JpaRepository<Salle, Long>{

}
