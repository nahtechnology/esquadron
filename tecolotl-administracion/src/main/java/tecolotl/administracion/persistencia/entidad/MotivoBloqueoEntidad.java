package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "motivo_bloqueo", schema ="administracion")
@NamedQueries({
		@NamedQuery(name = "MotivoBloqueoEntidad.busca", query = "SELECT m FROM MotivoBloqueoEntidad m WHERE NOT (m.id = 0)")
})
public class MotivoBloqueoEntidad {

	private short id;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_id")
    @SequenceGenerator(name = "secuencia_id", schema = "administracion", sequenceName = "motivo_bloqueo_seq")
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
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
