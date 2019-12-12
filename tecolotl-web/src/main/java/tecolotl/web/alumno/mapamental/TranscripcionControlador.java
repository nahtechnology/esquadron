package tecolotl.web.alumno.mapamental;

import tecolotl.alumno.sesion.TareaSesionBean;
import tecolotl.web.alumno.AlumnoControlador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
@Named
public class TranscripcionControlador {

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private Logger logger;

    private String transcripcion;
    private String activo;
    private Short calificacion;

    public  void enviaRespuestas() {
        tareaSesionBean.respuesta(transcripcion, alumnoControlador.getTareaActividadModelo().getId(), calificacion);
        alumnoControlador.getTareaActividadModelo().setResolviendoTranscript(false);
        alumnoControlador.getTareaActividadModelo().setRespuesta(transcripcion);
    }

    public void cambiaEstatus() {
        tareaSesionBean.estatus(alumnoControlador.getTareaActividadModelo().getId(), Boolean.parseBoolean(activo));
        alumnoControlador.getTareaActividadModelo().setResolviendoTranscript(Boolean.parseBoolean(activo));
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Short getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }
}
