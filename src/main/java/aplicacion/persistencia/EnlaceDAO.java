package aplicacion.persistencia;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
 

import aplicacion.modelo.Enlace;
import aplicacion.utils.JPAUtil;
 
 

public class EnlaceDAO {

	 
		
	public void insertarEnlaceJPA(Enlace enlace) {
				
		//JPA
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(enlace);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		 
		
		
	}
	
	 
		
	
	public void modificarEnlaceJPA(Enlace enlace) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(enlace);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
	}
	
	 
		
	public void eliminarEnlaceJPA(Enlace enlace) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(enlace)? enlace:em.merge(enlace) );
		
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
				
	}
	
	 
		
	public ArrayList<Enlace> listarEnlaceJPA() {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Enlace> misAlumnos = (ArrayList<Enlace>) em.createQuery("from Enlace").getResultList();
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
		
		return null;
		
	}
	
	
	
	
	
	 
	
	public Enlace buscarEnlacePorIdJPA(int id) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Enlace pedido = em.find(Enlace.class, id);
			System.out.println("El alumno buscado se llama :"+pedido.getNombre());
			return pedido;
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
	
	
	
	public void imprimirEnlace(ArrayList<Enlace> misEnlaces) {
		System.out.println("Listado de Alumnos");
		for(Enlace a:misEnlaces) {
			a.toString();
		}
	}
	
}
