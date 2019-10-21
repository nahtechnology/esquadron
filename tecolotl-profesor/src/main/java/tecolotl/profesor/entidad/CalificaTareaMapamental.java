package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "califica_tarea_mapamental", schema = "profesor")
public class CalificaTareaMapamental {

    private TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad;
    private String comentario;
    private Short puntaje;
    private Date momento;

    @Id
    @OneToOne
    @JoinColumns( value = {
            @JoinColumn(name = "id_codigo", referencedColumnName = "codigo"),
            @JoinColumn(name = "id_actvidad", referencedColumnName = "id_actividad"),
            @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea"),
            @JoinColumn(name = "vuelta", referencedColumnName = "vuelta")
    })
    public TareaMapaMentalActividadEntidad getTareaMapaMentalActividadEntidad() {
        return tareaMapaMentalActividadEntidad;
    }

    public void setTareaMapaMentalActividadEntidad(TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad) {
        this.tareaMapaMentalActividadEntidad = tareaMapaMentalActividadEntidad;
    }

    @NotNull
    @Size(min = 0)
    @Basic
    @Column(name = "comentario")
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @NotNull
    @Min(0)
    @Basic
    @Column(name = "puntaje")
    public Short getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    @Basic
    @Column(name = "momento", insertable = false, updatable = false)
    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

}
