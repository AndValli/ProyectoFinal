package aplicacion.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Usuario;
import aplicacion.modelo.Enlace;
import aplicacion.modelo.Categoria;
import aplicacion.persistencia.UsuarioDAO;
import aplicacion.persistencia.UsuarioRepo;
import aplicacion.persistencia.EnlaceDAO;
import aplicacion.persistencia.EnlaceRepo;
import aplicacion.persistencia.CategoriaDAO;
import aplicacion.persistencia.CategoriaRepo;

@RequestMapping("/")
@Controller
public class CategoriaController {

	// private AlumnoServiceImp usuarioService = new AlumnoServiceImp();

	@Autowired
	private CategoriaRepo categoriaRepo;
	
	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private EnlaceRepo enlaceRepo;

	@GetMapping(value = { "", "/" })
	String usuarios(Model model) {
		 	
		ArrayList<Categoria> miscategorias=(ArrayList<Categoria>) categoriaRepo.findAll();
        ArrayList<Usuario> misUsuarios= (ArrayList<Usuario>) usuarioRepo.findAll();
        ArrayList<Enlace> misEnlaces= (ArrayList<Enlace>) enlaceRepo.findAll();
       
        model.addAttribute("listaCategorias", miscategorias);
       // model.addAttribute("listaUsuarios", misUsuarios);
       // model.addAttribute("listaEnlaces", misEnlaces);
        
        model.addAttribute("categoriaNueva", new Categoria());
        model.addAttribute("enlaceNuevo", new Enlace());
		

		return "index";
		
	}

	@PostMapping("/addCategoria")
	public String addCategoria(@ModelAttribute("categoriaNueva") Categoria categoriaNueva, BindingResult bindingResult) {

	

		//Alumno usuarioNuevo = crudAlumno.buscarPorIdJPA(categoriaNuevo.getAlumno().getId());

		
		//Usuario usuarioNuevo = usuarioRepo.findById(categoriaNueva.getUsuario().getId()).get();
		
		//usuarioNuevo.getCategoria().add(categoriaNueva);
		//categoriaNueva.setUsuario(usuarioNuevo);

		categoriaRepo.save(categoriaNueva);
		

		return "redirect:/";
	}

	@PostMapping({ "addEnlace/{id}" })
	String idCategoria(Model model,@ModelAttribute("enlaceNuevo") Enlace enlaceNuevo, @PathVariable Integer id) {
		
        
		Categoria categoriaDelEnlace = categoriaRepo.findById(id).get();
		
		enlaceNuevo.setId(null);
		enlaceNuevo.getCategorias().add(categoriaDelEnlace);
		enlaceRepo.save(enlaceNuevo);
		
		categoriaDelEnlace.getEnlaces().add(enlaceNuevo);
		categoriaRepo.save(categoriaDelEnlace);
		
		return "redirect:/";
	}
	
	@PostMapping("/edit/{id}")
	public String editarCategoria(Model model, @PathVariable Integer id, @ModelAttribute ("categoriaMostrar") Categoria categoriaEditado) {
		Usuario a=usuarioRepo.findById(categoriaEditado.getUsuario().getId()).get();
		categoriaEditado.setUsuario(a);
		Categoria categoriaaEditar=categoriaRepo.findById(id).get();
		for(Enlace b:categoriaaEditar.getEnlaces()) {
			if(!categoriaEditado.getEnlaces().contains(b)) {
				b.getCategorias().remove(categoriaaEditar);
			}
		}
		for(Enlace b:categoriaEditado.getEnlaces()) {
			if(!categoriaaEditar.getEnlaces().contains(b)) {
				b.getCategorias().add(categoriaEditado);
			}
		}
		
		categoriaRepo.save(categoriaEditado);
		return "redirect:/categorias";
	}
	@GetMapping({ "/buscar/{nombre}" })
	public String obtenerCategoria(@PathVariable String nombre) {
		return "categoria";
	}

	@GetMapping({ "/delete/{id}" })
	String deleteCategoria(Model model, @PathVariable Integer id) {
		
		categoriaRepo.deleteById(id);

		return "redirect:/categorias";

	}

}
