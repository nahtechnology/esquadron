package tecolotl.web.alumno;

import tecolotl.alumno.sesion.RelacionarSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RelacionarOracionControlador {

    @Inject
    private RelacionarSesionBean relacionarSesionBean;
}
