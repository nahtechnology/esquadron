package tecolotl.alumno.entidad.gramatica;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_gramatica", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "TareaGramaticaEntidad.busca", query = "SELECT tge FROM TareaGramaticaEntidad tge"),
        @NamedQuery(name = "TareaGramaticaEntidad.buscaid_tarea", query = "SELECT tge FROM TareaGramaticaEntidad tge where tge.tareaGramaticaEntidadPK.tareaEntidad.id = :id_tarea")
}
)
public class TareaGramaticaEntidad {
    private TareaGramaticaEntidadPK tareaGramaticaEntidadPK;
    private String respuesta;
    private Date hora_respuesta;

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
        return new StringJoiner(", ", TareaGramaticaEntidad.class.getSimpleName() + "[", "]")
                .add("tareaGramaticaEntidadPK=" + tareaGramaticaEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_respuesta=" + hora_respuesta)
                .toString();
    }
}
