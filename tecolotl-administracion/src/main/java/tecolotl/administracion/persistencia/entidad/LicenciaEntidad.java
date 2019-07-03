package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "licencia", schema= "administracion")
@NamedQueries({
	@NamedQuery(name = "LicenciaEntidad.busca", query = "SELECT m FROM LicenciaEntidad m"),
	@NamedQuery(
		name = "LicenciaEntidad.buscaEscuela",
		query = "SELECT m FROM LicenciaEntidad m WHERE m.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"),
	@NamedQuery(
		name = "LicenciaEntidad.cuentaPorEscuela",
		query = "SELECT COUNT(l.licenciaEntidadPk.contador) FROM LicenciaEntidad l WHERE l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
	)
})
public class LicenciaEntidad {

	private LicenciaEntidadPk licenciaEntidadPk;
	private Date inicio;
	private Date adquisicion;

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

}
