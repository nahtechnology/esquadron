package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contacto", schema = "administracion")
@NamedQuery(name = "ContactoEntidad.busca", query = "SELECT m FROM ContactoEntidad m")
public class ContactoEntidad implements Serializable {
	
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_contacto", referencedColumnName="id")
	private TipoContactoEntidad tipoContactoEntidad;
    
    @Id
    @ManyToOne
    @JoinColumn(name="id_escuela", referencedColumnName="clave_centro_trabajo")
    private EscuelaEntidad escuelaEntidad;
    
    
	@Column(name = "nombre")
    @NotNull
    @Size(min = 11, max = 110)
    private String nombre;
    
	@Column(name = "telefono")
    @NotNull
    @Size(min = 11, max = 20)
    private String telefono;

    public TipoContactoEntidad getTipoContactoEntidad() {
		return tipoContactoEntidad;
	}

	public void setTipoContactoEntidad(TipoContactoEntidad tipoContactoEntidad) {
		this.tipoContactoEntidad = tipoContactoEntidad;
	}

	public EscuelaEntidad getEscuelaEntidad() {
		return escuelaEntidad;
	}

	public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
		this.escuelaEntidad = escuelaEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
