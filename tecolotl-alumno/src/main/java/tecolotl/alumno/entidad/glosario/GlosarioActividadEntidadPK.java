package tecolotl.alumno.entidad.glosario;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class GlosarioActividadEntidadPK implements Serializable {

    private GlosarioEntidad glosarioEntidad;
    private ActividadEntidad actividadEntidad;

    public GlosarioActividadEntidadPK() {
    }

    public GlosarioActividadEntidadPK(GlosarioEntidad glosarioEntidad, ActividadEntidad actividadEntidad) {
        this.glosarioEntidad = glosarioEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @NotNull
    @JoinColumns(value = {
            @JoinColumn(name = "id_glosario", referencedColumnName = "palabra"),
            @JoinColumn(name = "id_clase_glosario", referencedColumnName = "id_clase_glosario")
    })
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_actividad")
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GlosarioActividadEntidadPK that = (GlosarioActividadEntidadPK) o;
        return glosarioEntidad.equals(that.glosarioEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(glosarioEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("glosarioEntidad=" + glosarioEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
