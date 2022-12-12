package aplicacion.persistencia;

import aplicacion.modelo.Usuario;
import aplicacion.modelo.Enlace;
import aplicacion.modelo.Categoria;

public class Tablas {
	
	public void crearTablas() {

		
		Enlace e1 = new Enlace();
		Enlace e2 = new Enlace();
		
		Categoria c1 = new Categoria();
		Categoria c2 = new Categoria();
		
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		
		c1.getEnlaces().add(e1);
        c2.getEnlaces().add(e2);
        e1.getCategorias().add(c1);
        e2.getCategorias().add(c2);
       
        UsuarioDAO crudUsuario = new UsuarioDAO();
        CategoriaDAO  crudCategoria = new CategoriaDAO();
        EnlaceDAO  crudEnlace = new EnlaceDAO();
       
        
        crudUsuario.insertarUsuarioJPA(u1);
        crudUsuario.insertarUsuarioJPA(u2);
        crudCategoria.insertarCategoriaJPA(c1);
        crudCategoria.insertarCategoriaJPA(c2);
        crudEnlace.insertarEnlaceJPA(e1);
        crudEnlace.insertarEnlaceJPA(e2);
       
	}
}
