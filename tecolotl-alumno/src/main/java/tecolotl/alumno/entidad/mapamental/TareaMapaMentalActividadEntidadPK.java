package tecolotl.alumno.entidad.mapamental;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class TareaMapaMentalActividadEntidadPK implements Serializable {

    private MapaMentalActividadEntidad mapaMentalActividadEntidad;
    private TareaEntidad tareaEntidad;

    public TareaMapaMentalActividadEntidadPK() {
    }

    public TareaMapaMentalActividadEntidadPK(MapaMentalActividadEntidad mapaMentalActividadEntidad) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    public TareaEntidad getTareaEntidad() {
        return tareaEntidad;
    }

    public void setTareaEntidad(TareaEntidad tareaEntidad) {
        this.tareaEntidad = tareaEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaMapaMentalActividadEntidadPK that = (TareaMapaMentalActividadEntidadPK) o;
        return mapaMentalActividadEntidad.equals(that.mapaMentalActividadEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapaMentalActividadEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaMapaMentalActividadEntidadPK.class.getSimpleName() + "[", "]")
                .add("escribirActividadEntidad=" + mapaMentalActividadEntidad)
                .toString();
    }
}
