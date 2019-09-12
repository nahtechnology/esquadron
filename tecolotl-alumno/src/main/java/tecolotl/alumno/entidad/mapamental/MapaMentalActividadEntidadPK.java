package tecolotl.alumno.entidad.mapamental;


import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class MapaMentalActividadEntidadPK implements Serializable {
    private MapaMentalEntidad mapaMentalEntidad;
    private ActividadEntidad actividadEntidad;

    public MapaMentalActividadEntidadPK() {
    }

    public MapaMentalActividadEntidadPK(MapaMentalEntidad mapaMentalEntidad, ActividadEntidad actividadEntidad) {
        this.mapaMentalEntidad = mapaMentalEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @JoinColumns(
            value = {
                    @JoinColumn(name = "codigo", referencedColumnName = "codigo"),
                    @JoinColumn(name = "cardinalidad", referencedColumnName = "cardinalidad")
            }
    )
    @ManyToOne(fetch = FetchType.LAZY)
    public MapaMentalEntidad getMapaMentalEntidad() {
        return mapaMentalEntidad;
    }

    public void setMapaMentalEntidad(MapaMentalEntidad mapaMentalEntidad) {
        this.mapaMentalEntidad = mapaMentalEntidad;
    }

    @JoinColumn(name = "id_actividad")
    @ManyToOne(fetch = FetchType.LAZY)
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
        MapaMentalActividadEntidadPK that = (MapaMentalActividadEntidadPK) o;
        return mapaMentalEntidad.equals(that.mapaMentalEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapaMentalEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("mapaMentalEntidad=" + mapaMentalEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
