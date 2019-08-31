package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "actividad_relacionar", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "RelacionarActividadEntidad.busca", query = "SELECT ra FROM RelacionarActividadEntidad ra"),
        @NamedQuery(
                name = "RelacionarActividadEntidad.buscaActividad",
                query = "SELECT ra FROM RelacionarActividadEntidad ra JOIN FETCH ra.relacionarActividadEntidadPK.relacionarEntidad r " +
                        "JOIN FETCH ra.relacionarActividadEntidadPK.actividadEntidad a WHERE ra.relacionarActividadEntidadPK.actividadEntidad.id = :idActividad"
        )
})
public class RelacionarActividadEntidad {

    private RelacionarActividadEntidadPK relacionarActividadEntidadPK;

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
