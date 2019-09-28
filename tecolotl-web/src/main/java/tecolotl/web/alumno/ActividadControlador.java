package tecolotl.web.alumno;

import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
@Named("actividadAlumno2Controlador")
public class ActividadControlador {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    private String preguntaDetonadora;

    public void buscaPregunta(String idActividad) {
        preguntaDetonadora = actividadSesionBean.preguntaDetonadora(idActividad);
    }

    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }
}
