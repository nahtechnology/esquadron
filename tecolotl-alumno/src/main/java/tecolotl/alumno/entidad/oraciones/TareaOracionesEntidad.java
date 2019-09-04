package tecolotl.alumno.entidad.oraciones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_oraciones", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaOracionesEntidad.busca", query = "SELECT toe from TareaOracionesEntidad toe"),
        @NamedQuery(name = "TareaOracionesEntidad.buscaid_tarea", query = "SELECT toe FROM TareaOracionesEntidad toe where toe.tareaOracionesEntidadPK.tareaEntidad.id = :id_tarea")
}
)
public class TareaOracionesEntidad {
    private TareaOracionesEntidadPK tareaOracionesEntidadPK;
    private String respuesta;
    private Date hora_Respuesta;

    public TareaOracionesEntidad() {
    }

    public TareaOracionesEntidad(TareaOracionesEntidadPK tareaOracionesEntidadPK) {
        this.tareaOracionesEntidadPK = tareaOracionesEntidadPK;
    }

    @EmbeddedId
    public TareaOracionesEntidadPK getTareaOracionesEntidadPK() {
        return tareaOracionesEntidadPK;
    }

    public void setTareaOracionesEntidadPK(TareaOracionesEntidadPK tareaOracionesEntidadPK) {
        this.tareaOracionesEntidadPK = tareaOracionesEntidadPK;
    }

    @NotNull
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
    public Date getHora_Respuesta() {
        return hora_Respuesta;
    }

    public void setHora_Respuesta(Date hora_Respuesta) {
        this.hora_Respuesta = hora_Respuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaOracionesEntidad.class.getSimpleName() + "[", "]")
                .add("tareaOracionesEntidadPK=" + tareaOracionesEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_Respuesta=" + hora_Respuesta)
                .toString();
    }
}
