package tecolotl.alumno.entidad;

import javax.persistence.*;

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

}
