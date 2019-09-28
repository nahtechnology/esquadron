package tecolotl.alumno.entidad.oraciones;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_oraciones", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "TareaOracionesEntidad.busca", query = "SELECT toe from TareaOracionesEntidad toe"),
        @NamedQuery(
                name = "TareaOracionesEntidad.buscaidTarea",
                query = "SELECT toe FROM TareaOracionesEntidad toe JOIN FETCH toe.tareaOracionesEntidadPK.oracionesEntidad oe " +
                        "WHERE toe.tareaOracionesEntidadPK.tareaEntidad.id = :idTarea ORDER BY toe.tareaOracionesEntidadPK.oracionesEntidad.oracionesEntidadPK.cardinalidad")
})
public class TareaOracionesEntidad {
    private TareaOracionesEntidadPK tareaOracionesEntidadPK;
    private Short respuesta;
    private Date horaRespuesta;

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
    public Short getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Short respuesta) {
        this.respuesta = respuesta;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "hora_respuesta", insertable = false)
    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaOracionesEntidad.class.getSimpleName() + "[", "]")
                .add("tareaOracionesEntidadPK=" + tareaOracionesEntidadPK)
                .add("respuesta='" + respuesta + "'")
                .add("hora_Respuesta=" + horaRespuesta)
                .toString();
    }

}
