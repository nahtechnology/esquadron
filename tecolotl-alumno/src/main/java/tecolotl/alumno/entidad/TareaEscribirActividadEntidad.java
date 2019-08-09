package tecolotl.alumno.entidad;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_escribir_actividad", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(
                name = "TareaEscribirActividadEntidad.buscaEscribir",
                query = "SELECT tea FROM TareaEscribirActividadEntidad tea JOIN FETCH tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad ea JOIN FETCH ea.escribirActividadEntidadPK.escribirEntidad e"
        )
})
public class TareaEscribirActividadEntidad {

    private TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK;
    private String textoRespuesta;
    private Date horaRespuesta;

    @EmbeddedId
    public TareaEscribirActividadEntidadPK getTareaEscribirActividadEntidadPK() {
        return tareaEscribirActividadEntidadPK;
    }

    public void setTareaEscribirActividadEntidadPK(TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Basic
    @Column(name = "texto_respuesta")
    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
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
                textoRespuesta.equals(that.textoRespuesta) &&
                horaRespuesta.equals(that.horaRespuesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEscribirActividadEntidadPK, textoRespuesta, horaRespuesta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaEscribirActividadEntidadPK=" + tareaEscribirActividadEntidadPK.toString())
                .add("respuesta='" + textoRespuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
