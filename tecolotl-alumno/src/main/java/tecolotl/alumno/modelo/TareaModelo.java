package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class TareaModelo {

    private Integer id;
    private Date asignacion;
    private Short reproducciones;
    private String respuesta;
    private Date horaRespuesta;
    private boolean resolviendoTranscript;

    public TareaModelo() {
    }

    public TareaModelo(Integer id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad) {
        this.id = tareaEntidad.getId();
        this.asignacion = tareaEntidad.getAsignacion();
        this.reproducciones = tareaEntidad.getReproducciones();
        this.respuesta = tareaEntidad.getRespuesta();
        this.horaRespuesta = tareaEntidad.getHoraRespuesta();
        this.resolviendoTranscript = tareaEntidad.isResolviendoTranscript();
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }

    @NotNull
    @Min(0)
    public Short getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(Short reproducciones) {
        this.reproducciones = reproducciones;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    public boolean isResolviendoTranscript() {
        return resolviendoTranscript;
    }

    public void setResolviendoTranscript(boolean resolviendoTranscript) {
        this.resolviendoTranscript = resolviendoTranscript;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaModelo that = (TareaModelo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("asignacion=" + asignacion)
                .add("reproducciones=" + reproducciones)
                .toString();
    }
}
