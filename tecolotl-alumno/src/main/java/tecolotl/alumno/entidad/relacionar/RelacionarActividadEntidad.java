package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "actividad_relacionar", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(name = "RelacionarActividadEntidad.busca", query = "SELECT ra FROM RelacionarActividadEntidad ra"),
    @NamedQuery(
        name = "RelacionarActividadEntidad.buscaActividad",
        query = "SELECT ra FROM RelacionarActividadEntidad ra JOIN FETCH ra.relacionarActividadEntidadPK.glosarioEntidad g " +
                "WHERE ra.relacionarActividadEntidadPK.actividadEntidad.id = :idActividad"
    ),
    @NamedQuery(
        name = "RelacionarActividadEntidad.buscaBibliotecaLibre",
        query = "SELECT ra FROM RelacionarActividadEntidad ra WHERE ra.relacionarActividadEntidadPK.actividadEntidad.id = :idActividad"
    )
})
public class RelacionarActividadEntidad {

    private RelacionarActividadEntidadPK relacionarActividadEntidadPK;
    private List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista;

    public RelacionarActividadEntidad() {
    }

    public RelacionarActividadEntidad(RelacionarActividadEntidadPK relacionarActividadEntidadPK) {
        this.relacionarActividadEntidadPK = relacionarActividadEntidadPK;
    }

    @EmbeddedId
    public RelacionarActividadEntidadPK getRelacionarActividadEntidadPK() {
        return relacionarActividadEntidadPK;
    }

    public void setRelacionarActividadEntidadPK(RelacionarActividadEntidadPK relacionarActividadEntidadPK) {
        this.relacionarActividadEntidadPK = relacionarActividadEntidadPK;
    }

    @OneToMany(mappedBy = "tareaRelacionarActividadEntidadPK.relacionarActividadEntidad")
    public List<TareaRelacionarActividadEntidad> getTareaRelacionarActividadEntidadLista() {
        return tareaRelacionarActividadEntidadLista;
    }

    public void setTareaRelacionarActividadEntidadLista(List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista) {
        this.tareaRelacionarActividadEntidadLista = tareaRelacionarActividadEntidadLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarActividadEntidad that = (RelacionarActividadEntidad) o;
        return relacionarActividadEntidadPK.equals(that.relacionarActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionarActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarActividadEntidad.class.getSimpleName() + "[", "]")
                .add("relacionarActividadEntidadPK=" + relacionarActividadEntidadPK)
                .toString();
    }

}
