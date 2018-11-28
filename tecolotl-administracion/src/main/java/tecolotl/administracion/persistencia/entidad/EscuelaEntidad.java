package tecolotl.administracion.persistencia.entidad;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EscuelaEntidad {

	@Id
    private String claveCentroTrabajo;
	
    private int idMunicipio;
    private int idMotivoBloqueo;
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

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getIdMotivoBloqueo() {
		return idMotivoBloqueo;
	}

	public void setIdMotivoBloqueo(int idMotivoBloqueo) {
		this.idMotivoBloqueo = idMotivoBloqueo;
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
