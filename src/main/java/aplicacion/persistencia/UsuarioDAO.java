package aplicacion.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aplicacion.modelo.Usuario;
import aplicacion.utils.HibernateUtil;
import aplicacion.utils.JPAUtil;

public class UsuarioDAO {


	 public void insertarUsuarioJPA(Usuario usuario) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		 
	 }
	 
	 public void insertarUsuarioHibernate(Usuario usuario) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
			session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.persist(usuario);
			tr.commit();
		} catch (PersistenceException e) {
			tr.rollback();
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		 
	 }
	 
	 public void editarUsuarioHibernate (Usuario usuario) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.merge(usuario);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public void editarUsuarioJPA (Usuario usuario) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }

	 public void eliminarUsuarioJPA (Usuario usuario) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Usuario a = em.find(Usuario.class, usuario.getId());
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	 
	 public void eliminarUsuarioHibernate (Usuario usuario) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.delete(usuario);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public ArrayList<Usuario> listarUsuariosJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) em.createQuery("from Usuario").getResultList();
			em.getTransaction().commit();
			return listaUsuarios;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 
		 return null;
	 }
	 
	 public ArrayList<Usuario> listarUsuariosHibernate () {
			
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) session.createQuery("from Usuario").getResultList();
				tr.commit();
				return listaUsuarios;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return null;
	 }
	 
	 public void imprimirUsuarios(ArrayList<Usuario> listaUsuarios) {
		 
		 for (Usuario a: listaUsuarios) {
			 a.imprimir();
		 }
	 }
	 
	 public Usuario readUsuario(int id) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Usuario al = em.find(Usuario.class, id);
		 
		 return al;
	 }
	 
	 
	 public Usuario buscarIDJPA(int id) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Usuario usuario = entity.find(Usuario.class, id);
	            entity.getTransaction().commit();
	            System.out.println("El usuario del id " +id+ " es " +usuario.getNombre());
	            return usuario;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	 public Usuario buscarIDHibernate(int id) {
		 Transaction tr = null;
		 Session session = null;
		 Usuario resultado = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				resultado = session.find(Usuario.class, id);
				tr.commit();
				System.out.println("El usuario del id " +id+ " es " +resultado.toString());
				return resultado;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return resultado;
	    }
}
