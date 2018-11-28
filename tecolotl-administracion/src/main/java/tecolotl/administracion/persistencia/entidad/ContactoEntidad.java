package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacto", schema = "administracion")
@NamedQuery(name = "Contacto.busca", query = "SELECT m FROM Contacto m")
public class ContactoEntidad {
	@Id
	@Column(name = "id_escuela")
    private String idEscuela;
	
    private TipoContactoEntidad tipoContactoEntidad;
	@Column(name = "nombre")
    @NotNull
    @Size(min = 11, max = 110)
    private String nombre;
    @Column(name = "telefono")
    @NotNull
    @Size(min = 11, max = 20)
    private String domicilio;
    @Column(name = "correo_electronico")
    @NotNull
    @Size(min = 11, max = 100)
    private String correoElectronico;
	public String getIdEscuela() {
		return idEscuela;
	}
	public void setIdEscuela(String idEscuela) {
		this.idEscuela = idEscuela;
	}
	public TipoContactoEntidad getTipoContactoEntidad() {
		return tipoContactoEntidad;
	}
	public void setTipoContactoEntidad(TipoContactoEntidad tipoContactoEntidad) {
		this.tipoContactoEntidad = tipoContactoEntidad;
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
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


}
