package tecolotl.web.controlador;

import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;

public abstract class CatalogoControlador extends TablaControlador<CatalogoModelo> {

    private CatalogoSesionBean catalogoSesionBean;
    private CatalogoModelo catalogoModelo;

    @PostConstruct
    public void init() {
        setCollectionDataModel(new CollectionDataModel<>(getCatalogoSesionBean().busca()));
        catalogoSesionBean = getCatalogoSesionBean();
        catalogoModelo = new CatalogoModelo();
    }

    public void actualiza() {
        catalogoSesionBean.actualiza(catalogoModelo);
        actualizaDataModel();
    }

    @Override
    public void actualizaDataModel() {
        getCollectionDataModel().setWrappedData(getCatalogoSesionBean().busca());
    }

    protected abstract CatalogoSesionBean getCatalogoSesionBean();

    public CatalogoModelo getCatalogoModelo() {
        return catalogoModelo;
    }

    public void setCatalogoModelo(CatalogoModelo catalogoModelo) {
        this.catalogoModelo = catalogoModelo;
    }

}
