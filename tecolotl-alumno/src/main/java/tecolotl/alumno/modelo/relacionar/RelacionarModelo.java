package tecolotl.alumno.modelo.relacionar;

import tecolotl.alumno.entidad.relacionar.RelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.StringJoiner;

public class RelacionarModelo {

    private String idActividad;
    private Integer idTarea;
    private String palabra;
    private String codigo;
    private String respuesta;
    private Date horaRespuesta;
    private Integer vuelta;

    public RelacionarModelo() {
    }

    public RelacionarModelo(String idActividad, Integer idTarea, String codigo) {
        this.idActividad = idActividad;
        this.idTarea = idTarea;
        this.codigo = codigo;
    }

    public RelacionarModelo(RelacionarActividadEntidad relacionarActividadEntidad) {
        this.idActividad = relacionarActividadEntidad.getRelacionarActividadEntidadPK().getActividadEntidad().getId();
    }

    public RelacionarModelo(TareaRelacionarActividadEntidad tareaRelacionarActividadEntidad) {
        this.respuesta = tareaRelacionarActividadEntidad.getRespuesta();
        this.horaRespuesta = tareaRelacionarActividadEntidad.getHoraRespuesta();
        this.vuelta = tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getVuelta();
    }

    @NotNull(groups = {RelacionarLlavePrimariaValidacion.class})
    @Size(min = 11, max = 11, groups = {RelacionarLlavePrimariaValidacion.class})
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    @NotNull(groups = {RelacionarLlavePrimariaValidacion.class})
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @NotNull(groups = {RelacionarLlavePrimariaValidacion.class})
    @Size(min = 32, max = 32, groups = {RelacionarLlavePrimariaValidacion.class})
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getVuelta() {
        return vuelta;
    }

    public void setVuelta(Integer vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarModelo.class.getSimpleName() + "[", "]")
                .add("idActividad='" + idActividad + "'")
                .add("idTarea=" + idTarea)
                .add("palabra='" + palabra + "'")
                .add("codigo='" + codigo + "'")
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }
}
