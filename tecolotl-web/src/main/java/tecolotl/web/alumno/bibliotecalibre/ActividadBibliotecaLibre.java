package tecolotl.web.alumno.bibliotecalibre;

import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.web.alumno.AlumnoControlador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ActividadBibliotecaLibre {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private String transcripcion;

    @PostConstruct
    public void busca() {
        transcripcion = actividadSesionBean.transcripcion(alumnoControlador.getActividadModeloBibliotecaLibre().getIdVideo());
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }
}