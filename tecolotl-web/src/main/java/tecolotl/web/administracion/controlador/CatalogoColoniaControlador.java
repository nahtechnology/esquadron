package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.sesion.DireccionSesionBean;
import tecolotl.web.controlador.TablaControlador;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CatalogoColoniaControlador extends TablaControlador<ColoniaModelo> implements Serializable {

    private String codigoPostal;
    private UIInput uiInputColonia;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Inject
    private Logger logger;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private transient ResourceBundle resourceBundle;

    public void busca() {
        DireccionModelo direccionModelo = direccionSesionBean.busca(codigoPostal);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (direccionModelo == null || direccionModelo.getColoniaModeloLista() == null || direccionModelo.getColoniaModeloLista().isEmpty()) {
            facesContext.addMessage(uiInputColonia.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_WARN, resourceBundle.getString("dashboard.validation.zipcode"), null));
        } else {
            setCollectionDataModel(new CollectionDataModel<>(direccionModelo.getColoniaModeloLista()));
            facesContext.addMessage(uiInputColonia.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, resourceBundle.getString("dashboard.success.zipcode"), null));
        }
    }

    @Override
    public void actualizaDataModel() {

    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public UIInput getUiInputColonia() {
        return uiInputColonia;
    }

    public void setUiInputColonia(UIInput uiInputColonia) {
        this.uiInputColonia = uiInputColonia;
    }
}
