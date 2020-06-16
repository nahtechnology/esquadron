package tecolotl.profesor.entidad;

import tecolotl.nucleo.persistencia.entidad.ControlSesionEntidad;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "control_sesion", schema = "profesor")
@NamedQueries(value = {
        @NamedQuery(
                name = "ProfesorControlSesionEntidad.buscaFecha",
                query = "SELECT pcs FROM ProfesorControlSesionEntidad pcs WHERE pcs.profesorEntidad.id = :idProfesor AND " +
                        "pcs.tiempo BETWEEN :fechaInicio AND :fechaFin")
})
public class ProfesorControlSesionEntidad extends ControlSesionEntidad implements Serializable {

    private ProfesorEntidad profesorEntidad;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }
}
