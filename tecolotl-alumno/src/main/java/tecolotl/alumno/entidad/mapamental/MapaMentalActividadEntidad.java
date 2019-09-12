package tecolotl.alumno.entidad.mapamental;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mapamental_actividad", schema = "alumno")
@NamedQueries(value = {
        @NamedQuery(name = "MapaMentalActividadEntidad.busca", query = "SELECT mmae FROM MapaMentalActividadEntidad mmae"),
        @NamedQuery(
                name = "MapaMentalActividadEntidad.buscaIdActivdad",
                query = "SELECT mma FROM MapaMentalActividadEntidad mma JOIN FETCH mma.mapaMentalActividadPK.mapaMentalEntidad " +
                        "WHERE mma.mapaMentalActividadPK.actividadEntidad.id = :idActividad"
        )
})
public class MapaMentalActividadEntidad {
    private MapaMentalActividadEntidadPK mapaMentalActividadPK;

    public MapaMentalActividadEntidad() {
    }

    public MapaMentalActividadEntidad(MapaMentalActividadEntidadPK mapaMentalActividadPK) {
        this.mapaMentalActividadPK = mapaMentalActividadPK;
    }

    @EmbeddedId
    public MapaMentalActividadEntidadPK getMapaMentalActividadPK() {
        return mapaMentalActividadPK;
    }

    public void setMapaMentalActividadPK(MapaMentalActividadEntidadPK mapaMentalActividadPK) {
        this.mapaMentalActividadPK = mapaMentalActividadPK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalActividadEntidad.class.getSimpleName() + "[", "]")
                .add("mapaMentalActividadPK=" + mapaMentalActividadPK)
                .toString();
    }
}
