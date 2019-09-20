package tecolotl.web.alumno;

import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class TareasProgresoControlador {

    @Inject
    private Logger logger;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<TareaActividadModelo> tareaActividadModeloLista;
    private List<TareaResuetasModelo> tareaResuetasModeloLista;

    @PostConstruct
    public void init(){
        tareaActividadModeloLista = alumnoControlador.getTareaActvidadModeloLista();
        logger.info(tareaActividadModeloLista.toString());
    }

    public void llenaTareas(Integer idTarea){
        this.setTareaResuetasModeloLista(tareaSesionBean.tareasResuelta(idTarea));
        logger.info(this.getTareaResuetasModeloLista().toString().concat(String.valueOf(this.getTareaResuetasModeloLista().size())));
    }

    public void

    public List<TareaResuetasModelo> getTareaResuetasModeloLista() {
        return tareaResuetasModeloLista;
    }

    public void setTareaResuetasModeloLista(List<TareaResuetasModelo> tareaResuetasModeloLista) {
        this.tareaResuetasModeloLista = tareaResuetasModeloLista;
    }

    public List<TareaActividadModelo> getTareaActividadModeloLista() {
        return tareaActividadModeloLista;
    }

    public void setTareaActividadModeloLista(List<TareaActividadModelo> tareaActividadModeloLista) {
        this.tareaActividadModeloLista = tareaActividadModeloLista;
    }
}
