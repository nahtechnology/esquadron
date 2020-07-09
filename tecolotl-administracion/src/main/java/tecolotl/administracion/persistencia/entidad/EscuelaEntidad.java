package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "escuela", schema = "administracion")
@NamedQueries({
	@NamedQuery(
		name = "EscuelaEntidad.busca",
		query = "SELECT e from EscuelaEntidad e ORDER BY e.nombre"),
	@NamedQuery(
		name = "EscuelaEntidad.detalle",
		query = "SELECT e FROM EscuelaEntidad e " +
				"LEFT JOIN FETCH e.motivoBloqueoEntidad WHERE e.claveCentroTrabajo=:claveCentroTrabajo"),
	@NamedQuery(
		name = "EscuelaEntidad.existe",
		query = "SELECT e.nombre FROM EscuelaEntidad e WHERE e.claveCentroTrabajo=:claveCentroTrabajo"),
    @NamedQuery(
		name = "EscuelaEntidad.buscaNombre",
		query = "SELECT NEW EscuelaEntidad(e.claveCentroTrabajo, e.nombre) FROM EscuelaEntidad e"),
	@NamedQuery(
		name = "EscuelaEntidad.cuentaEscuela",
		query = "SELECT COUNT(e.claveCentroTrabajo) FROM EscuelaEntidad e"),
	@NamedQuery(
		name = "EscuelaEntidad.buscaGalaxia",
		query = "SELECT new EscuelaEntidad (e.claveCentroTrabajo, e.galaxia) FROM EscuelaEntidad e")
})
public class EscuelaEntidad {

    private String claveCentroTrabajo;
    private String nombre;
	private Integer galaxia;
	private MotivoBloqueoEntidad motivoBloqueoEntidad;
	private List<ContactoEntidad> contactoEntidadLista;

	public EscuelaEntidad() {
	}

	public EscuelaEntidad(String claveCentroTrabajo) {
		this.claveCentroTrabajo = claveCentroTrabajo;
	}

	public EscuelaEntidad(String claveCentroTrabajo, String nombre) {
		this.claveCentroTrabajo = claveCentroTrabajo;
		this.nombre = nombre;
	}

	public EscuelaEntidad(String claveCentroTrabajo, Integer galaxia) {
		this.claveCentroTrabajo = claveCentroTrabajo;
		this.galaxia = galaxia;
	}

	@Id
	@Column(name = "clave_centro_trabajo")
	@Size(min = 10, max = 14)
	public String getClaveCentroTrabajo() {
		return claveCentroTrabajo;
	}

	public void setClaveCentroTrabajo(String claveCentroTrabajo) {
		this.claveCentroTrabajo = claveCentroTrabajo;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_motivo_bloqueo", referencedColumnName = "clave")
	public MotivoBloqueoEntidad getMotivoBloqueoEntidad() {
		return motivoBloqueoEntidad;
	}

	public void setMotivoBloqueoEntidad(MotivoBloqueoEntidad motivoBloqueoEntidad) {
		this.motivoBloqueoEntidad = motivoBloqueoEntidad;
	}

	@Basic
	@Column(name = "nombre")
	@NotNull
	@Size(min = 4, max = 70)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic
	@Column(name = "galaxia")
	public Integer getGalaxia() {
		return galaxia;
	}

	public void setGalaxia(Integer galaxia) {
		this.galaxia = galaxia;
	}

	@OneToMany(mappedBy = "contactoEntidadPK.escuelaEntidad", fetch = FetchType.LAZY)
	public List<ContactoEntidad> getContactoEntidadLista() {
		return contactoEntidadLista;
	}

	public void setContactoEntidadLista(List<ContactoEntidad> contactoEntidadLista) {
		this.contactoEntidadLista = contactoEntidadLista;
	}

	@PrePersist
	public void motivoPorDefecto() {
		if (motivoBloqueoEntidad == null) {
			motivoBloqueoEntidad = new MotivoBloqueoEntidad((short)1);
		}
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

	@Override
	public String toString() {
		return new StringJoiner(", ", EscuelaEntidad.class.getSimpleName() + "[", "]")
				.add("claveCentroTrabajo='" + claveCentroTrabajo + "'")
				.add("motivoBloqueoEntidad=" + motivoBloqueoEntidad)
				.add("nombre='" + nombre + "'")
				.add("contactoEntidadLista=" + contactoEntidadLista)
				.toString();
	}
}
