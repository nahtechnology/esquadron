package tecolotl.alumno.modelo.relacionar_oraciones;

import tecolotl.alumno.entidad.relacionar_oraciones.RelacionarOracionesEntidad;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

public class RelacionarOracionModelo {

    private Integer id;
    private String idActividad;
    private String pregunta;
    private String respuesta;

    public RelacionarOracionModelo() {
    }

    public RelacionarOracionModelo(Integer id) {
        this.id = id;
    }

    public RelacionarOracionModelo(RelacionarOracionesEntidad relacionarOracionesEntidad) {
        this.id = relacionarOracionesEntidad.getId();
        this.idActividad = relacionarOracionesEntidad.getActividadEntidad().getId();
        this.pregunta = relacionarOracionesEntidad.getPregunta();
        this.respuesta = relacionarOracionesEntidad.getRespuesta();
    }

    @NotNull(groups = {RelacionarOracionLlavePrimariaValidacion.class})
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarOracionModelo that = (RelacionarOracionModelo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarOracionModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("idActividad='" + idActividad + "'")
                .add("pregunta='" + pregunta + "'")
                .add("respuesta='" + respuesta + "'")
                .toString();
    }
}
