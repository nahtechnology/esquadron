package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "escribir_actividad", schema = "alumno")
@NamedQuery(
        name = "EscribirActividadEntidad.buscaActivdad",
        query = "SELECT ea FROM EscribirActividadEntidad ea JOIN FETCH ea.escribirEntidad e WHERE ea.actividadEntidad.id = :idActividad"
)
public class EscribirActividadEntidad implements Serializable {

    private EscribirEntidad escribirEntidad;
    private ActividadEntidad actividadEntidad;

    public EscribirActividadEntidad() {
    }

    public EscribirActividadEntidad(EscribirEntidad escribirEntidad, ActividadEntidad actividadEntidad) {
        this.escribirEntidad = escribirEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escribir")
    public EscribirEntidad getEscribirEntidad() {
        return escribirEntidad;
    }

    public void setEscribirEntidad(EscribirEntidad escribirEntidad) {
        this.escribirEntidad = escribirEntidad;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
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
        EscribirActividadEntidad that = (EscribirActividadEntidad) o;
        return escribirEntidad.equals(that.escribirEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escribirEntidad, actividadEntidad);
    }

}
