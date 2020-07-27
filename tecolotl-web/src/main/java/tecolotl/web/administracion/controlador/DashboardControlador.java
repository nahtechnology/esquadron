package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.web.controlador.TablaControlador;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.annotation.PostConstruct;
import javax.faces.component.UIInput;
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
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private transient ResourceBundle resourceBundle;

    private List<MotivoBloqueoModelo> motivoBloqueoModeloLista;
    private EscuelaBaseModelo escuelaBaseModelo;
    private MotivoBloqueoModelo motivoBloqueoModelo;
    private String busqueda;

    @PostConstruct
    public void init() {
        setCollectionDataModel(new CollectionDataModel<>(escuelaSesionBean.busca()));
        escuelaBaseModelo = new EscuelaBaseModelo();
        motivoBloqueoModelo = new MotivoBloqueoModelo();
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

    public void limpiaDetalleModelo() {
        escuelaBaseModelo = new EscuelaBaseModelo();
    }

    public void inserta() {
        escuelaSesionBean.inserta(escuelaBaseModelo);
        actualizaDataModel();
        limpiaDetalleModelo();
    }

    public void elimina() {
        escuelaSesionBean.elimina(escuelaBaseModelo.getClaveCentroTrabajo());
        actualizaDataModel();
    }

    public void actualiza() {
        escuelaSesionBean.actualizar(escuelaBaseModelo);
        actualizaDataModel();
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

}
