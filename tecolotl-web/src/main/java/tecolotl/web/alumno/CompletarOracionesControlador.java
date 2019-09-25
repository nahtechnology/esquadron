package tecolotl.web.alumno;

import tecolotl.alumno.modelo.completar.CompletarModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.sesion.CompletarSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class CompletarOracionesControlador {

    @Inject
    private Logger logger;

    @Inject
    private CompletarSesionBean completarSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<TareaCompletarModelo> tareaCompletarModeloLista;

    @PostConstruct
    public void init(){
        logger.info(alumnoControlador.getTareaActividadModelo().getIdActividad());
        logger.info(alumnoControlador.getTareaActividadModelo().getId().toString());
        tareaCompletarModeloLista = completarSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
        logger.info(tareaCompletarModeloLista.toString());
    }

    public List<TareaCompletarModelo> getTareaCompletarModeloLista() {
        return tareaCompletarModeloLista;
    }

    public void setTareaCompletarModeloLista(List<TareaCompletarModelo> tareaCompletarModeloLista) {
        this.tareaCompletarModeloLista = tareaCompletarModeloLista;
    }
}
