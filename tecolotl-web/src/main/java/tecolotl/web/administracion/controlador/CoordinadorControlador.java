package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.nucleo.modelo.PersonaMotivoBloqueoModelo;
import tecolotl.nucleo.sesion.PersonaMoitvoBloqueoSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CoordinadorControlador implements Serializable {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private PersonaMoitvoBloqueoSesionBean personaMoitvoBloqueoSesionBean;

    @Inject
    private Logger logger;

    private List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista;
    private List<CoordinadorModelo> coordinadorModeloLista;
    private CoordinadorModelo coordinadorModelo;
    private PersonaMotivoBloqueoModelo personaMotivoBloqueoModelo;
    private String claveCentroTrabajo;

    public void inicio() {
        coordinadorModeloLista = coordinadorSesionBean.busca(claveCentroTrabajo);
    }

    public void agregar() {
        logger.info(coordinadorModelo.toString());
    }

    public void limpiaCoordinador() {
        coordinadorModelo = new CoordinadorModelo();
    }

    public CoordinadorModelo getCoordinadorModelo() {
        return coordinadorModelo;
    }

    public void setCoordinadorModelo(CoordinadorModelo coordinadorModelo) {
        this.coordinadorModelo = coordinadorModelo;
    }

    public List<PersonaMotivoBloqueoModelo> getPersonaMotivoBloqueoModeloLista() {
        return personaMotivoBloqueoModeloLista;
    }

    public void setPersonaMotivoBloqueoModeloLista(List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista) {
        this.personaMotivoBloqueoModeloLista = personaMotivoBloqueoModeloLista;
    }

    public PersonaMotivoBloqueoModelo getPersonaMotivoBloqueoModelo() {
        return personaMotivoBloqueoModelo;
    }

    public void setPersonaMotivoBloqueoModelo(PersonaMotivoBloqueoModelo personaMotivoBloqueoModelo) {
        this.personaMotivoBloqueoModelo = personaMotivoBloqueoModelo;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public List<CoordinadorModelo> getCoordinadorModeloLista() {
        return coordinadorModeloLista;
    }

    public void setCoordinadorModeloLista(List<CoordinadorModelo> coordinadorModeloLista) {
        this.coordinadorModeloLista = coordinadorModeloLista;
    }
}
