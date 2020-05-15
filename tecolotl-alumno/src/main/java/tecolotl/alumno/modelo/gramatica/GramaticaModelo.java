package tecolotl.alumno.modelo.gramatica;

import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.vista.CalificacionTareaGramaticaVistaEntidad;
import tecolotl.alumno.modelo.ActividadModelo;

import java.util.Date;
import java.util.StringJoiner;

public class GramaticaModelo {

    private ActividadModelo actividadModelo;
    private String codigo;
    private String palabra;
    private String respuesta;
    private Short calificacion;
    private Short vuelta;
    private Date horaRespuesta;

    public GramaticaModelo() {
    }

    public GramaticaModelo(GramaticaEntidad gramaticaEntidad){
        this.actividadModelo = new ActividadModelo(gramaticaEntidad.getGramaticaEntidadPK().getActividadEntidad().getId());
        this.codigo = gramaticaEntidad.getGramaticaEntidadPK().getCodigo();
        this.palabra = gramaticaEntidad.getPalabra();
    }

    public GramaticaModelo(TareaGramaticaEntidad tareaGramaticaEntidad) {
        this(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad());
        this.respuesta = tareaGramaticaEntidad.getRespuesta();
        this.horaRespuesta = tareaGramaticaEntidad.getHoraRespuesta();
        this.vuelta = tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getVuelta();
    }

    public GramaticaModelo(CalificacionTareaGramaticaVistaEntidad calificacionTareaGramaticaVistaEntidad) {
        actividadModelo = new ActividadModelo(calificacionTareaGramaticaVistaEntidad.getIdActividad());
        codigo = calificacionTareaGramaticaVistaEntidad.getCodigo();
        palabra = calificacionTareaGramaticaVistaEntidad.getPalabra();
        respuesta = calificacionTareaGramaticaVistaEntidad.getRespuesta();
        vuelta = calificacionTareaGramaticaVistaEntidad.getVuelta();
        calificacion = calificacionTareaGramaticaVistaEntidad.getPuntaje();
    }

    public Short getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Short getVuelta() {
        return vuelta;
    }

    public void setVuelta(Short vuelta) {
        this.vuelta = vuelta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GramaticaModelo.class.getSimpleName() + "[", "]")
                .add("actividadModelo=" + actividadModelo)
                .add("codigo='" + codigo + "'")
                .add("palabra='" + palabra + "'")
                .add("respuesta='" + respuesta + "'")
                .add("calificacion=" + calificacion)
                .add("vuelta=" + vuelta)
                .add("horaRespuesta=" + horaRespuesta)
                .toString();
    }

}
