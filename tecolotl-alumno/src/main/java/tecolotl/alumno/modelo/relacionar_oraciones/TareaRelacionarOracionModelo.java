package tecolotl.alumno.modelo.relacionar_oraciones;

import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class TareaRelacionarOracionModelo {

    private RelacionarOracionModelo relacionarOracionModelo;
    private Integer idTarea;
    private Integer respuesta;
    private Date horaRespuesta;

    public TareaRelacionarOracionModelo() {
    }

    public TareaRelacionarOracionModelo(Integer idRelacionarOracion, Integer idTarea) {
        this.relacionarOracionModelo = new RelacionarOracionModelo(idRelacionarOracion);
        this.idTarea = idTarea;
    }

    public TareaRelacionarOracionModelo(TareaRelacionarOracionesEntidad tareaRelacionarOracionesEntidad) {
        this.relacionarOracionModelo = new RelacionarOracionModelo(tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getRelacionarOracionesEntidad());
        this.idTarea = tareaRelacionarOracionesEntidad.getTareaRelacionarOracionesEntidadPK().getTareaEntidad().getId();
        this.respuesta = tareaRelacionarOracionesEntidad.getRespuesta();
        this.horaRespuesta = tareaRelacionarOracionesEntidad.getHoraRespuesta();
    }

    @NotNull(groups = {RelacionarOracionLlavePrimariaValidacion.class})
    @Valid
    public RelacionarOracionModelo getRelacionarOracionModelo() {
        return relacionarOracionModelo;
    }

    public void setRelacionarOracionModelo(RelacionarOracionModelo relacionarOracionModelo) {
        this.relacionarOracionModelo = relacionarOracionModelo;
    }

    @NotNull
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    @NotNull(groups = {TareaRelacionarOracionRespuestaValidacion.class})
    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public Date getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(Date horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaRelacionarOracionModelo that = (TareaRelacionarOracionModelo) o;
        return Objects.equals(relacionarOracionModelo, that.relacionarOracionModelo) &&
                Objects.equals(idTarea, that.idTarea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relacionarOracionModelo, idTarea);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaRelacionarOracionModelo.class.getSimpleName() + "[", "]")
                .add("relacionarOracionModelo=" + relacionarOracionModelo)
                .add("idTarea=" + idTarea)
                .add("respuesta=" + respuesta)
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }

}
