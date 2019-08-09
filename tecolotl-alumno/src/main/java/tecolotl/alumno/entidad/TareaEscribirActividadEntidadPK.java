package tecolotl.alumno.entidad;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaEscribirActividadEntidadPK implements Serializable {

    private EscribirActividadEntidad escribirActividadEntidad;
    private TareaEntidad tareaEntidad;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_escribir", referencedColumnName = "id_escribir"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    })
    public EscribirActividadEntidad getEscribirActividadEntidad() {
        return escribirActividadEntidad;
    }

    public void setEscribirActividadEntidad(EscribirActividadEntidad escribirActividadEntidad) {
        this.escribirActividadEntidad = escribirActividadEntidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaEscribirActividadEntidadPK that = (TareaEscribirActividadEntidadPK) o;
        return escribirActividadEntidad.equals(that.escribirActividadEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escribirActividadEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirActividadEntidad=" + escribirActividadEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
