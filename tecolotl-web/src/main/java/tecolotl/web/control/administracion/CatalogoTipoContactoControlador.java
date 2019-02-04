package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.sesion.TipoContactoSesionBean;
import tecolotl.web.control.PaginadorControlador;
import tecolotl.web.enumeracion.TipoValidacion;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CatalogoTipoContactoControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    private CollectionDataModel<TipoContactoDto> collectionDataModel;
    private HtmlDataTable htmlDataTable;
    private ResourceBundle resourceBundle;
    private UIComponent uiComponent;
    private TipoContactoDto tipoContactoDtoModelo;

    @PostConstruct
    public void init() {
        collectionDataModel = new CollectionDataModel<>(tipoContactoSesionBean.busca());
        tipoContactoDtoModelo = new TipoContactoDto();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "escuelacatalogo");
    }

    public void inserta() {
        if (!valida(TipoValidacion.AGREGA)) {
            tipoContactoSesionBean.inserta(this.tipoContactoDtoModelo.getDescripcion());
            collectionDataModel.setWrappedData(tipoContactoSesionBean.busca());
        }
    }

    public void actualizaModelo(TipoContactoDto tipoContactoDto) {
        this.tipoContactoDtoModelo = new TipoContactoDto(tipoContactoDto.getId(), tipoContactoDto.getDescripcion());
    }

    public void actualiza() {
        if (!valida(TipoValidacion.ACTUALIZA)) {
            tipoContactoSesionBean.actualiza(tipoContactoDtoModelo.getId(), tipoContactoDtoModelo.getDescripcion());
            List<TipoContactoDto> tipoContactoDtoLista = (List<TipoContactoDto>) collectionDataModel.getWrappedData();
            int indice = tipoContactoDtoLista.indexOf(tipoContactoDtoModelo);
            tipoContactoDtoLista.get(indice).setDescripcion(tipoContactoDtoModelo.getDescripcion());
        }
    }

    public void limpiaModelo() {
        tipoContactoDtoModelo = new TipoContactoDto();
    }

    private boolean valida(TipoValidacion tipoValidacion) {
        List<TipoContactoDto> tipoContactoDtoLista = (List<TipoContactoDto>)collectionDataModel.getWrappedData();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        switch (tipoValidacion) {
            case AGREGA:
                for (TipoContactoDto tipoContactoDto : tipoContactoDtoLista) {
                    if (tipoContactoDto.getDescripcion().equalsIgnoreCase(tipoContactoDtoModelo.getDescripcion())) {
                        generarMensaje();
                        return true;
                    }
                }
                break;
            case ACTUALIZA:
                int actual = tipoContactoDtoLista.indexOf(tipoContactoDtoModelo);
                for (int i = 0; i < tipoContactoDtoLista.size(); i++) {
                    if (i == actual) continue;
                    if (tipoContactoDtoLista.get(i).getDescripcion().equalsIgnoreCase(tipoContactoDtoModelo.getDescripcion())) {
                        generarMensaje();
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    private void generarMensaje() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(uiComponent.getClientId(), new FacesMessage("", resourceBundle.getString("modal.contact.exist")));
        facesContext.validationFailed();
        facesContext.renderResponse();
    }

    public CollectionDataModel<TipoContactoDto> getCollectionDataModel() {
        return collectionDataModel;
    }

    public void setCollectionDataModel(CollectionDataModel<TipoContactoDto> collectionDataModel) {
        this.collectionDataModel = collectionDataModel;
    }

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    public TipoContactoDto getTipoContactoDtoModelo() {
        return tipoContactoDtoModelo;
    }

    public void setTipoContactoDtoModelo(TipoContactoDto tipoContactoDtoModelo) {
        this.tipoContactoDtoModelo = tipoContactoDtoModelo;
    }

    public UIComponent getUiComponent() {
        return uiComponent;
    }

    public void setUiComponent(UIComponent uiComponent) {
        this.uiComponent = uiComponent;
    }
}
