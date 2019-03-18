package tecolotl.administracion.persistencia.entidad;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name= "licencia", schema= "administracion")
@NamedQuery(name = "LicenciaEntidad.busca", query = "SELECT m FROM LicenciaEntidad m")
public class LicenciaEntidad {

	private LicenciaEntidadPk licenciaEntidadPk;
	private Date inicio;
	private Date adquisicion;

	@EmbeddedId
	public LicenciaEntidadPk getLicenciaEntidadPk() {
		return licenciaEntidadPk;
	}

	public void setLicenciaEntidadPk(LicenciaEntidadPk licenciaEntidadPk) {
		this.licenciaEntidadPk = licenciaEntidadPk;
	}

	@Basic(optional = true)
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
	@Null
	@Column(name = "adquisicion", insertable = false)
	@Temporal(TemporalType.DATE)
	public Date getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(Date adquisicion) {
		this.adquisicion = adquisicion;
	}

}
