package tecolotl.web.coordinador.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.modelo.ProfesorGrupoAlumnoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
@Named("coordinadorDashboardControlador")
public class DashboardControlador implements Serializable {

    @Inject
    private CoordinadorControlador coordinadorControlador;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private List<ProfesorGrupoAlumnoModelo> profesorGrupoAlumnoModeloLista;

    @PostConstruct
    public void inicio() {
        profesorGrupoAlumnoModeloLista =
                profesorSesionBean.buscaTotalGrupoAlumno(coordinadorControlador.getEscuelaBaseModelo().getClaveCentroTrabajo());
    }

    public List<ProfesorGrupoAlumnoModelo> getProfesorGrupoAlumnoModeloLista() {
        return profesorGrupoAlumnoModeloLista;
    }

    public void setProfesorGrupoAlumnoModeloLista(List<ProfesorGrupoAlumnoModelo> profesorGrupoAlumnoModeloLista) {
        this.profesorGrupoAlumnoModeloLista = profesorGrupoAlumnoModeloLista;
    }
}
