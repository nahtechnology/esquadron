package tecolotl.alumno.entidad.hablar;


import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaHablarEntidadPK implements Serializable {
    private HablarEntidad hablarEntidad;
    private TareaEntidad tareaEntidad;

    @JoinColumn(name = "id_hablar")
    @ManyToOne(fetch = FetchType.LAZY)
    public HablarEntidad getHablarEntidad() {
        return hablarEntidad;
    }

    public void setHablarEntidad(HablarEntidad hablarEntidad) {
        this.hablarEntidad = hablarEntidad;
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
        TareaHablarEntidadPK that = (TareaHablarEntidadPK) o;
        return hablarEntidad.equals(that.hablarEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hablarEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaHablarEntidadPK.class.getSimpleName() + "[", "]")
                .add("hablarEntidad=" + hablarEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
