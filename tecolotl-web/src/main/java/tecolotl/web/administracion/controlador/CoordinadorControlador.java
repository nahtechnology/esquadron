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
public class CoordinadorControlador extends TablaControlador<CoordinadorModelo> implements Serializable {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private PersonaMoitvoBloqueoSesionBean personaMoitvoBloqueoSesionBean;

    @Inject
    private Logger logger;

    private List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista;
    private CoordinadorModelo coordinadorModelo;
    private PersonaMotivoBloqueoModelo personaMotivoBloqueoModelo;
    private String claveCentroTrabajo;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        personaMotivoBloqueoModeloLista = personaMoitvoBloqueoSesionBean.busca("Sin Bloqueo");
        personaMotivoBloqueoModelo = new PersonaMotivoBloqueoModelo();
        actualizaDataModel();
    }

    public void limpiaCoordinador() {
        coordinadorModelo = new CoordinadorModelo();
    }

    public void agrega() {
        coordinadorModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        coordinadorSesionBean.agreaga(coordinadorModelo);
        limpiaCoordinador();
        actualizaDataModel();
    }

    public void elimina() {
        coordinadorSesionBean.elimina(coordinadorModelo);
        limpiaCoordinador();
        actualizaDataModel();
    }

    public void actualiza() {
        coordinadorSesionBean.actualiza(coordinadorModelo);
        limpiaCoordinador();
        actualizaDataModel();
    }

    public void bloqueo() {
        coordinadorModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        coordinadorSesionBean.estatus(coordinadorModelo, personaMotivoBloqueoModelo.getClave());
        limpiaCoordinador();
        actualizaDataModel();
    }

    public void desbloquea() {
        System.out.println(coordinadorModelo);
        coordinadorSesionBean.estatus(coordinadorModelo, (short)1);
        limpiaCoordinador();
        actualizaDataModel();
    }

    @Override
    public void actualizaDataModel() {
        setCollectionDataModel(new CollectionDataModel<>(coordinadorSesionBean.busca(claveCentroTrabajo)));
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
}
