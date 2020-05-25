package tecolotl.web.profesor;

import tecolotl.profesor.modelo.NotificacionModelo;
import tecolotl.profesor.sesion.NotificacionSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class BuzonControlador {

    @Inject
    private NotificacionSesionBean notificacionSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private Logger logger;

    private List<NotificacionModelo> notificacionModeloLista;

    public void inicio() {
        notificacionModeloLista = notificacionSesionBean.busca(profesorControlador.getProfesorModelo().getId());
    }

    public void leido(String idAlumno) {
        logger.info(idAlumno);
    }

    public List<NotificacionModelo> getNotificacionModeloLista() {
        return notificacionModeloLista;
    }

    public void setNotificacionModeloLista(List<NotificacionModelo> notificacionModeloLista) {
        this.notificacionModeloLista = notificacionModeloLista;
    }
}
