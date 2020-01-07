package tecolotl.web.coordinador.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
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
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
@Named("coordinadorDashboardControlador")
public class DashboardControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    @Named(value = "coordinadorDashboardTablaControlador")
    private DashboardTablaControlador dashboardTablaControlador;

    private ProfesorModelo profesorModelo;
    private UIInput uiInputApodo;
    private Map<UUID, ProfesorDashboardModelo> profesorDashboardModeloMapa;
    private HtmlInputHidden htmlInputHidden;

    @PostConstruct
    public void init() {
        profesorModelo = new ProfesorModelo();
        profesorDashboardModeloMapa = dashboardTablaControlador.getProfesorDashboardModeloMapa();
    }

    public void agrega() {
        if (profesorDashboardModeloMapa.values().stream().anyMatch(p -> p.getApodo().equals(profesorModelo.getApodo()))) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), new FacesMessage(FacesMessage.SEVERITY_ERROR, "apodo ya existe", null));
        } else {
            profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo(dashboardTablaControlador.getClaveCentroTrabajo()));
            profesorSesionBean.inserta(profesorModelo);
            dashboardTablaControlador.getProfesorDashboardModeloMapa().put(profesorModelo.getId(), new ProfesorDashboardModelo(profesorModelo));
        }
    }

    public void actualiza() {
        logger.info(profesorModelo.toString());
        if (profesorDashboardModeloMapa.values().stream().filter( p -> p.getId().equals(profesorModelo.getId()))
                .anyMatch(p -> p.getApodo().equals(profesorModelo.getApodo()))) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), new FacesMessage(FacesMessage.SEVERITY_ERROR, "apodo ya existe", null));
        } else {
            profesorSesionBean.actualiza(profesorModelo);
            ProfesorDashboardModelo profesorDashboardModelo = profesorDashboardModeloMapa.get(profesorModelo.getId());
            profesorDashboardModelo.setNombre(profesorModelo.getNombre());
            profesorDashboardModelo.setApellidoPaterno(profesorModelo.getApellidoPaterno());
            profesorDashboardModelo.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        }
    }

    public void elimina() {
        logger.info(profesorModelo.toString());
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public UIInput getUiInputApodo() {
        return uiInputApodo;
    }

    public void setUiInputApodo(UIInput uiInputApodo) {
        this.uiInputApodo = uiInputApodo;
    }

    public HtmlInputHidden getHtmlInputHidden() {
        return htmlInputHidden;
    }

    public void setHtmlInputHidden(HtmlInputHidden htmlInputHidden) {
        this.htmlInputHidden = htmlInputHidden;
    }
}
