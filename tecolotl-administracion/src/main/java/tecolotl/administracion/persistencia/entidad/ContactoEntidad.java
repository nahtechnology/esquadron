package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contacto", schema = "administracion")
@NamedQueries({
		@NamedQuery(name = "ContactoEntidad.busca", query = "SELECT m FROM ContactoEntidad m"),
		@NamedQuery(
				name = "ContactoEntidad.buscaCCT",
				query = "SELECT m FROM ContactoEntidad m WHERE m.contactoEntidadPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"
		)
})
public class ContactoEntidad implements Serializable {

    private ContactoEntidadPK contactoEntidadPK;
    private String nombre;
    private String telefono;
    private String correoElectronico;

	public ContactoEntidad() {
	}

	public ContactoEntidad(ContactoEntidadPK contactoEntidadPK) {
		this.contactoEntidadPK = contactoEntidadPK;
	}

	@EmbeddedId
	public ContactoEntidadPK getContactoEntidadPK() {
		return contactoEntidadPK;
	}

	public void setContactoEntidadPK(ContactoEntidadPK contactoEntidadPK) {
		this.contactoEntidadPK = contactoEntidadPK;
	}

	@Basic
	@Column(name = "nombre")
	@NotNull
	@Size(min = 11, max = 110)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic
	@Column(name = "telefono")
	@NotNull
	@Size(min = 7, max = 20)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Basic
	@Column(name = "correo_electronico")
	@Size(max = 100)
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
