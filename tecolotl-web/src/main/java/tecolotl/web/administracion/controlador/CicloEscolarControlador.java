package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CicloEscolarControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private String claveCentroTrabajo;
    private String idProfesor;

    @PostConstruct
    public void inicio() {
        cicloEscolarModeloLista = new ArrayList<>();
    }

    public void busca() {
        logger.info(claveCentroTrabajo);
        logger.info(idProfesor);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo, true, UUID.fromString(idProfesor));
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

}
