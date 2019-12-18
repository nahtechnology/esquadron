package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name= "licencia", schema= "administracion")
@NamedQueries({
	@NamedQuery(name = "LicenciaEntidad.busca", query = "SELECT m FROM LicenciaEntidad m"),
	@NamedQuery(
		name = "LicenciaEntidad.buscaEscuela",
		query = "SELECT m FROM LicenciaEntidad m WHERE m.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"),
	@NamedQuery(
		name = "LicenciaEntidad.cuenta",
		query = "SELECT COUNT (l) FROM LicenciaEntidad l"),
	@NamedQuery(
		name = "LicenciaEntidad.buscaActivo",
		query = "SELECT l FROM LicenciaEntidad l WHERE l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo =:claveCentroTrabajo AND l.inicio BETWEEN :fechaInicio AND :fechaFin")
})
public class LicenciaEntidad {

	private LicenciaEntidadPk licenciaEntidadPk;
	private Date inicio;
	private Date adquisicion;
	private Short alumnos;

	public LicenciaEntidad() {
	}

	public LicenciaEntidad(LicenciaEntidadPk licenciaEntidadPk) {
		this.licenciaEntidadPk = licenciaEntidadPk;
	}

	@EmbeddedId
	public LicenciaEntidadPk getLicenciaEntidadPk() {
		return licenciaEntidadPk;
	}

	public void setLicenciaEntidadPk(LicenciaEntidadPk licenciaEntidadPk) {
		this.licenciaEntidadPk = licenciaEntidadPk;
	}

	@Basic
	@Column(name = "inicio", insertable = false)
	@Temporal(TemporalType.DATE)
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@Basic
	@Column(name = "adquisicion", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	public Date getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(Date adquisicion) {
		this.adquisicion = adquisicion;
	}

	@Basic
	@Column(name = "alumnos")
	@NotNull
	public Short getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Short alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", LicenciaEntidad.class.getSimpleName() + "[", "]")
				.add("licenciaEntidadPk=" + licenciaEntidadPk)
				.add("inicio=" + inicio)
				.add("adquisicion=" + adquisicion)
				.add("alumnos=" + alumnos)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LicenciaEntidad that = (LicenciaEntidad) o;
		return licenciaEntidadPk.equals(that.licenciaEntidadPk);
	}

	@Override
	public int hashCode() {
		return Objects.hash(licenciaEntidadPk);
	}

}
