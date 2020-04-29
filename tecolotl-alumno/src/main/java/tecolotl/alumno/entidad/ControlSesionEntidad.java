package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "control_sesion", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(
                name = "ControlSesionEntidad.buscaAlumno",
                query = "SELECT cs FROM ControlSesionEntidad cs WHERE cs.alumnoEntidad.id = :idAlumno")
})
public class ControlSesionEntidad extends tecolotl.nucleo.persistencia.entidad.ControlSesionEntidad implements Serializable {

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

    @PrePersist
    public void momento() {
        setTiempo(new Date());
    }

}
