package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "estado", schema = "administracion")
@NamedQuery(name = "EstadoEntidad.busca", query = "SELECT e FROM EstadoEntidad e")
public class EstadoEntidad {

    private String id;
    private String nombre;

    @Id
    @Size(min = 3, max = 3)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 70)
    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EstadoEntidad.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("nombre='" + nombre + "'")
                .toString();
    }
}
