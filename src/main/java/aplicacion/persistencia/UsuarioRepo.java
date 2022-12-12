package aplicacion.persistencia;
 

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Usuario;
import aplicacion.modelo.Enlace;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	
	
}
