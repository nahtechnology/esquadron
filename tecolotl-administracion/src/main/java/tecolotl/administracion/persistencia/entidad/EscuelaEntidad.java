package tecolotl.administracion.persistencia.entidad;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "escuela", schema = "administracion")
@NamedQueries({
	@NamedQuery(name = "EscuelaEntidad.busca", query = "SELECT e from EscuelaEntidad e"),
	@NamedQuery(name = "EscuelaEntidad.buscaExistencia", query = "SELECT COUNT(e.claveCentroTrabajo) FROM EscuelaEntidad e WHERE e.claveCentroTrabajo=:claveCentroTrabajo")
})
public class EscuelaEntidad {

    private String claveCentroTrabajo;
    private ColoniaEntidad coloniaEntidad;
    private MotivoBloqueoEntidad motivoBloqueoEntidad;
    private String nombre;
	private String domicilio;
	private List<LicenciaEntidad> licencia;

	public EscuelaEntidad() {
	}

	public EscuelaEntidad(String claveCentroTrabajo) {
		this.claveCentroTrabajo = claveCentroTrabajo;
	}

	@Id
	@Column(name = "clave_centro_trabajo")
	public String getClaveCentroTrabajo() {
		return claveCentroTrabajo;
	}

	public void setClaveCentroTrabajo(String claveCentroTrabajo) {
		this.claveCentroTrabajo = claveCentroTrabajo;
	}

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_colonia", referencedColumnName = "id")
	public ColoniaEntidad getColoniaEntidad() {
		return coloniaEntidad;
	}

	public void setColoniaEntidad(ColoniaEntidad coloniaEntidad) {
		this.coloniaEntidad = coloniaEntidad;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_motivo_bloqueo", referencedColumnName = "id")
	public MotivoBloqueoEntidad getMotivoBloqueoEntidad() {
		return motivoBloqueoEntidad;
	}

	public void setMotivoBloqueoEntidad(MotivoBloqueoEntidad motivoBloqueoEntidad) {
		this.motivoBloqueoEntidad = motivoBloqueoEntidad;
	}

	@Column(name = "nombre")
	@NotNull
	@Size(min = 11, max = 50)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "domicilio")
	@NotNull
	@Size(min = 11, max = 60)
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@OneToMany(mappedBy = "escuela")
	public List<LicenciaEntidad> getLicencia() {
		return licencia;
	}

	public void setLicencia(List<LicenciaEntidad> licencia) {
		this.licencia = licencia;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EscuelaEntidad that = (EscuelaEntidad) o;
		return claveCentroTrabajo.equals(that.claveCentroTrabajo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(claveCentroTrabajo);
	}
}