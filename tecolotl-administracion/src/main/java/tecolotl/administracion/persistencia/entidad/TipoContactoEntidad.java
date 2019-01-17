package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_contacto", schema = "administracion")
@NamedQuery(name = "TipoContactoEntidad.busca", query = "SELECT tc FROM TipoContactoEntidad tc WHERE NOT (tc.descripcion = :descripcion) ORDER BY tc.descripcion")
public class TipoContactoEntidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

    @Basic
    @Column(name = "descripcion")
    @NotNull
    @Size(min = 7, max = 30)
    private String descripcion;

    public TipoContactoEntidad() {
    }

    public TipoContactoEntidad(Short id) {
        this.id = id;
    }

    public TipoContactoEntidad(Short id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }


    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

