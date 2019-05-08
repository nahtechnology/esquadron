package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tarea_video", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaVideoEntidad.busca", query = "SELECT a FROM TareaVideoEntidad a"),
        @NamedQuery(name = "TareaVideoEntidad.buscaTarea", query = "SELECT a FROM TareaVideoEntidad a WHERE a.tareaVideoEntidadPK.tareaEntidad.id = :id"),
        @NamedQuery(name = "TareaVideoEntidad.buscaActividad", query = "SELECT a FROM TareaVideoEntidad a WHERE a.tareaVideoEntidadPK.actividadEntidad.id = :id")
})
public class TareaVideoEntidad {
    private TareaVideoEntidadPK tareaVideoEntidadPK;
    private Short reproducciones;

    @EmbeddedId
    public TareaVideoEntidadPK getTareaVideoEntidadPK() {
        return tareaVideoEntidadPK;
    }

    public void setTareaVideoEntidadPK(TareaVideoEntidadPK tareaVideoEntidadPK) {
        this.tareaVideoEntidadPK = tareaVideoEntidadPK;
    }

    @Basic
    @Column(name = "reproducciones")
    @NotNull
    @Size(max = 32)
    public Short getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Short reproducciones) {
        this.reproducciones = reproducciones;
    }
}
