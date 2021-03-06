package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.sesion.DireccionSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Named(value = "escuelaDashboardControlador")
@ViewScoped
public class DashboardControlador extends TablaControlador<EscuelaDashboardModelo> implements Serializable {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private transient ResourceBundle resourceBundle;

    private List<MotivoBloqueoModelo> motivoBloqueoModeloLista;
    private EscuelaDetalleModelo escuelaDetalleModelo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private DireccionModelo direccionModelo;
    private MotivoBloqueoModelo motivoBloqueoModelo;
    private String codigoPostal;
    private String busqueda;
    private UIInput uiInputCodigoPostal;

    @PostConstruct
    public void init() {
        setCollectionDataModel(new CollectionDataModel<>(escuelaSesionBean.busca()));
        escuelaDetalleModelo = new EscuelaDetalleModelo();
        escuelaBaseModelo = new EscuelaBaseModelo();
        motivoBloqueoModelo = new MotivoBloqueoModelo();
        direccionModelo = new DireccionModelo();
        motivoBloqueoModeloLista = motivoBloqueoSesionBean.busca("Sin bloqueo");
    }

    @Override
    public void actualizaDataModel() {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            getCollectionDataModel().setWrappedData(escuelaSesionBean.busca());
        } else {
            getCollectionDataModel().setWrappedData(escuelaSesionBean.busca().stream().filter(escuelaDashboardModelo ->
                    escuelaDashboardModelo.getNombre().toLowerCase().contains(busqueda.toLowerCase()) ||
                            escuelaDashboardModelo.getClaveCentroTrabajo().toLowerCase().contains(busqueda.toLowerCase())
            ).collect(Collectors.toList()));
        }
    }

    public void buscaColonias() {
        direccionModelo = direccionSesionBean.busca(codigoPostal);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;
        if (direccionModelo == null || direccionModelo.getColoniaModeloLista() == null || direccionModelo.getColoniaModeloLista().isEmpty()) {
            facesMessage = new FacesMessage(resourceBundle.getString("dashboard.validation.zipcode"));
            facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
            facesContext.addMessage(uiInputCodigoPostal.getClientId(facesContext), facesMessage);
        } else {
            DireccionModelo direccionModelo1 = direccionSesionBean.busca(direccionModelo.getColoniaModeloLista().get(0).getId());
            direccionModelo.setEstado(direccionModelo1.getEstado());
            direccionModelo.setMunicipio(direccionModelo1.getMunicipio());
            facesMessage = new FacesMessage(resourceBundle.getString("dashboard.success.zipcode"));
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            facesContext.addMessage(uiInputCodigoPostal.getClientId(facesContext), facesMessage);
        }
    }

    public void actualizaDetalleModelo() {
        escuelaDetalleModelo = escuelaSesionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo());
        codigoPostal = escuelaDetalleModelo.getColoniaModelo().getCodigoPostal();
        direccionModelo = direccionSesionBean.busca(codigoPostal);
        DireccionModelo direccionModelo1 = direccionSesionBean.busca(direccionModelo.getColoniaModeloLista().get(0).getId());
        direccionModelo.setEstado(direccionModelo1.getEstado());
        direccionModelo.setMunicipio(direccionModelo1.getMunicipio());
    }

    public void limpiaDetalleModelo() {
        escuelaDetalleModelo = new EscuelaDetalleModelo();
    }

    public void inserta() {
        escuelaSesionBean.inserta(escuelaDetalleModelo);
        actualizaDataModel();
        escuelaDetalleModelo = new EscuelaDetalleModelo();
        codigoPostal = null;
    }

    public void bloquea() {
        escuelaSesionBean.bloqueo(escuelaBaseModelo, motivoBloqueoModelo);
        motivoBloqueoModelo = new MotivoBloqueoModelo();
        escuelaBaseModelo = new EscuelaBaseModelo();
        actualizaDataModel();
    }

    public void activa() {
        escuelaSesionBean.activa(escuelaBaseModelo.getClaveCentroTrabajo());
        actualizaDataModel();
    }

    public void elimina() {
        escuelaSesionBean.elimina(escuelaBaseModelo.getClaveCentroTrabajo());
        actualizaDataModel();
    }

    public void actualiza() {
        escuelaSesionBean.actualizar(escuelaDetalleModelo);
        actualizaDataModel();
    }

    public EscuelaDetalleModelo getEscuelaDetalleModelo() {
        return escuelaDetalleModelo;
    }

    public void setEscuelaDetalleModelo(EscuelaDetalleModelo escuelaDetalleModelo) {
        this.escuelaDetalleModelo = escuelaDetalleModelo;
    }

    public DireccionModelo getDireccionModelo() {
        return direccionModelo;
    }

    public void setDireccionModelo(DireccionModelo direccionModelo) {
        this.direccionModelo = direccionModelo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public List<MotivoBloqueoModelo> getMotivoBloqueoModeloLista() {
        return motivoBloqueoModeloLista;
    }

    public void setMotivoBloqueoModeloLista(List<MotivoBloqueoModelo> motivoBloqueoModeloLista) {
        this.motivoBloqueoModeloLista = motivoBloqueoModeloLista;
    }

    public MotivoBloqueoModelo getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoModelo motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public UIInput getUiInputCodigoPostal() {
        return uiInputCodigoPostal;
    }

    public void setUiInputCodigoPostal(UIInput uiInputCodigoPostal) {
        this.uiInputCodigoPostal = uiInputCodigoPostal;
    }
}
