package tecolotl.alumno.entidad.relacionar_oraciones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_relacionar_oracion", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaRelacionarOracionesEntidad.busca", query = "SELECT troe FROM TareaRelacionarOracionesEntidad troe"),
        @NamedQuery(name = "TareaRelacionarOracionesEntidad.buscaid_tarea", query = "SELECT troe FROM TareaRelacionarOracionesEntidad troe where troe.tareaRelacionarOracionesEntidadPK.tareaEntidad.id =  :id_tarea")
}
)
public class TareaRelacionarOracionesEntidad {
    private TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK;
    private String respuesta;
    private Date hora_respuesta;

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
        return new StringJoiner(", ", TareaRelacionarOracionesEntidad.class.getSimpleName() + "[", "]")
                .add("tareaRelacionarOracionesEntidadPK=" + tareaRelacionarOracionesEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_respuesta=" + hora_respuesta)
                .toString();
    }
}



