package tecolotl.nucleo.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the tema database table.
 * 
 */
@Entity
@Table(name="tema" ,schema = "nucleo")
@NamedQueries({
	@NamedQuery(name="TemaEntidad.find", query="SELECT t FROM TemaEntidad t")
})
public class TemaEntidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TemaEntidad() {
	}
	
	public TemaEntidad(String descripcion, String nombre) {
		this.descripcion = descripcion;
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min=20)
	private String descripcion;

	@NotNull
	private String nombre;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}