package tecolotl.web.profesor;

import tecolotl.profesor.modelo.NotificacionModelo;
import tecolotl.profesor.sesion.NotificacionSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class BuzonControlador implements Serializable {

    @Inject
    private NotificacionSesionBean notificacionSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private Logger logger;

    private List<NotificacionModelo> notificacionModeloLista;

    @PostConstruct
    public void inicio() {
        notificacionModeloLista = notificacionSesionBean.busca(profesorControlador.getProfesorModelo().getId());
    }

    public void leido(String idAlumno) {
        int cambios =
                notificacionSesionBean.leido(profesorControlador.getProfesorModelo().getId(), UUID.fromString(idAlumno));
        if (cambios != 0) {
            Optional<NotificacionModelo> notificacionModeloOptional =
                notificacionModeloLista.stream().filter(not -> not.getIdAlumno().compareTo(UUID.fromString(idAlumno)) == 0).findFirst();
            notificacionModeloOptional.ifPresent(notificacionModelo -> notificacionModelo.setLeido(true));
        }
    }

    public void elimina(String idAlumno) {
        int cambios =
                notificacionSesionBean.elimina(profesorControlador.getProfesorModelo().getId(), UUID.fromString(idAlumno));
        if (cambios != 0) {
            notificacionModeloLista.removeIf(notificacionModelo -> notificacionModelo.getIdAlumno().compareTo(UUID.fromString(idAlumno)) == 0);
        }
    }

    public List<NotificacionModelo> getNotificacionModeloLista() {
        return notificacionModeloLista;
    }

    public void setNotificacionModeloLista(List<NotificacionModelo> notificacionModeloLista) {
        this.notificacionModeloLista = notificacionModeloLista;
    }
}
