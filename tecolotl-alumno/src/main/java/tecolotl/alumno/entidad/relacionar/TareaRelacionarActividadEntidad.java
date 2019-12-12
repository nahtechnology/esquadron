package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_actividad_relacionar", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(name = "TareaRelacionarActividadEntidad.busca", query = "SELECT tra FROM TareaRelacionarActividadEntidad tra"),
    @NamedQuery(
        name = "TareaRelacionarActividadEntidad.buscaTarea",
        query = "SELECT tra FROM TareaRelacionarActividadEntidad tra JOIN FETCH tra.tareaRelacionarActividadEntidadPK.relacionarActividadEntidad ra " +
                "JOIN FETCH ra.relacionarActividadEntidadPK.glosarioEntidad g JOIN FETCH g.glosarioEntidadPK.claseGlosarioEntidad cg " +
                "WHERE tra.tareaRelacionarActividadEntidadPK.tareaEntidad.id = :idTarea"
    ),
    @NamedQuery(
        name = "TareaRelacionarActividadEntidad.cuentaTarea",
        query = "SELECT COUNT (t) FROM TareaEntidad t JOIN t.tareaGlosarioActividadEntidadLista tga "
    ),
    @NamedQuery(
        name = "TareaRelacionarActividadEntidad.responder",
        query = "UPDATE TareaRelacionarActividadEntidad t SET t.respuesta = :respuesta WHERE t.tareaRelacionarActividadEntidadPK.tareaEntidad.id = :idTarea AND " +
                "t.tareaRelacionarActividadEntidadPK.relacionarActividadEntidad.relacionarActividadEntidadPK.glosarioEntidad.glosarioEntidadPK.palabra = :palabra AND " +
                "t.tareaRelacionarActividadEntidadPK.relacionarActividadEntidad.relacionarActividadEntidadPK.actividadEntidad.id = :idActividad AND " +
                "t.tareaRelacionarActividadEntidadPK.relacionarActividadEntidad.relacionarActividadEntidadPK.glosarioEntidad.glosarioEntidadPK.claseGlosarioEntidad.clave = :claveClase"
    )
})
public class TareaRelacionarActividadEntidad {

    private TareaRelacionarActividadEntidadPK tareaRelacionarActividadEntidadPK;
    private String respuesta;
    private Date horaRespuesta;

    public TareaRelacionarActividadEntidad() {
    }

    public TareaRelacionarActividadEntidad(TareaRelacionarActividadEntidadPK tareaRelacionarActividadEntidadPK) {
        this.tareaRelacionarActividadEntidadPK = tareaRelacionarActividadEntidadPK;
    }

    @EmbeddedId
    public TareaRelacionarActividadEntidadPK getTareaRelacionarActividadEntidadPK() {
        return tareaRelacionarActividadEntidadPK;
    }

    public void setTareaRelacionarActividadEntidadPK(TareaRelacionarActividadEntidadPK tareaRelacionarActividadEntidadPK) {
        this.tareaRelacionarActividadEntidadPK = tareaRelacionarActividadEntidadPK;
    }

    @Basic
    @Column(name = "respuesta")
    @Size(min = 32, max = 32)
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Basic
    @Column(name = "hora_respuesta")
    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaRelacionarActividadEntidad that = (TareaRelacionarActividadEntidad) o;
        return tareaRelacionarActividadEntidadPK.equals(that.tareaRelacionarActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaRelacionarActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaRelacionarActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaRelacionarActividadEntidadPK=" + tareaRelacionarActividadEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }

}
