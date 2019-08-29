package tecolotl.alumno.entidad.mapamental;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_mapamental_actividad", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.buscaEscribir",
        query = "SELECT tea FROM TareaMapaMentalActividadEntidad tea JOIN tea.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad ea " +
                "JOIN ea.mapaMentalEntidad e WHERE tea.tareaEntidad.id = :idTarea"
    ),
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.buscaTarea",
        query = "SELECT tea FROM TareaMapaMentalActividadEntidad tea JOIN FETCH tea.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad ea" +
                " JOIN FETCH ea.mapaMentalEntidad e WHERE tea.tareaEntidad.id = :idTarea"
    ),
    @NamedQuery(
        name = "TareaEscribirActividadEntidad.busca",
        query = "SELECT tea FROM TareaMapaMentalActividadEntidad tea WHERE tea.tareaEntidad.id = :idTarea AND " +
                "tea.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad.mapaMentalEntidad.id = :idEscribir AND " +
                "tea.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad.actividadEntidad.id = :idActividad"
    )
})
public class TareaMapaMentalActividadEntidad implements Serializable {

    private TareaEntidad tareaEntidad;
    private TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK;
    private String textRespuesta;
    private Date horaRespuesta;

    public TareaMapaMentalActividadEntidad() {
    }

    public TareaMapaMentalActividadEntidad(TareaEntidad tareaEntidad, TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK) {
        this.tareaEntidad = tareaEntidad;
        this.tareaMapaMentalActividadEntidadPK = tareaMapaMentalActividadEntidadPK;
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
    public TareaMapaMentalActividadEntidadPK getTareaMapaMentalActividadEntidadPK() {
        return tareaMapaMentalActividadEntidadPK;
    }

    public void setTareaMapaMentalActividadEntidadPK(TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK) {
        this.tareaMapaMentalActividadEntidadPK = tareaMapaMentalActividadEntidadPK;
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
        TareaMapaMentalActividadEntidad that = (TareaMapaMentalActividadEntidad) o;
        return tareaEntidad.equals(that.tareaEntidad) &&
                tareaMapaMentalActividadEntidadPK.equals(that.tareaMapaMentalActividadEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tareaEntidad, tareaMapaMentalActividadEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaMapaMentalActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaEntidad=" + tareaEntidad)
                .add("tareaEscribirActividadEntidadPK=" + tareaMapaMentalActividadEntidadPK)
                .add("textRespuesta='" + textRespuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
