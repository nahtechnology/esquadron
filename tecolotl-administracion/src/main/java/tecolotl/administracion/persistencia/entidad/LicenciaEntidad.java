package tecolotl.administracion.persistencia.entidad;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "licencia", schema= "administracion")
@NamedQuery(name = "LicenciaEntidad.busca", query = "SELECT m FROM LicenciaEntidad m")
public class LicenciaEntidad {

	private short id;
	private EscuelaEntidad escuela;
	private Date inicio;
	private String idEscuela;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	@NotNull
	@Size(min = 10, max = 10)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_escuela", columnDefinition = "clave_centro_trabajo")
	public EscuelaEntidad getEscuela() {
		return escuela;
	}

	public void setEscuela(EscuelaEntidad escuela) {
		this.escuela = escuela;
	}

	@NotNull
	@Column(name = "inicio")
	@Temporal(TemporalType.DATE)
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@Basic
	@Column(name = "id_escuela", insertable = false, updatable = false)
	public String getIdEscuela() {
		return idEscuela;
	}

	public void setIdEscuela(String idEscuela) {
		this.idEscuela = idEscuela;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LicenciaEntidad that = (LicenciaEntidad) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
