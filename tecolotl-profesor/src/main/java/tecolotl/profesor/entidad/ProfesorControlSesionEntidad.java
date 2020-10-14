package tecolotl.profesor.entidad;

import tecolotl.nucleo.persistencia.entidad.ControlSesionEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "control_sesion", schema = "profesor")
@NamedQueries(value = {
        @NamedQuery(
                name = "ProfesorControlSesionEntidad.buscaFecha",
                query = "SELECT pcs FROM ProfesorControlSesionEntidad pcs JOIN FETCH pcs.tipoRegistroEntidad tr WHERE pcs.profesorEntidad.id = :idProfesor AND " +
                        "pcs.tiempo BETWEEN :fechaInicio AND :fechaFin ORDER BY pcs.tiempo DESC ")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfesorControlSesionEntidad that = (ProfesorControlSesionEntidad) o;
        return Objects.equals(profesorEntidad, that.profesorEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profesorEntidad);
    }
}
