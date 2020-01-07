package tecolotl.web.coordinador.controlador;

import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Named(value = "coordinadorDashboardTablaControlador")
@ViewScoped
public class DashboardTablaControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private Map<UUID, ProfesorDashboardModelo> profesorDashboardModeloMapa;
    private HtmlDataTable htmlDataTable;
    private String claveCentroTrabajo;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = "21DBA0003D";
        //profesorDashboardModeloMapa = profesorSesionBean.busca(claveCentroTrabajo);
    }

    public Map<UUID, ProfesorDashboardModelo> getProfesorDashboardModeloMapa() {
        return profesorDashboardModeloMapa;
    }

    public void setProfesorDashboardModeloMapa(Map<UUID, ProfesorDashboardModelo> profesorDashboardModeloMapa) {
        this.profesorDashboardModeloMapa = profesorDashboardModeloMapa;
    }

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }
}
