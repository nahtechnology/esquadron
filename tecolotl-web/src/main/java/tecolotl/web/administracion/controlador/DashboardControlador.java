package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class DashboardControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private EscuelaDashboardModelo escuelaDashboardModelo;
    private CollectionDataModel<EscuelaDashboardModelo> collectionDataModel;
    private HtmlDataTable htmlDataTable;

    @PostConstruct
    public void init() {
        collectionDataModel = new CollectionDataModel<>(escuelaSesionBean.busca());
    }

}
