package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class CiclosEscolaresControlador {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;
    private HtmlDataTable htmlDataTable;

    @PostConstruct
    public void buscaCiclosEscolares() {
        cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J");
    }

    public void insertaCicloEscolar() {
        logger.info("Datos del ciclo escolar: ".concat(cicloEscolarModelo.toString()));
        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J");
    }

    public void seleccionaCicloEscolar() {
        logger.info("Esto tiene el HTML DATA TABLE: ".concat(htmlDataTable.getRowData().toString()));
        cicloEscolarModelo = cicloEscolarModeloLista.get(htmlDataTable.getRowIndex());
        logger.info("Estos son los datos actualizados: ".concat(cicloEscolarModelo.toString()));
    }

    public void actualizaCicloEscolar() {
        logger.info("Datos a actualizar del Ciclo Escolar: ".concat(cicloEscolarModelo.toString()));
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
         cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J");
    }

    public void eliminaCicloEscolar() {
        logger.info(cicloEscolarModelo.toString());
        cicloEscolarSessionBean.elimina(cicloEscolarModelo);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J");
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
