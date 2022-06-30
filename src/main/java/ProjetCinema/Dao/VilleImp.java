package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ProjetCinema.model.Ville;

@RepositoryRestResource
@CrossOrigin("*")
public interface VilleImp extends JpaRepository<Ville, Long>{

}
