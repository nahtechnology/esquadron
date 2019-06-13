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

    public void inserta() {
        catalogoSesionBean.inserta(catalogoModelo.getValor());
        actualizaDataModel();
    }

    public void limpiaModelo() {
        catalogoModelo = new CatalogoModelo();
    }

    @Override
    public void actualizaDataModel() {
        getCollectionDataModel().setWrappedData(getCatalogoSesionBean().busca());
        catalogoModelo = new CatalogoModelo();
    }

    protected abstract CatalogoSesionBean getCatalogoSesionBean();

    public CatalogoModelo getCatalogoModelo() {
        return catalogoModelo;
    }

    public void setCatalogoModelo(CatalogoModelo catalogoModelo) {
        this.catalogoModelo = catalogoModelo;
    }

}
