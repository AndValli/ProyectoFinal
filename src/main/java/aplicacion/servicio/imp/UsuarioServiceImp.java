package aplicacion.servicio.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aplicacion.modelo.Usuario;
import aplicacion.persistencia.UsuarioRepo;
import principal.servicio.interfaces.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepo.findByUsername(username);

		if (usuario.isPresent()) {

			Usuario springUserMio = usuario.get();
			return springUserMio;

		} else {
			throw new UsernameNotFoundException("usuario no encontrado");
		}

		
	}

}
