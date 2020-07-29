package tecolotl.web.profesor;

import tecolotl.profesor.sesion.NotificacionSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class TotalBuzonControlador {

    @Inject
    private NotificacionSesionBean notificacionSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    private Long tareasAsigandas;

    @PostConstruct
    public void busca() {
        tareasAsigandas = notificacionSesionBean.cuenta(profesorControlador.getProfesorModelo().getId());
    }

    public Long getTareasAsigandas() {
        return tareasAsigandas;
    }

    public void setTareasAsigandas(Long tareasAsigandas) {
        this.tareasAsigandas = tareasAsigandas;
    }

}
