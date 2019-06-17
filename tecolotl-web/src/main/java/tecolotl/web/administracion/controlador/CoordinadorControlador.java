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

@ViewScoped
@Named
public class CoordinadorControlador extends TablaControlador<CoordinadorModelo> {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    private String claveCentroTrabajo;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        setCollectionDataModel(new CollectionDataModel<>(coordinadorSesionBean.busca(claveCentroTrabajo)));
    }

    @Override
    public void actualizaDataModel() {
        setCollectionDataModel(new CollectionDataModel<>(coordinadorSesionBean.busca(claveCentroTrabajo)));
    }
}
