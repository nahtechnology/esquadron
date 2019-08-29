package tecolotl.alumno.entidad.mapamental;

import tecolotl.alumno.entidad.ActividadEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "mapamental_actividad", schema = "alumno")
@NamedQueries(value = {
    @NamedQuery(
        name = "EscribirActividadEntidad.buscaActivdad",
        query = "SELECT ea FROM MapaMentalActividadEntidad ea JOIN FETCH ea.mapaMentalEntidad e WHERE ea.actividadEntidad.id = :idActividad"
    ),
    @NamedQuery(
        name = "MapaMentalActividadEntidad.buscaPreguntaNoActividad",
        query = "SELECT mma FROM MapaMentalActividadEntidad mma JOIN FETCH mma.mapaMentalEntidad mm WHERE mm.pregunta LIKE :pregunta " +
                "AND mma.actividadEntidad <> :idActividad"
    )
})
public class MapaMentalActividadEntidad implements Serializable {

    private MapaMentalEntidad mapaMentalEntidad;
    private ActividadEntidad actividadEntidad;

    public MapaMentalActividadEntidad() {
    }

    public MapaMentalActividadEntidad(MapaMentalEntidad mapaMentalEntidad, ActividadEntidad actividadEntidad) {
        this.mapaMentalEntidad = mapaMentalEntidad;
        this.actividadEntidad = actividadEntidad;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mapamental", referencedColumnName = "id")
    public MapaMentalEntidad getMapaMentalEntidad() {
        return mapaMentalEntidad;
    }

    public void setMapaMentalEntidad(MapaMentalEntidad mapaMentalEntidad) {
        this.mapaMentalEntidad = mapaMentalEntidad;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad")
    public ActividadEntidad getActividadEntidad() {
        return actividadEntidad;
    }

    public void setActividadEntidad(ActividadEntidad actividadEntidad) {
        this.actividadEntidad = actividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapaMentalActividadEntidad that = (MapaMentalActividadEntidad) o;
        return mapaMentalEntidad.equals(that.mapaMentalEntidad) &&
                actividadEntidad.equals(that.actividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapaMentalEntidad, actividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalActividadEntidad.class.getSimpleName() + "[", "]")
                .add("escribirEntidad=" + mapaMentalEntidad)
                .add("actividadEntidad=" + actividadEntidad)
                .toString();
    }
}
