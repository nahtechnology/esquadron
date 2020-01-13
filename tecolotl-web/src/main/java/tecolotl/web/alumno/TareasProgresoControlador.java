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
import java.util.UUID;
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

    private String idTarea;
    private List<TareaResuetasModelo> tareaResuetasModeloLista;

    public void buscaTareasRealizadas() {
        tareaResuetasModeloLista = tareaSesionBean.tareasResuelta(UUID.fromString(idTarea));
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public List<TareaResuetasModelo> getTareaResuetasModeloLista() {
        return tareaResuetasModeloLista;
    }

    public void setTareaResuetasModeloLista(List<TareaResuetasModelo> tareaResuetasModeloLista) {
        this.tareaResuetasModeloLista = tareaResuetasModeloLista;
    }
}
