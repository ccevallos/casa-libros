package casolibro.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean que representa la información de un libro
 * 
 * @author Carlos Cevallos
 */
@Entity
@Table(name = "libro")
public class Libro {
	/**
	 * Código único del libro. Es la clave primaria. Se genera mediante una
	 * secuencia a nivel de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * Titulo del libro.
	 */
	@Column
	private String titulo;
	/**
	 * Autor del libro.
	 */
	@Column
	private String autor;
	/**
	 * Resumen del libro.
	 */
	@Column
	private String resumen;
	/**
	 * Estado del libro. Puede ser activo o inactivo.
	 */
	@Column
	private String estado = "activo";

	/**
	 * Permite obtener el codigo del libro.
	 * 
	 * @return id del libro
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Permite definir el codigo del libro.
	 * 
	 * @param id id del libro
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Permite obtener el titulo del libro.
	 * 
	 * @return titulo del libro
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Permite definir el titulo del libro.
	 * 
	 * @param titulo titulo del libro
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Permite obtener el autor del libro.
	 * 
	 * @return autor del libro
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Permite definir el autor del libro.
	 * 
	 * @param autor autor del libro
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Permite obtener el resumen del libro.
	 * 
	 * @return resumen del libro
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * Permite definir el resumen del libro.
	 * 
	 * @param resumen resumen del libro
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * Permite obtener el estado del libro ("activo" / "inactivo").
	 * 
	 * @return estado del libro
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Permite definir el estado del libro.
	 * 
	 * @param estado estado del libro
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}