package tecolotl.web.profesor;

import tecolotl.alumno.sesion.TareaSesionBean;
import tecolotl.profesor.modelo.TareaAlumnoModelo;
import tecolotl.profesor.sesion.CalificaTareaMapaMentalSesionBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class TareaAlumnoControlador implements Serializable {

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private Logger logger;

    private List<TareaAlumnoModelo> tareaAlumnoModeloLista;
    private String idAlumno;
    private String idTarea;

    public void detalle() {
        tareaAlumnoModeloLista = calificaTareaMapaMentalSesionBean.busca(UUID.fromString(idAlumno));
        profesorGrupoControlador.detalleAlumno(UUID.fromString(idAlumno));
    }

    public void borrar() {
        tareaAlumnoModeloLista.removeIf(tareaAlumnoModelo -> tareaAlumnoModelo.getId().compareTo(UUID.fromString(idTarea)) == 0);
        tareaSesionBean.elimina(UUID.fromString(idTarea));
    }

    public List<TareaAlumnoModelo> getTareaAlumnoModeloLista() {
        return tareaAlumnoModeloLista;
    }

    public void setTareaAlumnoModeloLista(List<TareaAlumnoModelo> tareaAlumnoModeloLista) {
        this.tareaAlumnoModeloLista = tareaAlumnoModeloLista;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }
}
