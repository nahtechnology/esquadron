package tecolotl.alumno.entidad.mapamental;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "mapamental", schema = "alumno")
@NamedQueries({
        @NamedQuery(
                name = "MapaMentalEntidad.busca",
                query = "SELECT mme FROM MapaMentalEntidad mme")
})
public class MapaMentalEntidad {

    private MapaMentalEntidadPK mapaMentalEntidadPK;
    private String pregunta;

    public MapaMentalEntidad() {
    }

    public MapaMentalEntidad(MapaMentalEntidadPK mapaMentalEntidadPK) {
        this.mapaMentalEntidadPK = mapaMentalEntidadPK;
    }

    @EmbeddedId
    public MapaMentalEntidadPK getMapaMentalEntidadPK() {
        return mapaMentalEntidadPK;
    }

    public void setMapaMentalEntidadPK(MapaMentalEntidadPK mapaMentalEntidadPK) {
        this.mapaMentalEntidadPK = mapaMentalEntidadPK;
    }

    @Basic
    @NotNull
    @Size(max = 200)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalEntidad.class.getSimpleName() + "[", "]")
                .add("mapaMentalEntidadPK=" + mapaMentalEntidadPK)
                .add("pregunta='" + pregunta + "'")
                .toString();
    }
}
