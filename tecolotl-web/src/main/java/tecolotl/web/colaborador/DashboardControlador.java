package tecolotl.web.colaborador;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.web.alumno.ActividadesModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
@Named("colaboradorDashboardControlador")
public class DashboardControlador  {
    private HtmlDataTable htmlDataTable;
    private CollectionDataModel<ActividadModelo> collectionDataModel;
    private ActividadModelo actividadModelo;

    @Inject
    private ActividadSesionBean actividadSesionBean;
    @Inject
    private Logger logger;
    @PostConstruct
    public void  init(){
        collectionDataModel = new CollectionDataModel<>(actividadSesionBean.busca());
    }

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    public CollectionDataModel<ActividadModelo> getCollectionDataModel() {
        return collectionDataModel;
    }

    public void setCollectionDataModel(CollectionDataModel<ActividadModelo> collectionDataModel) {
        this.collectionDataModel = collectionDataModel;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }
}
