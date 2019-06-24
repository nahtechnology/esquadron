package tecolotl.web.controlador;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.CollectionDataModel;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TablaControlador<T> {

    @Inject
    protected Logger logger;

    private CollectionDataModel<T> collectionDataModel;
    private HtmlDataTable htmlDataTable;
    protected T modelo;

    public abstract void actualizaDataModel();

    public void limpiaModelo() {
        try {
            modelo = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getConstructor().newInstance();
        } catch (ReflectiveOperationException ex) {
            logger.log(Level.SEVERE, "No se puede crear el catalogo por:".concat(ex.getMessage()), ex);
        }
    }

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

    public T getModelo() {
        return modelo;
    }

    public void setModelo(T modelo) {
        this.modelo = modelo;
    }
}
