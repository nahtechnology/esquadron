package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "municipio", schema = "administracion")
@NamedQueries({
        @NamedQuery(name = "MunicipioEntidad.busca", query = "SELECT m FROM MunicipioEntidad m LEFT JOIN FETCH m.estado")

})
public class MunicipioEntidad {

    private Integer id;
    private String nombre;
    private EstadoEntidad estado;

    public MunicipioEntidad() {
    }

    public MunicipioEntidad(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    @NotNull
    @Size(min = 3, max = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    public EstadoEntidad getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntidad estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MunicipioEntidad.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nombre='" + nombre + "'")
                .add("estado=" + estado)
                .toString();
    }
}
