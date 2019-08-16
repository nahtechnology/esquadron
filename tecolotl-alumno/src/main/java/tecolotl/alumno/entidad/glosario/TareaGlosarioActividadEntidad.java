package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_glosario_actividad", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGlosarioActividadEntidad.busca", query = "SELECT tga FROM TareaGlosarioActividadEntidad tga")
})
public class TareaGlosarioActividadEntidad {

    private TareaGlosarioActividadEntidadPK tareaGlosarioActividadEntidadPK;

    public TareaGlosarioActividadEntidad() {
    }

    public TareaGlosarioActividadEntidad(TareaGlosarioActividadEntidadPK tareaGlosarioActividadEntidadPK) {
        this.tareaGlosarioActividadEntidadPK = tareaGlosarioActividadEntidadPK;
    }

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
