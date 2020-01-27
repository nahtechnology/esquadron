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
    private HtmlDataTable htmlDataTable;
    private String claveCentroTrabajo;

    @PostConstruct
    public void buscaCiclosEscolares() {
        cicloEscolarModelo = new CicloEscolarModelo();
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        cicloEscolarModelo.setIdEscuela(claveCentroTrabajo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);

    }

    public void insertaCicloEscolar() {
        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
    }

    public void seleccionaCicloEscolar() {
        cicloEscolarModelo = cicloEscolarModeloLista.get(htmlDataTable.getRowIndex());
    }

    public void actualizaCicloEscolar() {
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
         cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo);
    }

    public void eliminaCicloEscolar() {
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

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }
}
