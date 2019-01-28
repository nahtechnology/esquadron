package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "motivo_bloqueo", schema ="administracion")
@NamedQueries({
		@NamedQuery(name = "MotivoBloqueoEntidad.busca", query = "SELECT m FROM MotivoBloqueoEntidad m WHERE NOT (m.descripcion = :descripcion) ORDER BY m.descripcion"),
		@NamedQuery(name = "MotivoBloqueoEntidad.buscaDescripcion", query = "SELECT m FROM MotivoBloqueoEntidad m WHERE m.descripcion = :descripcion")
})
public class MotivoBloqueoEntidad {

	private Short id;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}

	@Basic
	@Column(name = "descripcion")
	@NotNull
	@Size(min = 3, max = 20)
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
