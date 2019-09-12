package tecolotl.alumno.entidad.mapamental;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tarea_mapamental_actividad", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "TareaMapaMentalActividadEntidad.busca", query = "SELECT tmmae FROM TareaMapaMentalActividadEntidad tmmae"),
        @NamedQuery(
                name = "TareaMapaMentalActividadEntidad.buscaidTarea",
                query = "SELECT tmmae FROM TareaMapaMentalActividadEntidad tmmae JOIN tmmae.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad mma " +
                        "JOIN mma.mapaMentalActividadPK.mapaMentalEntidad mm WHERE tmmae.tareaMapaMentalActividadEntidadPK.tareaEntidad.id = :idTarea"
        ),
        @NamedQuery(
                name = "TareaMapaMentalActividadEntidad.buscaIdMapaMental",
                query = "SELECT DISTINCT tmma.tareaMapaMentalActividadEntidadPK.mapaMentalActividadEntidad.mapaMentalActividadPK.mapaMentalEntidad.mapaMentalEntidadPK.cardinalidad " +
                        "FROM TareaMapaMentalActividadEntidad tmma WHERE tmma.tareaMapaMentalActividadEntidadPK.tareaEntidad.id = :idTarea"
        )
})
public class TareaMapaMentalActividadEntidad implements Serializable {
    private TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK;
    private String textoRespuesta;
    private Date horaRespuesta;

    public TareaMapaMentalActividadEntidad() {
    }

    public TareaMapaMentalActividadEntidad(TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK) {
        this.tareaMapaMentalActividadEntidadPK = tareaMapaMentalActividadEntidadPK;
    }

    @EmbeddedId
    public TareaMapaMentalActividadEntidadPK getTareaMapaMentalActividadEntidadPK() {
        return tareaMapaMentalActividadEntidadPK;
    }

    public void setTareaMapaMentalActividadEntidadPK(TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK) {
        this.tareaMapaMentalActividadEntidadPK = tareaMapaMentalActividadEntidadPK;
    }

    @Basic
    @NotNull
    @Size(max = 300)
    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "horaRespuesta", insertable = false, updatable = false)
    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaMapaMentalActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaMapaMentalActividadEntidadPK=" + tareaMapaMentalActividadEntidadPK)
                .add("textoRespuesta='" + textoRespuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
