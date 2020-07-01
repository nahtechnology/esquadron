package tecolotl.web.alumno;

import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.TareaAlumnoModelo;
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
    private TareaSesionBean tareaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private String idTarea;
    private List<TareaResuetasModelo> tareaResuetasModeloLista;
    private List<TareaAlumnoModelo> tareaAlumnoModeloLista;

    public void buscaTareasRealizadas() {
        tareaResuetasModeloLista = tareaSesionBean.tareasResuelta(UUID.fromString(idTarea));
        tareaAlumnoModeloLista = tareaSesionBean.buscaCalificaciones(alumnoControlador.getAlumnoModelo().getId());
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

    public List<TareaAlumnoModelo> getTareaAlumnoModeloLista() {
        return tareaAlumnoModeloLista;
    }

    public void setTareaAlumnoModeloLista(List<TareaAlumnoModelo> tareaAlumnoModeloLista) {
        this.tareaAlumnoModeloLista = tareaAlumnoModeloLista;
    }
}
