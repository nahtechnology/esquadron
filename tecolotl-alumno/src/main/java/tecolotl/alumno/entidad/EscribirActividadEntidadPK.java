package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class EscribirActividadEntidadPK implements Serializable {

    private EscribirEntidad escribirEntidad;
    private ActividadEntidad actividadEntidad;

    public EscribirActividadEntidadPK() {
    }

    public EscribirActividadEntidadPK(EscribirEntidad escribirEntidad, ActividadEntidad actividadEntidad) {
        this.escribirEntidad = escribirEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_escribir")
    public EscribirEntidad getEscribirEntidad() {
        return escribirEntidad;
    }

    public void setEscribirEntidad(EscribirEntidad escribirEntidad) {
        this.escribirEntidad = escribirEntidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_actividad")
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
        EscribirActividadEntidadPK that = (EscribirActividadEntidadPK) o;
        return escribirEntidad.equals(that.escribirEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escribirEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirEntidad=" + escribirEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
