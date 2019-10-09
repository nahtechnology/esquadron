package tecolotl.web.profesor;

import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named("dashboardProfesorControlador")
public class DashboardProfesorControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ProfesorControlador profesorModelo;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;
    private List<GrupoModelo> grupoModeloLista;

    @PostConstruct
    public void init(){
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(
                profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo(), true, profesorModelo.getProfesorModelo().getId());
        cicloEscolarModelo = cicloEscolarModeloLista.get(0);
        grupoModeloLista = grupoSesionBean.busca(
                cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(),
                profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo(),
                profesorModelo.getProfesorModelo().getId());
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public CicloEscolarModelo getCicloEscolarModelo() {
        return cicloEscolarModelo;
    }

    public void setCicloEscolarModelo(CicloEscolarModelo cicloEscolarModelo) {
        this.cicloEscolarModelo = cicloEscolarModelo;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }
}
