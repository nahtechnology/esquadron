package tecolotl.alumno.entidad.relacionar_oraciones;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaRelacionarOracionesEntidadPK implements Serializable {
    private RelacionarOracionesEntidad relacionarOracionesEntidad;
    private TareaEntidad tareaEntidad;

    @JoinColumn(name = "id_relacionar_oraciones")
    @ManyToOne(fetch = FetchType.LAZY)
    public RelacionarOracionesEntidad getRelacionarOracionesEntidad() {
        return relacionarOracionesEntidad;
    }

    public void setRelacionarOracionesEntidad(RelacionarOracionesEntidad relacionarOracionesEntidad) {
        this.relacionarOracionesEntidad = relacionarOracionesEntidad;
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
        TareaRelacionarOracionesEntidadPK that = (TareaRelacionarOracionesEntidadPK) o;
        return relacionarOracionesEntidad.equals(that.relacionarOracionesEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionarOracionesEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaRelacionarOracionesEntidadPK.class.getSimpleName() + "[", "]")
                .add("relacionarOracionesEntidad=" + relacionarOracionesEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
