package tecolotl.alumno.entidad;

import tecolotl.nucleo.persistencia.entidad.ControlSesionEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "control_sesion", schema = "alumno")
@NamedQueries( value = {
        @NamedQuery (
                name = "AlumnoControlSesionEntidad.buscaAlumno",
                query = "SELECT acs FROM AlumnoControlSesionEntidad acs JOIN FETCH acs.tipoRegistroEntidad WHERE acs.alumnoEntidad.id = :idAlumno AND " +
                        "acs.tiempo BETWEEN :fechaInicio AND :fechaFin ORDER BY acs.tiempo DESC")
})
public class AlumnoControlSesionEntidad extends ControlSesionEntidad implements Serializable {

    private AlumnoEntidad alumnoEntidad;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_alumno")
    public AlumnoEntidad getAlumnoEntidad() {
        return alumnoEntidad;
    }

    public void setAlumnoEntidad(AlumnoEntidad alumnoEntidad) {
        this.alumnoEntidad = alumnoEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoControlSesionEntidad that = (AlumnoControlSesionEntidad) o;
        return alumnoEntidad.equals(that.alumnoEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoEntidad);
    }
}
