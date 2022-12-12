package aplicacion.persistencia;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import aplicacion.modelo.Categoria;
import aplicacion.utils.JPAUtil;
 
 

public class CategoriaDAO {
 
	@Autowired
	CategoriaRepo categoriaRepo;
	
	
	public void insertarCategoriaJPA(Categoria categoria) {
			
		//categoriaRepo.save(categoria);
		
		
		
		//JPA
	  /*  EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
		 
		
		
	}
	
	 
	
	public void modificarCategoriaJPA(Categoria categoria) {
		
		//categoriaRepo.save(categoria);
		
		//JPA
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(categoria);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
		
	}
	
	 
		
	public void eliminarCategoriaJPA(Categoria categoria) {
		
		//categoriaRepo.delete(categoria);
		
		//JPA
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(categoria)? categoria:em.merge(categoria) );
		
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
				
	}
	
	 
		
	public ArrayList<Categoria> listarCategoriaJPA() {
		
		return (ArrayList<Categoria>) categoriaRepo.findAll();
		
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Categoria> misAlumnos = (ArrayList<Categoria>) em.createQuery("from Categoria").getResultList();
			em.getTransaction().commit();
			return misAlumnos;
			
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		
		return null;*/
		
	}
	
	
	 
		
	
	public Categoria buscarCategoriaPorIdJPA(int id) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Categoria categoria = em.find(Categoria.class, id);
			System.out.println("El alumno buscado se llama :"+categoria.getId());
			return categoria;
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			return null;		
	}
	
	
	
	public void imprimir(ArrayList<Categoria> misCategorias) {
		System.out.println("Listado de Alumnos");
		for(Categoria a:misCategorias) {
			a.toString();
		}
	}
	
}
