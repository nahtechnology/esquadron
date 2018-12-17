package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_contacto", schema = "administracion")
@NamedQuery(name = "TipoContactoEntidad.busca", query = "SELECT m FROM TipoContactoEntidad m")
public class TipoContactoEntidad {

	@Id
	private int id;

    @Basic
    @Column(name = "descripcion")
    @NotNull
    @Size(min = 11, max = 30)
    private String descripcion;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

