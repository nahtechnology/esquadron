package tecolotl.alumno.modelo.relacionar;

import tecolotl.alumno.entidad.relacionar.RelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class RelacionarModelo {

    private String idActividad;
    private UUID idTarea;
    private String palabra;
    private ClaseGlosarioModelo claseGlosarioModelo;
    private String respuesta;
    private Date horaRespuesta;
    private Integer vuelta;

    public RelacionarModelo() {
    }

    public RelacionarModelo(String idActividad) {
        this.idActividad = idActividad;
    }

    public RelacionarModelo(RelacionarActividadEntidad relacionarActividadEntidad) {
        this.idActividad = relacionarActividadEntidad.getRelacionarActividadEntidadPK().getActividadEntidad().getId();
        this.claseGlosarioModelo = new ClaseGlosarioModelo(relacionarActividadEntidad.getRelacionarActividadEntidadPK().getGlosarioEntidad()
                .getGlosarioEntidadPK().getClaseGlosarioEntidad());
        this.palabra = relacionarActividadEntidad.getRelacionarActividadEntidadPK().getGlosarioEntidad().getGlosarioEntidadPK().getPalabra();
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
    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
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

    public ClaseGlosarioModelo getClaseGlosarioModelo() {
        return claseGlosarioModelo;
    }

    public void setClaseGlosarioModelo(ClaseGlosarioModelo claseGlosarioModelo) {
        this.claseGlosarioModelo = claseGlosarioModelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarModelo that = (RelacionarModelo) o;
        return idActividad.equals(that.idActividad) &&
                idTarea.equals(that.idTarea) &&
                palabra.equals(that.palabra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividad, idTarea, palabra);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarModelo.class.getSimpleName() + "[", "]")
                .add("idActividad='" + idActividad + "'")
                .add("idTarea=" + idTarea)
                .add("palabra='" + palabra + "'")
                .add("respuesta='" + respuesta + "'")
                .add("horaRespuesta=" + horaRespuesta)
                .add("vuelta=" + vuelta)
                .toString();
    }
}
