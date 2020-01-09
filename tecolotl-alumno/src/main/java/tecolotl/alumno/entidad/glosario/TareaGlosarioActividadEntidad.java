package tecolotl.alumno.entidad.glosario;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_glosario_actividad", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "TareaGlosarioActividadEntidad.busca", query = "SELECT tga FROM TareaGlosarioActividadEntidad tga"),
        @NamedQuery(
                name = "TareaGlosarioActividadEntidad.buscaTarea",
                query = "SELECT tga FROM TareaGlosarioActividadEntidad tga JOIN FETCH tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad ga " +
                        "JOIN FETCH ga.glosarioActividadEntidadPK.glosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg " +
                        "WHERE tga.tareaGlosarioActividadEntidadPK.tareaEntidad.id = :idTarea"),
        @NamedQuery(
                name = "TareaGlosarioActividadEntidad.buscaActividadPorTarea",
                query = "SELECT tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad.glosarioActividadEntidadPK.actividadEntidad.id FROM TareaGlosarioActividadEntidad tga WHERE " +
                        "tga.tareaGlosarioActividadEntidadPK.tareaEntidad.id = :idTarea GROUP BY tga.tareaGlosarioActividadEntidadPK.glosarioActividadEntidad.glosarioActividadEntidadPK.actividadEntidad.id"
        )
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
