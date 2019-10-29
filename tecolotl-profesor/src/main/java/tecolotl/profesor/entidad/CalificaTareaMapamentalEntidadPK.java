package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class CalificaTareaMapamentalEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private Short cardinalidad;
    private Short vuelta;

    public CalificaTareaMapamentalEntidadPK() {
    }

    public CalificaTareaMapamentalEntidadPK(TareaEntidad tareaEntidad, Short cardinalidad, Short vuelta) {
        this.tareaEntidad = tareaEntidad;
        this.cardinalidad = cardinalidad;
        this.vuelta = vuelta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Basic
    @Column(name = "cardinalidad")
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    @Basic
    @Column(name = "vuelta")
    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalificaTareaMapamentalEntidadPK that = (CalificaTareaMapamentalEntidadPK) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                cardinalidad.equals(that.cardinalidad) &&
                vuelta.equals(that.vuelta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, cardinalidad, vuelta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalificaTareaMapamentalEntidadPK.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("cardinalidad=" + cardinalidad)
                .add("vuelta=" + vuelta)
                .toString();
    }

}
