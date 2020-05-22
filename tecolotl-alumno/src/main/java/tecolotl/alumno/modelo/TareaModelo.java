package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.TareaEntidad;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class TareaModelo {

    private UUID id;
    private Date asignacion;
    private Short reproducciones;
    private String respuesta;
    private Date horaRespuesta;
    private boolean resolviendoTranscript;
    private Short calificacion;
    private boolean verRespuesta;

    public TareaModelo() {
    }

    public TareaModelo(UUID id) {
        this.id = id;
    }

    public TareaModelo(TareaEntidad tareaEntidad) {
        this.id = tareaEntidad.getId();
        this.asignacion = tareaEntidad.getAsignacion();
        this.reproducciones = tareaEntidad.getReproducciones();
        this.respuesta = tareaEntidad.getRespuesta();
        this.horaRespuesta = tareaEntidad.getHoraRespuesta();
        this.resolviendoTranscript = tareaEntidad.isResolviendoTranscript();
        this.calificacion = tareaEntidad.getCalificacion();
        verRespuesta = tareaEntidad.isVerRespuesta();
    }

    @NotNull
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Short getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isVerRespuesta() {
        return verRespuesta;
    }

    public void setVerRespuesta(boolean verRespuesta) {
        this.verRespuesta = verRespuesta;
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
