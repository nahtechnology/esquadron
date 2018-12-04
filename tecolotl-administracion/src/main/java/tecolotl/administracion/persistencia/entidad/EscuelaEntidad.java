package tecolotl.administracion.persistencia.entidad;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "escuela", schema = "administracion")
@NamedQuery(name = "EscuelaEntidad.busca", query = "SELECT e from EscuelaEntidad e")
public class EscuelaEntidad {

	@Id
	@Column(name = "clave_centro_trabajo")
    private String claveCentroTrabajo;

	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_colonia", referencedColumnName = "id")
    private ColoniaEntidad coloniaEntidad;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_motivo_bloqueo", referencedColumnName = "id")
    private MotivoBloqueoEntidad motivoBloqueoEntidad;

	@Column(name = "nombre")
    @NotNull
    @Size(min = 11, max = 50)
    private String nombre;

	@Column(name = "domicilio")
    @NotNull
    @Size(min = 11, max = 60)
	private String domicilio;

	public String getClaveCentroTrabajo() {
		return claveCentroTrabajo;
	}

	public void setClaveCentroTrabajo(String claveCentroTrabajo) {
		this.claveCentroTrabajo = claveCentroTrabajo;
	}

	public ColoniaEntidad getColoniaEntidad() {
		return coloniaEntidad;
	}

	public void setColoniaEntidad(ColoniaEntidad coloniaEntidad) {
		this.coloniaEntidad = coloniaEntidad;
	}

	public MotivoBloqueoEntidad getMotivoBloqueoEntidad() {
		return motivoBloqueoEntidad;
	}

	public void setMotivoBloqueoEntidad(MotivoBloqueoEntidad motivoBloqueoEntidad) {
		this.motivoBloqueoEntidad = motivoBloqueoEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
}
