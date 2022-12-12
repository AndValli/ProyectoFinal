package aplicacion.persistencia;
 

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Enlace;
import aplicacion.modelo.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

	
	
}
