package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaGlosarioActividadEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private GlosarioEntidad glosarioEntidad;
    private ActividadEntidad actividadEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_glosario")
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_actividad")
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TareaGlosarioActividadEntidadPK that = (TareaGlosarioActividadEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                glosarioEntidad.equals(that.glosarioEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, glosarioEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaGlosarioActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("glosarioEntidad=" + glosarioEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
