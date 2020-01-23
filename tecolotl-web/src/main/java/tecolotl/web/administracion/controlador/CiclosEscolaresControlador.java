package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class CiclosEscolaresControlador {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;

    @PostConstruct
    public void buscaCiclosEscolares(){
        cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J");
        logger.info("Ciclos Escolares\n".concat(cicloEscolarModeloLista.toString()));
    }
    public void insertaCicloEscolar(){
        logger.info("Datos del ciclo escolar: ".concat(cicloEscolarModelo.toString()));
        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
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
}
