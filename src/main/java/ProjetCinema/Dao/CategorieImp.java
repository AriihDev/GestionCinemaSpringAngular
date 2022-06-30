package ProjetCinema.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ProjetCinema.model.Categorie;

@RepositoryRestResource
public interface CategorieImp extends JpaRepository<Categorie, Long>{

}
