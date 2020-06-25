package tecolotl.web.coordinador.controlador;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.modelo.CalificacionPendientesModelo;
import tecolotl.profesor.modelo.TareasPendientesGrupoModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.profesor.sesion.TareasAlumnoSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class DetalleProfesorControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private TareasAlumnoSesionBean tareasAlumnoSesionBean;

    @Inject
    private Logger logger;

    private String idProfesor;
    private List<CalificacionPendientesModelo> calificacionPendientesModeloLista;
    private List<AlumnoModelo> alumnoModeloLista;
    private TareasPendientesGrupoModelo tareasPendientesGrupoModelo;

    public void inicio() {
        calificacionPendientesModeloLista = profesorSesionBean.buscaTotalCalificado(UUID.fromString(idProfesor));
    }

    public void buscaTareasPendientes(String idGrupo) {
        tareasPendientesGrupoModelo = tareasAlumnoSesionBean.buscaTareasPendientes(UUID.fromString(idGrupo));
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public List<CalificacionPendientesModelo> getCalificacionPendientesModeloLista() {
        return calificacionPendientesModeloLista;
    }

    public void setCalificacionPendientesModeloLista(List<CalificacionPendientesModelo> calificacionPendientesModeloLista) {
        this.calificacionPendientesModeloLista = calificacionPendientesModeloLista;
    }

    public List<AlumnoModelo> getAlumnoModeloLista() {
        return alumnoModeloLista;
    }

    public void setAlumnoModeloLista(List<AlumnoModelo> alumnoModeloLista) {
        this.alumnoModeloLista = alumnoModeloLista;
    }

    public TareasPendientesGrupoModelo getTareasPendientesGrupoModelo() {
        return tareasPendientesGrupoModelo;
    }

    public void setTareasPendientesGrupoModelo(TareasPendientesGrupoModelo tareasPendientesGrupoModelo) {
        this.tareasPendientesGrupoModelo = tareasPendientesGrupoModelo;
    }
}
