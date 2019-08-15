package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_escribir_actividad", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.buscaEscribir",
        query = "SELECT tea FROM TareaEscribirActividadEntidad tea "
    )
})
public class TareaEscribirActividadEntidad implements Serializable {

    private TareaEntidad tareaEntidad;
    private TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK;

    public TareaEscribirActividadEntidad() {
    }

    public TareaEscribirActividadEntidad(TareaEntidad tareaEntidad, TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEntidad = tareaEntidad;
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @EmbeddedId
    public TareaEscribirActividadEntidadPK getTareaEscribirActividadEntidadPK() {
        return tareaEscribirActividadEntidadPK;
    }

    public void setTareaEscribirActividadEntidadPK(TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaEscribirActividadEntidad that = (TareaEscribirActividadEntidad) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                tareaEscribirActividadEntidadPK.equals(that.tareaEscribirActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, tareaEscribirActividadEntidadPK);
    }

}
