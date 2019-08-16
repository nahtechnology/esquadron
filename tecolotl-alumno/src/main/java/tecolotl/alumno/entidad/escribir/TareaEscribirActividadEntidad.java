package tecolotl.alumno.entidad.escribir;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_escribir_actividad", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.buscaEscribir",
        query = "SELECT tea FROM TareaEscribirActividadEntidad tea JOIN tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad ea " +
                "JOIN ea.escribirEntidad e WHERE tea.tareaEntidad.id = :idTarea"
    ),
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.buscaTarea",
        query = "SELECT tea FROM TareaEscribirActividadEntidad tea JOIN FETCH tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad ea" +
                " JOIN FETCH ea.escribirEntidad e WHERE tea.tareaEntidad.id = :idTarea"
    ),
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.busca",
        query = "SELECT tea FROM TareaEscribirActividadEntidad tea WHERE tea.tareaEntidad.id = :idTarea AND " +
                "tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad.escribirEntidad.id = :idEscribir AND " +
                "tea.tareaEscribirActividadEntidadPK.escribirActividadEntidad.actividadEntidad.id = :idActividad"
    )
})
public class TareaEscribirActividadEntidad implements Serializable {

    private TareaEntidad tareaEntidad;
    private TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK;
    private String textRespuesta;
    private Date horaRespuesta;

    public TareaEscribirActividadEntidad() {
    }

    public TareaEscribirActividadEntidad(TareaEntidad tareaEntidad, TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEntidad = tareaEntidad;
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @EmbeddedId
    public TareaEscribirActividadEntidadPK getTareaEscribirActividadEntidadPK() {
        return tareaEscribirActividadEntidadPK;
    }

    public void setTareaEscribirActividadEntidadPK(TareaEscribirActividadEntidadPK tareaEscribirActividadEntidadPK) {
        this.tareaEscribirActividadEntidadPK = tareaEscribirActividadEntidadPK;
    }

    @Basic
    @Column(name = "texto_respuesta", insertable = false)
    public String getTextRespuesta() {
        return textRespuesta;
    }

    public void setTextRespuesta(String textRespuesta) {
        this.textRespuesta = textRespuesta;
    }

    @Basic
    @Column(name = "hora_respuesta", insertable = false)
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
        return tareaEntidad.equals(that.tareaEntidad) &&
                tareaEscribirActividadEntidadPK.equals(that.tareaEscribirActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, tareaEscribirActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("tareaEscribirActividadEntidadPK=" + tareaEscribirActividadEntidadPK)
                .add("textRespuesta='" + textRespuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
