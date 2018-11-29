package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "motivo_bloqueo", schema ="administracion")
@NamedQuery(name = "MotivoBloqueoEntidad.busca", query = "SELECT m FROM MotivoBloqueoEntidad m")
public class MotivoBloqueoEntidad {
	
	@Id
	private short id;
	@Basic
	@Column(name = "descripcion")
	@NotNull
	@Size(min = 3, max = 20)
	private String descripcion;
	
	
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
