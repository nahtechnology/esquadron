package tecolotl.web.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;

public class TablaControlador<T> {

    private CollectionDataModel<T> collectionDataModel;
    private HtmlDataTable htmlDataTable;

    public CollectionDataModel<T> getCollectionDataModel() {
        return collectionDataModel;
    }

    public void setCollectionDataModel(CollectionDataModel<T> collectionDataModel) {
        this.collectionDataModel = collectionDataModel;
    }

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

}
