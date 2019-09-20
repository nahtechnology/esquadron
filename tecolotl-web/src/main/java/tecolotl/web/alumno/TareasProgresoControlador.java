package tecolotl.web.alumno;

import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class TareasProgresoControlador {

    @Inject
    private Logger logger;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<TareaActividadModelo> tareaActividadModeloLista;
    private List<TareaResuetasModelo> tareaResuetasModeloLista;
    private TareaResuetasModelo tareaResuetasModelo;

    @PostConstruct
    public void init(){
        tareaActividadModeloLista = alumnoControlador.getTareaActvidadModeloLista();
    }

    public void agregaTarea(TareaResuetasModelo tareaResuetasModelo){
        this.tareaResuetasModelo = tareaResuetasModelo;
    }
    
    public List<TareaResuetasModelo> getTareaResuetasModeloLista() {
        return tareaResuetasModeloLista;
    }

    public void setTareaResuetasModeloLista(List<TareaResuetasModelo> tareaResuetasModeloLista) {
        this.tareaResuetasModeloLista = tareaResuetasModeloLista;
    }

    public TareaResuetasModelo getTareaResuetasModelo() {
        return tareaResuetasModelo;
    }

    public void setTareaResuetasModelo(TareaResuetasModelo tareaResuetasModelo) {
        this.tareaResuetasModelo = tareaResuetasModelo;
    }
}
