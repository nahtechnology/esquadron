package tecolotl.administracion.persistencia.entidad;


import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EscuelaEntidad {

	@Id
    private String claveCentroTrabajo;
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_municipio", referencedColumnName = "id")
    private MunicipioEntidad municipioEntidad;
	@NotNull
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_motivo_bloque", referencedColumnName = "id")
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
	public MunicipioEntidad getMunicipioEntidad() {
		return municipioEntidad;
	}
	public void setMunicipioEntidad(MunicipioEntidad municipioEntidad) {
		this.municipioEntidad = municipioEntidad;
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
