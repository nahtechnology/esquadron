package tecolotl.web.profesor;

import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.GrupoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
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
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;


    private List<GrupoModelo> grupoModeloLista;

    private ProfesorModelo profesorModelo;


    @PostConstruct
    public void init(){
        logger.info("Iniciando el controlador...\n");
        profesorModelo = profesorSesionBean.busca(1);
        logger.info(profesorModelo.toString());
        grupoModeloLista = grupoSesionBean.busca(1);
        logger.info(getGrupoModeloLista().toString());
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }
}
