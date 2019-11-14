package tecolotl.alumno.entidad.completar;

import org.hibernate.mapping.Join;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaCompletarEntidadPK implements Serializable {
    private CompletarEntidad completarEntidad;
    private TareaEntidad tareaEntidad;
    private Short vuelta;

    @JoinColumn(name = "id_completar")
    @ManyToOne(fetch = FetchType.LAZY)
    public CompletarEntidad getCompletarEntidad() {
        return completarEntidad;
    }

    public void setCompletarEntidad(CompletarEntidad completarEntidad) {
        this.completarEntidad = completarEntidad;
    }

    @JoinColumn(name = "id_tarea")
    @ManyToOne(fetch = FetchType.LAZY)
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TareaCompletarEntidadPK that = (TareaCompletarEntidadPK) o;
        return completarEntidad.equals(that.completarEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completarEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaCompletarEntidadPK.class.getSimpleName() + "[", "]")
                .add("completarEntidad=" + completarEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
