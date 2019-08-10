package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "escribir_actividad", schema = "alumno")
@NamedQuery(
        name = "EscribirActividadEntidad.buscaActivdad",
        query = "SELECT ea FROM EscribirActividadEntidad ea JOIN FETCH ea.escribirActividadEntidadPK.escribirEntidad e WHERE ea.escribirActividadEntidadPK.actividadEntidad.id = :idActividad"
)
public class EscribirActividadEntidad {

    private EscribirActividadEntidadPK escribirActividadEntidadPK;

    public EscribirActividadEntidad() {
    }

    public EscribirActividadEntidad(EscribirActividadEntidadPK escribirActividadEntidadPK) {
        this.escribirActividadEntidadPK = escribirActividadEntidadPK;
    }

    @EmbeddedId
    public EscribirActividadEntidadPK getEscribirActividadEntidadPK() {
        return escribirActividadEntidadPK;
    }

    public void setEscribirActividadEntidadPK(EscribirActividadEntidadPK escribirActividadEntidadPK) {
        this.escribirActividadEntidadPK = escribirActividadEntidadPK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscribirActividadEntidad that = (EscribirActividadEntidad) o;
        return escribirActividadEntidadPK.equals(that.escribirActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escribirActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirActividadEntidad.class.getSimpleName() + "[", "]")
                .add("escribirActividadEntidadPK=" + escribirActividadEntidadPK)
                .toString();
    }
}
