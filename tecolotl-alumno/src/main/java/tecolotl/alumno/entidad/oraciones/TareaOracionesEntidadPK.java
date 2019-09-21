package tecolotl.alumno.entidad.oraciones;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaOracionesEntidadPK  implements Serializable {
    private OracionesEntidad oracionesEntidad;
    private TareaEntidad tareaEntidad;

    public TareaOracionesEntidadPK() {
    }

    public TareaOracionesEntidadPK(OracionesEntidad oracionesEntidad, TareaEntidad tareaEntidad) {
        this.oracionesEntidad = oracionesEntidad;
        this.tareaEntidad = tareaEntidad;
    }


    @JoinColumns(
            value = {
                    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad"),
                    @JoinColumn(name = "codigo", referencedColumnName = "codigo"),
                    @JoinColumn(name = "cardinalidad", referencedColumnName = "cardinalidad")
            }
    )
    @ManyToOne(fetch = FetchType.LAZY)
    public OracionesEntidad getOracionesEntidad() {
        return oracionesEntidad;
    }

    public void setOracionesEntidad(OracionesEntidad oracionesEntidad) {
        this.oracionesEntidad = oracionesEntidad;
    }

    @JoinColumn(name = "id_tarea")
    @ManyToOne(fetch = FetchType.LAZY)
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TareaOracionesEntidadPK that = (TareaOracionesEntidadPK) o;
        return oracionesEntidad.equals(that.oracionesEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oracionesEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaOracionesEntidadPK.class.getSimpleName() + "[", "]")
                .add("oracionesEntidad=" + oracionesEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
