package tecolotl.web.controlador;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;
import javax.inject.Inject;
import java.util.logging.Logger;

public abstract class TablaControlador<T> {

    @Inject
    protected Logger logger;

    private CollectionDataModel<T> collectionDataModel;
    private HtmlDataTable htmlDataTable;

    public abstract void actualizaDataModel();

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
