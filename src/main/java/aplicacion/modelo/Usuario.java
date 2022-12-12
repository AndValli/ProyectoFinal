package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import aplicacion.modelo.Usuario;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(cascade= {CascadeType.MERGE}, mappedBy="usuario", fetch = FetchType.EAGER)
	private Set<Categoria> categorias; 
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		categorias = new HashSet<Categoria>();
	}
	
	public Usuario() {
		categorias = new HashSet<Categoria>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<Categoria> getCategoria() {
		return categorias;
	}

	public void setCategoria(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void imprimir() {
		
		System.out.println("Usuario id=" + id + ", nombre=" + getNombre());
		for(Categoria p: categorias) {
			p.imprimir();
			System.out.println("");
		}
		
		
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + "]";
	}
}
