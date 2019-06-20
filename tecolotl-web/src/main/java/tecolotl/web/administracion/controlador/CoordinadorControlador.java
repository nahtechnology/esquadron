package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class CoordinadorControlador extends TablaControlador<CoordinadorModelo> implements Serializable {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    private CoordinadorModelo coordinadorModelo;
    private String claveCentroTrabajo;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
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
}
