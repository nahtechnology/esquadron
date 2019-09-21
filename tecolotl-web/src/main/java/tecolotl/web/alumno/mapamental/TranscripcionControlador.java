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

    public  void enviaRespuestas() {
        tareaSesionBean.respuesta(transcripcion, alumnoControlador.getTareaActividadModelo().getId());
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }
}
