package tecolotl.alumno.entidad.mapamental;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaEscribirActividadEntidadPK implements Serializable {

    private MapaMentalActividadEntidad mapaMentalActividadEntidad;

    public TareaEscribirActividadEntidadPK() {
    }

    public TareaEscribirActividadEntidadPK(MapaMentalActividadEntidad mapaMentalActividadEntidad) {
        this.mapaMentalActividadEntidad = mapaMentalActividadEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "id_mapamental", referencedColumnName = "id_mapamental"),
            @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    })
    public MapaMentalActividadEntidad getMapaMentalActividadEntidad() {
        return mapaMentalActividadEntidad;
    }

    public void setMapaMentalActividadEntidad(MapaMentalActividadEntidad mapaMentalActividadEntidad) {
        this.mapaMentalActividadEntidad = mapaMentalActividadEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaEscribirActividadEntidadPK that = (TareaEscribirActividadEntidadPK) o;
        return mapaMentalActividadEntidad.equals(that.mapaMentalActividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapaMentalActividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaEscribirActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirActividadEntidad=" + mapaMentalActividadEntidad)
                .toString();
    }
}
