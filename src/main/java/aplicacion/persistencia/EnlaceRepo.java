package aplicacion.persistencia;
 

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Enlace;

public interface EnlaceRepo extends JpaRepository<Enlace, Integer> {

	
	
}
