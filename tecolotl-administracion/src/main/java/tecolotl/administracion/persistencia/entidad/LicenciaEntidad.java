package tecolotl.administracion.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "licencia", schema= "administracion")
@NamedQuery(name = "LicenciaEntidad.busca", query = "SELECT m FROM LicenciaEntidad m")
public class LicenciaEntidad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private short id;
	
	@NotNull
	@Size(min = 10, max = 10)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="clave_centro_trabajo")
	private EscuelaEntidad idEscuela;
	
	@NotNull
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public EscuelaEntidad getIdEscuela() {
		return idEscuela;
	}

	public void setIdEscuela(EscuelaEntidad idEscuela) {
		this.idEscuela = idEscuela;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
	

}
