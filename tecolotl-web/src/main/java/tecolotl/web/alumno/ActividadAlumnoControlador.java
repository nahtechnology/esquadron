package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ActividadAlumnoControlador {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private String transcripcion;

    @PostConstruct
    public void inicio() {
        transcripcion = actividadSesionBean.transcripcion(alumnoControlador.getTareaActividadModelo().getIdActividad());
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }
}
