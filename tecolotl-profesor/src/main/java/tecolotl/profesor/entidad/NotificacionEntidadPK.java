package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NotificacionEntidadPK implements Serializable {

    private AlumnoEntidad alumnoEntidad;
    private ProfesorEntidad profesorEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificacionEntidadPK that = (NotificacionEntidadPK) o;
        return alumnoEntidad.equals(that.alumnoEntidad) &&
                profesorEntidad.equals(that.profesorEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoEntidad, profesorEntidad);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificacionEntidadPK{");
        sb.append("alumnoEntidad=").append(alumnoEntidad);
        sb.append(", profesorEntidad=").append(profesorEntidad);
        sb.append('}');
        return sb.toString();
    }
}
