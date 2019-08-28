package tecolotl.alumno.modelo.mapamental;

import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirNuevoValidacion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class MapaMentalBaseModelo {

    private Integer id;
    private String pregunta;

    public MapaMentalBaseModelo() {
    }

    public MapaMentalBaseModelo(Integer id) {
        this.id = id;
    }

    public MapaMentalBaseModelo(MapaMentalEntidad mapaMentalEntidad) {
        this.id = mapaMentalEntidad.getId();
        this.pregunta = mapaMentalEntidad.getPregunta();
    }

    @NotNull(groups = {EscribirNuevoValidacion.class, EscribirLlavePrimariaValidacion.class})
    @Min(value = 1, groups = {EscribirNuevoValidacion.class, EscribirLlavePrimariaValidacion.class})
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 100, min = 2)
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapaMentalBaseModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pregunta='" + pregunta + "'")
                .toString();
    }
}
