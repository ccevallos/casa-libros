package casolibro.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Bean que almacena la información de todas las transacciones realizadas
 * con la fecha y hora.
 * 
 * @author Carlos Cevallos
 */
@Entity
@Table(name = "transaccion")
public class Transaccion {
	/**
	 * Código único de la transacción. Es la clave primaria. Se genera mediante una
	 * secuencia a nivel de base de datos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Tipo de transacción. Puede ser: registro, actualiza, consulta y elimina
	 */
	@Column
	private String tipo;

	/**
	 * Fecha y hora a la que se realiza la transacción.
	 */
	@Column
	private Date fecha;

	/**
	 * Id del libro creado, modificado o eliminado. En el caso de consultas este
	 * campo se mantiene nulo.
	 */
	@Column(name = "id_libro")
	private Integer idLibro;

	/**
	 * Permite obtener el código de la transacción.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Permite definir el código de la transacción.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Permite obtener el tipo de la transacción.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Permite definir el tipo de la transacción.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Permite obtener la fecha y hora de la transacción.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Permite definir la fecha y hora de la transacción.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Permite obtener el código del libro sobre el cual se realizó la transacción.
	 */
	public Integer getIdLibro() {
		return idLibro;
	}

	/**
	 * Permite definir el código del libro sobre el cual se realizó la transacción.
	 */
	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}
}