package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaVideoEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private ActividadEntidad actividadEntidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad", referencedColumnName = "id_video")
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
        TareaVideoEntidadPK that = (TareaVideoEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaVideoEntidadPK.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
