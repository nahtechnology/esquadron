package tecolotl.alumno.entidad.relacionar_oraciones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_relacionar_oracion", schema = "alumno")
@NamedQueries({
    @NamedQuery(name = "TareaRelacionarOracionesEntidad.busca", query = "SELECT troe FROM TareaRelacionarOracionesEntidad troe"),
    @NamedQuery(
        name = "TareaRelacionarOracionesEntidad.buscaidTarea",
        query = "SELECT troe FROM TareaRelacionarOracionesEntidad troe JOIN FETCH troe.tareaRelacionarOracionesEntidadPK.relacionarOracionesEntidad " +
                "WHERE troe.tareaRelacionarOracionesEntidadPK.tareaEntidad.id =  :idTarea ORDER BY troe.tareaRelacionarOracionesEntidadPK.relacionarOracionesEntidad.id"),
    @NamedQuery(
        name = "TareaRelacionarOracionesEntidad.respuesta",
        query = "UPDATE TareaRelacionarOracionesEntidad tro SET tro.respuesta = :respuesta WHERE tro.tareaRelacionarOracionesEntidadPK.vuelta = :vuelta AND " +
                "tro.tareaRelacionarOracionesEntidadPK.tareaEntidad.id = :idTarea AND tro.tareaRelacionarOracionesEntidadPK.relacionarOracionesEntidad.id = :idOracionRelacionar")
})
public class TareaRelacionarOracionesEntidad {
    private TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK;
    private Integer respuesta;
    private Date horaRespuesta;

    public TareaRelacionarOracionesEntidad() {
    }

    public TareaRelacionarOracionesEntidad(TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK) {
        this.tareaRelacionarOracionesEntidadPK = tareaRelacionarOracionesEntidadPK;
    }

    @EmbeddedId
    public TareaRelacionarOracionesEntidadPK getTareaRelacionarOracionesEntidadPK() {
        return tareaRelacionarOracionesEntidadPK;
    }

    public void setTareaRelacionarOracionesEntidadPK(TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK) {
        this.tareaRelacionarOracionesEntidadPK = tareaRelacionarOracionesEntidadPK;
    }

    @NotNull
    @Basic
    @Column(name = "respuesta")
    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "hora_respuesta", insertable = false, updatable = false)
    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaRelacionarOracionesEntidad.class.getSimpleName() + "[", "]")
                .add("tareaRelacionarOracionesEntidadPK=" + tareaRelacionarOracionesEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_respuesta=" + horaRespuesta)
                .toString();
    }
}
