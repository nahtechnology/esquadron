package tecolotl.web.alumno;


import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.GlosarioSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class TareasAlumnoControlador {

    @Inject
    private Logger logger;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private TareaSesionBean tareaSesionBean;

    private List<TareaModelo> tareaModeloLista;
    private List<TareaResuetasModelo> tareaResuetasModeloLista;


    @PostConstruct
    public void init() {
        tareaModeloLista = alumnoControlador.getTareaModeloLista();
        logger.info(tareaModeloLista.toString());
        tareaResuetasModeloLista = tareaSesionBean.tareasResuelta(tareaModeloLista.get(0).getId());
        for (TareaResuetasModelo tareaResuetasModelo : tareaResuetasModeloLista){
            logger.info(tareaResuetasModelo.getTarea().toString());
        }
        logger.info(tareaResuetasModeloLista.toString());
    }

    public List<TareaModelo> getTareaModeloLista() {
        return tareaModeloLista;
    }

    public void setTareaModeloLista(List<TareaModelo> tareaModeloLista) {
        this.tareaModeloLista = tareaModeloLista;
    }

    public List<TareaResuetasModelo> getTareaResuetasModeloLista() {
        return tareaResuetasModeloLista;
    }

    public void setTareaResuetasModeloLista(List<TareaResuetasModelo> tareaResuetasModeloLista) {
        this.tareaResuetasModeloLista = tareaResuetasModeloLista;
    }
}


