package tecolotl.web.herramienta;

import tecolotl.alumno.sesion.ControlSesionSesionBean;
import tecolotl.nucleo.modelo.UsuarioSesionModelo;
import tecolotl.nucleo.sesion.SesionControlSingleton;
import tecolotl.profesor.sesion.ProfesorSesionControlSessionBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.Principal;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebListener
public class ControlSesionListener implements HttpSessionListener {

    @Inject
    private Logger logger;

    @Inject
    private SesionControlSingleton sesionControlSingleton;

    @Inject
    private ControlSesionSesionBean controlSesionSesionBean;

    @Inject
    private ProfesorSesionControlSessionBean profesorSesionControlSessionBean;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        UsuarioSesionModelo usuarioSesionModelo = sesionControlSingleton.cierre(httpSession.getId());
        if (usuarioSesionModelo != null) {
            switch (usuarioSesionModelo.getTipo()) {
                case ALUMNO:
                    controlSesionSesionBean.inserta(usuarioSesionModelo.getId(),
                            UsuarioSesionModelo.Registro.INICIO.getValue().shortValue(),
                            usuarioSesionModelo.getMomento());
                    controlSesionSesionBean.inserta(usuarioSesionModelo.getId(),
                            UsuarioSesionModelo.Registro.CIERRE.getValue().shortValue(),
                            new Date());
                    break;
                case PROFESOR:
                    profesorSesionControlSessionBean.inserta(usuarioSesionModelo.getId(),
                            UsuarioSesionModelo.Registro.INICIO.getValue().shortValue(),
                            usuarioSesionModelo.getMomento());
                    profesorSesionControlSessionBean.inserta(usuarioSesionModelo.getId(),
                            UsuarioSesionModelo.Registro.CIERRE.getValue().shortValue(),
                            new Date());
                    break;
            }
        }
    }

}
