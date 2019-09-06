package tecolotl.web.alumno;


import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class ActividadesAlumnoControlador {

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private Logger logger;

    private List<TareaResuetasModelo> tareaResuetasModeloLista;

    @PostConstruct
    public void init(){
        tareaResuetasModeloLista = tareaSesionBean.tareasResuelta(1);
        logger.info(tareaResuetasModeloLista.toString());
    }

    public List<TareaResuetasModelo> getTareaResuetasModeloLista() {
        return tareaResuetasModeloLista;
    }

    public void setTareaResuetasModeloLista(List<TareaResuetasModelo> tareaResuetasModeloLista) {
        this.tareaResuetasModeloLista = tareaResuetasModeloLista;
    }
}
