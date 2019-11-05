package tecolotl.alumno.entidad.gramatica;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_gramatica", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGramaticaEntidad.busca", query = "SELECT tge FROM TareaGramaticaEntidad tge"),
        @NamedQuery(name = "TareaGramaticaEntidad.buscaTarea", query = "SELECT tge FROM TareaGramaticaEntidad tge " +
                "JOIN FETCH tge.tareaGramaticaEntidadPK.gramaticaEntidad g WHERE tge.tareaGramaticaEntidadPK.tareaEntidad.id = :idTarea")
})
public class TareaGramaticaEntidad implements Serializable {
    private TareaGramaticaEntidadPK tareaGramaticaEntidadPK;
    private String respuesta;
    private Date horaRespuesta;

    public TareaGramaticaEntidad() {
    }

    public TareaGramaticaEntidad(TareaGramaticaEntidadPK tareaGramaticaEntidadPK) {
        this.tareaGramaticaEntidadPK = tareaGramaticaEntidadPK;
    }

    @EmbeddedId
    public TareaGramaticaEntidadPK getTareaGramaticaEntidadPK() {
        return tareaGramaticaEntidadPK;
    }

    public void setTareaGramaticaEntidadPK(TareaGramaticaEntidadPK tareaGramaticaEntidadPK) {
        this.tareaGramaticaEntidadPK = tareaGramaticaEntidadPK;
    }

    @NotNull
    @Column(name = "respuesta")
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
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
        return new StringJoiner(", ", TareaGramaticaEntidad.class.getSimpleName() + "[", "]")
                .add("tareaGramaticaEntidadPK=" + tareaGramaticaEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_respuesta=" + horaRespuesta)
                .toString();
    }
}
