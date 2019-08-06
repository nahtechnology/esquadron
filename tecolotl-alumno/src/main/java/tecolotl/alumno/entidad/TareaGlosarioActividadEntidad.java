package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_glosario_actividad", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGlosarioEntidad.busca", query = "SELECT a FROM TareaGlosarioActividadEntidad a")
})
public class TareaGlosarioActividadEntidad {

    private TareaGlosarioActividadEntidadPK tareaGlosarioActividadEntidadPK;

    @EmbeddedId
    public TareaGlosarioActividadEntidadPK getTareaGlosarioActividadEntidadPK() {
        return tareaGlosarioActividadEntidadPK;
    }

    public void setTareaGlosarioActividadEntidadPK(TareaGlosarioActividadEntidadPK tareaGlosarioActividadEntidadPK) {
        this.tareaGlosarioActividadEntidadPK = tareaGlosarioActividadEntidadPK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaGlosarioActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaGlosarioActividadEntidadPK=" + tareaGlosarioActividadEntidadPK)
                .toString();
    }
}
