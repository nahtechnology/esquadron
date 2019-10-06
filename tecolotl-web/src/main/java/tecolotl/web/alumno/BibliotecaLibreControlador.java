package tecolotl.web.alumno;

import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class BibliotecaLibreControlador {

    @Inject
    private ActividadSesionBean actividadSesionBean;
}
