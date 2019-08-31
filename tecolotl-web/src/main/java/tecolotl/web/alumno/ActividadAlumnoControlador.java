package tecolotl.web.alumno;

import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class ActividadAlumnoControlador implements Serializable {

    @Inject
    private ActividadSesionBean actividadSesionBean;

}
