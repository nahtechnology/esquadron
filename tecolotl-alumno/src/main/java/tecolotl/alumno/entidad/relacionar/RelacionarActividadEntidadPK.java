package tecolotl.alumno.entidad.relacionar;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class RelacionarActividadEntidadPK implements Serializable {

    private ActividadEntidad actividadEntidad;
    private GlosarioEntidad glosarioEntidad;

    public RelacionarActividadEntidadPK() {
    }

    public RelacionarActividadEntidadPK(ActividadEntidad actividadEntidad, GlosarioEntidad glosarioEntidad) {
        this.actividadEntidad = actividadEntidad;
        this.glosarioEntidad = glosarioEntidad;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "id_palabra", referencedColumnName = "palabra"),
            @JoinColumn(name = "id_clase", referencedColumnName = "id_clase_glosario")
    })
    public GlosarioEntidad getGlosarioEntidad() {
        return glosarioEntidad;
    }

    public void setGlosarioEntidad(GlosarioEntidad glosarioEntidad) {
        this.glosarioEntidad = glosarioEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarActividadEntidadPK that = (RelacionarActividadEntidadPK) o;
        return actividadEntidad.equals(that.actividadEntidad) &&
                glosarioEntidad.equals(that.glosarioEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividadEntidad, glosarioEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("actividadEntidad=" + actividadEntidad)
                .add("glosarioEntidad=" + glosarioEntidad)
                .toString();
    }

}
