package aplicacion.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Usuario;
import aplicacion.persistencia.UsuarioDAO;
@RequestMapping("/usuarios")
@Controller
public class UsuariosController {
	
	UsuarioDAO usuarioDAO=new UsuarioDAO();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value={"","/"})
	String homeusuarios(Model model) {
		//Salir a buscar a la BBDD
		ArrayList<Usuario> misUsuarios=usuarioDAO.listarUsuariosJPA();
		model.addAttribute("listaUsuarios", misUsuarios);
		model.addAttribute("usuarioaEditar", new Usuario());
		model.addAttribute("usuarioNuevo", new Usuario());
		return "usuarios";
	}
	@PostMapping("/edit/{id}")
	public String editarUsuario(@PathVariable Integer id, @ModelAttribute("usuarioaEditar") Usuario usuarioEditado, BindingResult bidingresult) {
		Usuario usuarioaEditar=usuarioDAO.buscarIDJPA(id);
		usuarioaEditar.setNombre(usuarioEditado.getNombre());
		usuarioDAO.editarUsuarioJPA(usuarioaEditar);
		return "redirect:/usuarios";
	}
	@GetMapping({"/{id}"})
	String idUsuario(Model model, @PathVariable Integer id) {
		Usuario usuarioMostrar=usuarioDAO.buscarIDJPA(id);
		model.addAttribute("usuarioMostrar", usuarioMostrar);
		return "usuario";
	}
	@GetMapping({"/delete/{id}"})
	String deleteUsuario(Model model, @PathVariable Integer id) {
		Usuario usuarioaEliminar=usuarioDAO.buscarIDJPA(id);
		usuarioDAO.eliminarUsuarioJPA(usuarioaEliminar);
		return "redirect:/usuarios";
	}
	@PostMapping("/add")
	public String addUsuario(@ModelAttribute("usuarioNuevo") Usuario usuarioNew, BindingResult bidingresult) {
		
		String hashClave = passwordEncoder.encode(usuarioNew.getPassword());
		usuarioNew.setPassword(hashClave);
		
		usuarioDAO.insertarUsuarioJPA(usuarioNew);
		return "redirect:/usuarios";
	}
}
