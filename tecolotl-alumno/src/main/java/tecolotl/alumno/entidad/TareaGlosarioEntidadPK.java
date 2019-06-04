package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TareaGlosarioEntidadPK implements Serializable {
    private TareaEntidad tareaEntidad;
    private GlosarioEntidad glosarioEntidad;

    @NotNull
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @NotNull
    @JoinColumn(name = "id_glosario")
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaGlosarioEntidadPK that = (TareaGlosarioEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                glosarioEntidad.equals(that.glosarioEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, glosarioEntidad);
    }
}
