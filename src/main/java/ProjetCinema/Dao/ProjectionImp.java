package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Projection;

@RepositoryRestResource
public interface ProjectionImp extends JpaRepository<Projection, Long>{

}
