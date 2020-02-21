package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CiclosEscolaresControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;
    private String claveCentroTrabajo;

    @PostConstruct
    public void buscaCiclosEscolares() {
        cicloEscolarModelo = new CicloEscolarModelo();
    }

    public void inicia() {
        cicloEscolarModelo.setIdEscuela(claveCentroTrabajo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
    }

    public void inserta() {
        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
    }

    public void actualiza() {
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
         cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
    }

    public void elimina() {
        cicloEscolarSessionBean.elimina(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
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

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }
}
