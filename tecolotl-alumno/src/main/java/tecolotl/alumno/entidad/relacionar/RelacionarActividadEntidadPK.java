package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class RelacionarActividadEntidadPK implements Serializable {

    private RelacionarEntidad relacionarEntidad;
    private ActividadEntidad actividadEntidad;

    public RelacionarActividadEntidadPK() {
    }

    public RelacionarActividadEntidadPK(RelacionarEntidad relacionarEntidad, ActividadEntidad actividadEntidad) {
        this.relacionarEntidad = relacionarEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @JoinColumn(name = "id_relacionar")
    @ManyToOne(fetch = FetchType.LAZY)
    public RelacionarEntidad getRelacionarEntidad() {
        return relacionarEntidad;
    }

    public void setRelacionarEntidad(RelacionarEntidad relacionarEntidad) {
        this.relacionarEntidad = relacionarEntidad;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarActividadEntidadPK that = (RelacionarActividadEntidadPK) o;
        return relacionarEntidad.equals(that.relacionarEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionarEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("relacionarEntidad=" + relacionarEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
