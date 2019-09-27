package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaRelacionarActividadEntidadPK implements Serializable {

    private RelacionarActividadEntidad relacionarActividadEntidad;
    private TareaEntidad tareaEntidad;
    private Integer vuelta;

    public TareaRelacionarActividadEntidadPK() {
    }

    public TareaRelacionarActividadEntidadPK(RelacionarActividadEntidad relacionarActividadEntidad, TareaEntidad tareaEntidad) {
        this.relacionarActividadEntidad = relacionarActividadEntidad;
        this.tareaEntidad = tareaEntidad;
    }

    @JoinColumns(value = {
            @JoinColumn(name = "id_relacionar", referencedColumnName = "id_relacionar"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    public RelacionarActividadEntidad getRelacionarActividadEntidad() {
        return relacionarActividadEntidad;
    }

    public void setRelacionarActividadEntidad(RelacionarActividadEntidad relacionarActividadEntidad) {
        this.relacionarActividadEntidad = relacionarActividadEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Basic
    @Column(name = "vuelta", insertable = false)
    public Integer getVuelta() {
        return vuelta;
    }

    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaRelacionarActividadEntidadPK that = (TareaRelacionarActividadEntidadPK) o;
        return relacionarActividadEntidad.equals(that.relacionarActividadEntidad) &&
                tareaEntidad.equals(that.tareaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionarActividadEntidad, tareaEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaRelacionarActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("relacionarActividadEntidad=" + relacionarActividadEntidad)
                .add("tareaEntidad=" + tareaEntidad)
                .toString();
    }
}
