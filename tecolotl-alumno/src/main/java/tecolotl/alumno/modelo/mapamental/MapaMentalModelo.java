package tecolotl.alumno.modelo.mapamental;

import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.validacion.escribir.EscribirRespuestaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class MapaMentalModelo extends MapaMentalBaseModelo {

    private String textoRespuesta;
    private Date fechaRespuesta;

    public MapaMentalModelo() {
    }

    public MapaMentalModelo(Integer id) {
        super(id);
    }

    public MapaMentalModelo(MapaMentalEntidad mapaMentalEntidad) {
        super(mapaMentalEntidad);
    }

    public MapaMentalModelo(TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad) {
        this(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalEntidad());
        this.textoRespuesta = tareaMapaMentalActividadEntidad.getTextRespuesta();
        this.fechaRespuesta = tareaMapaMentalActividadEntidad.getHoraRespuesta();
    }

    @NotNull(groups = {EscribirRespuestaValidacion.class})
    @Size(max = 300, groups = {EscribirRespuestaValidacion.class})
    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapaMentalModelo that = (MapaMentalModelo) o;
        return textoRespuesta.equals(that.textoRespuesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textoRespuesta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalModelo.class.getSimpleName() + "[", "]")
                .add("textoRespuesta='" + textoRespuesta + "'")
                .add("fechaRespuesta=" + fechaRespuesta)
                .toString();
    }
}
