package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaGlosarioActividadEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private GlosarioActividadEntidad glosarioActividadEntidad;

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
    @JoinColumns(value = {
            @JoinColumn(name = "id_glosario", referencedColumnName = "id_glosario"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    })
    public GlosarioActividadEntidad getGlosarioActividadEntidad() {
        return glosarioActividadEntidad;
    }

    public void setGlosarioActividadEntidad(GlosarioActividadEntidad glosarioActividadEntidad) {
        this.glosarioActividadEntidad = glosarioActividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaGlosarioActividadEntidadPK that = (TareaGlosarioActividadEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                glosarioActividadEntidad.equals(that.glosarioActividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, glosarioActividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaGlosarioActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("glosarioActividadEntidad=" + glosarioActividadEntidad)
                .toString();
    }
}
