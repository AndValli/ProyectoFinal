package aplicacion.controlador;
 
import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
 

import aplicacion.modelo.Enlace;

import aplicacion.persistencia.EnlaceDAO;

@RequestMapping("/enlaces")
@Controller
public class EnlaceController {

	 
	 	
	 
	private EnlaceDAO crudEnlace = new EnlaceDAO( ); 
	
	@GetMapping(value={"","/"})
	String enlaces(Model model) {

		List<Enlace> lista = crudEnlace.listarEnlaceJPA();
		model.addAttribute("enlaces",lista );
		model.addAttribute("nuevoEnlace",new Enlace() );
		model.addAttribute("enlaceEditar", new Enlace());    
		model.addAttribute("enlaceNuevo", "");    
		return "enlaces";
	}

	@GetMapping(value="/add/{enlace}")
	public String insertarEnlace(Model model, @PathVariable String nombre ) {
		 
		System.out.println("Insertando enlace nuevo: "+nombre);		
		
		return "redirect:/enlaces";
	}
	
	
	@PostMapping("/add")
	public String addEnlace(@ModelAttribute("nuevoEnlace") Enlace enlace, BindingResult bindingResult) {
		
		crudEnlace.insertarEnlaceJPA(enlace);
		System.out.println("Insertando enlace nuevo: "+enlace.getNombre());			
				
		return "redirect:/alumnos";	
	}
	
	@PostMapping("/edit/{id}")
	public String editarEnlace(@PathVariable Integer id, @ModelAttribute("enlaceEditar") Enlace enlace,BindingResult bindingResult) {
		
		Enlace enlaceEditar = crudEnlace.buscarEnlacePorIdJPA(id);
		enlaceEditar.setNombre(enlace.getNombre());
		crudEnlace.insertarEnlaceJPA(enlaceEditar);
		
		return "redirect:/enlaces";
		 
	}
	
	  
	
	@GetMapping({"/{id}"})
	public String obtenerEnlace(Model model, @PathVariable Integer id) {
	 
		model.addAttribute("enlace", crudEnlace.buscarEnlacePorIdJPA(id));
		
		return "enlace";
	}
	
	@GetMapping({"/buscar/{nombre}"})
	public String obtenerEnlace(@PathVariable String nombre) {
		return "enlace";
	}
	
	@GetMapping({"/delete/{id}"})
	public String borrarEnlace(@PathVariable Integer id) {
		
		crudEnlace.eliminarEnlaceJPA(crudEnlace.buscarEnlacePorIdJPA(id));
		
		return "redirect:/enlaces";
		 
	}
	

	
	
	
}
