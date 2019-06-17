package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.sesion.DireccionSesionBean;
import tecolotl.web.controlador.TablaControlador;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.annotation.PostConstruct;
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

    private UIInput uiInputColonia;
    private ColoniaModelo coloniaModelo;
    private String estado;
    private String municipio;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Inject
    private Logger logger;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private transient ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        coloniaModelo = new ColoniaModelo();
    }

    @Override
    public void actualizaDataModel() {
        DireccionModelo direccionModelo = direccionSesionBean.busca(coloniaModelo.getCodigoPostal());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (direccionModelo == null || direccionModelo.getColoniaModeloLista() == null || direccionModelo.getColoniaModeloLista().isEmpty()) {
            facesContext.addMessage(uiInputColonia.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_WARN, resourceBundle.getString("dashboard.validation.zipcode"), null));
        } else {
            setCollectionDataModel(new CollectionDataModel<>(direccionModelo.getColoniaModeloLista()));
            direccionModelo = direccionSesionBean.busca(direccionModelo.getColoniaModeloLista().get(0).getId());
            estado = direccionModelo.getEstado();
            municipio = direccionModelo.getMunicipio();
            facesContext.addMessage(uiInputColonia.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, resourceBundle.getString("dashboard.success.zipcode"), null));
        }
    }

    public void actualiza() {
        direccionSesionBean.actualiza(coloniaModelo);
        actualizaDataModel();;
    }

    public UIInput getUiInputColonia() {
        return uiInputColonia;
    }

    public void setUiInputColonia(UIInput uiInputColonia) {
        this.uiInputColonia = uiInputColonia;
    }

    public ColoniaModelo getColoniaModelo() {
        return coloniaModelo;
    }

    public void setColoniaModelo(ColoniaModelo coloniaModelo) {
        this.coloniaModelo = coloniaModelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
