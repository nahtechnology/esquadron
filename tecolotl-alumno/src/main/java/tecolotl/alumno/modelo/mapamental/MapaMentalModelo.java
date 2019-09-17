package tecolotl.alumno.modelo.mapamental;

import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class MapaMentalModelo{

    private String codigo;
    private Short cardinalidad;
    private String pregunta;

    public MapaMentalModelo() {
    }

    public MapaMentalModelo(MapaMentalEntidad mapaMentalEntidad){
        this.codigo = mapaMentalEntidad.getMapaMentalEntidadPK().getCodigo();
        this.cardinalidad = mapaMentalEntidad.getMapaMentalEntidadPK().getCardinalidad();
        this.pregunta = mapaMentalEntidad.getPregunta();
    }

    public MapaMentalModelo(MapaMentalActividadEntidad mapaMentalActividadEntidad) {
        this(mapaMentalActividadEntidad.getMapaMentalActividadPK().getMapaMentalEntidad());
    }

    @NotNull(groups = {MapaMentalLlavePrimariaValidacion.class})
    @Size(min = 32, max = 32, groups = {MapaMentalLlavePrimariaValidacion.class})
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @NotNull(groups = {MapaMentalLlavePrimariaValidacion.class})
    public Short getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(Short cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalModelo.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("cardinalidad=" + cardinalidad)
                .add("pregunta='" + pregunta + "'")
                .toString();
    }

}
