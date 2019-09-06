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
                name = "TareaMapaMentalActividadEntidad.buscaid_tarea",
                query = "SELECT tmmae FROM TareaMapaMentalActividadEntidad tmmae WHERE tmmae.tareaMapaMentalActividadEntidadPK.tareaEntidad.id = :id_tarea"
        )
})
public class TareaMapaMentalActividadEntidad implements Serializable {
    private TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK;
    private String texto_respuesta;
    private Date hora_respuesta;

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
    public String getTexto_respuesta() {
        return texto_respuesta;
    }

    public void setTexto_respuesta(String texto_respuesta) {
        this.texto_respuesta = texto_respuesta;
    }

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
        return new StringJoiner(", ", TareaMapaMentalActividadEntidad.class.getSimpleName() + "[", "]")
                .add("tareaMapaMentalActividadEntidadPK=" + tareaMapaMentalActividadEntidadPK)
                .add("texto_respuesta='" + texto_respuesta + "'")
                .add("hora_respuesta=" + hora_respuesta)
                .toString();
    }
}
