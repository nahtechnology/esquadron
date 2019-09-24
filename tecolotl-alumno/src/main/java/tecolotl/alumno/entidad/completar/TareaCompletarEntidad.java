package tecolotl.alumno.entidad.completar;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_completar", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaCompletarEntidad.busca", query = "SELECT tce FROM TareaCompletarEntidad tce"),
        @NamedQuery(
                name = "TareaCompletarEntidad.buscaidTarea",
                query = "SELECT tce FROM TareaCompletarEntidad tce JOIN FETCH tce.tareaCompletarEntidadPK.completarEntidad " +
                        "WHERE tce.tareaCompletarEntidadPK.tareaEntidad.id = :idTarea")
})
public class TareaCompletarEntidad {
    private TareaCompletarEntidadPK tareaCompletarEntidadPK;
    private String respuesta;
    private Date hora_respuesta;

    public TareaCompletarEntidad() {
    }

    public TareaCompletarEntidad(TareaCompletarEntidadPK tareaCompletarEntidadPK) {
        this.tareaCompletarEntidadPK = tareaCompletarEntidadPK;
    }

    @EmbeddedId
    public TareaCompletarEntidadPK getTareaCompletarEntidadPK() {
        return tareaCompletarEntidadPK;
    }

    public void setTareaCompletarEntidadPK(TareaCompletarEntidadPK tareaCompletarEntidadPK) {
        this.tareaCompletarEntidadPK = tareaCompletarEntidadPK;
    }

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "respuesta")
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @NotNull
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "hora_respuesta", insertable = false)
    public Date getHora_respuesta() {
        return hora_respuesta;
    }

    public void setHora_respuesta(Date hora_respuesta) {
        this.hora_respuesta = hora_respuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaCompletarEntidad.class.getSimpleName() + "[", "]")
                .add("tareaCompletarEntidadPK=" + tareaCompletarEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_respuesta=" + hora_respuesta)
                .toString();
    }
}
