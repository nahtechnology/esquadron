package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_escribir_actividad", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(
                name = "TareaEscribirActividadEntidad.buscaEscribir",
                query = "SELECT tea FROM TareaEscribirActividadEntidad tea JOIN FETCH  tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad ea JOIN FETCH ea.escribirActividadEntidadPK.escribirEntidad " +
                        "WHERE tea.tareaEscribirActividadEntidadPK.tareaEntidad = :idTerea AND ea.escribirActividadEntidadPK.actividadEntidad.id = :idActividad"
        )
})
public class TareaEscribirActividadEntidad {

    private TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK;
    private String respuesta;
    private Date horaRespuesta;

    @EmbeddedId
    public TareaEscribirActividadEntidadPK getTareaEscribirActividadEntidadPK() {
        return tareaEscribirActividadEntidadPK;
    }

    public void setTareaEscribirActividadEntidadPK(TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Basic
    @Column(name = "respuesta")
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
        TareaEscribirActividadEntidad that = (TareaEscribirActividadEntidad) o;
        return tareaEscribirActividadEntidadPK.equals(that.tareaEscribirActividadEntidadPK) &&
                respuesta.equals(that.respuesta) &&
                horaRespuesta.equals(that.horaRespuesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEscribirActividadEntidadPK, respuesta, horaRespuesta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaEscribirActividadEntidadPK=" + tareaEscribirActividadEntidadPK.toString())
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
