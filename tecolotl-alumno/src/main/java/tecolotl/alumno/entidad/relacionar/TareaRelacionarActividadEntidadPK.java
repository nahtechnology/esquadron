package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaRelacionarActividadEntidadPK implements Serializable {

    private TareaEntidad tareaEntidad;
    private Integer vuelta;
    private RelacionarActividadEntidad relacionarActividadEntidad;

    public TareaRelacionarActividadEntidadPK() {
    }

    public TareaRelacionarActividadEntidadPK(RelacionarActividadEntidad relacionarActividadEntidad, TareaEntidad tareaEntidad, Integer vuelta) {
        this.tareaEntidad = tareaEntidad;
        this.relacionarActividadEntidad = relacionarActividadEntidad;
        this.vuelta = vuelta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_palabra", referencedColumnName = "id_palabra"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad"),
            @JoinColumn(name = "id_clase", referencedColumnName = "id_clase")
    })
    public RelacionarActividadEntidad getRelacionarActividadEntidad() {
        return relacionarActividadEntidad;
    }

    public void setRelacionarActividadEntidad(RelacionarActividadEntidad relacionarActividadEntidad) {
        this.relacionarActividadEntidad = relacionarActividadEntidad;
    }

    @Basic
    @Column(name = "vuelta", insertable = false)
    public Integer getVuelta() {
        return vuelta;
    }

    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

}
