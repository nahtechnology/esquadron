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

import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "escuelaDashboardControlador")
@ViewScoped
public class DashboardControlador extends TablaControlador<EscuelaDashboardModelo> implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    private List<MotivoBloqueoModelo> motivoBloqueoModeloLista;
    private EscuelaDetalleModelo escuelaDetalleModelo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private DireccionModelo direccionModelo;
    private String codigoPostal;
    private MotivoBloqueoModelo motivoBloqueoModelo;

    @PostConstruct
    public void init() {
        setCollectionDataModel(new CollectionDataModel<>(escuelaSesionBean.busca()));
        escuelaDetalleModelo = new EscuelaDetalleModelo();
        escuelaBaseModelo = new EscuelaBaseModelo();
        motivoBloqueoModelo = new MotivoBloqueoModelo();
        motivoBloqueoModeloLista = motivoBloqueoSesionBean.busca("Sin bloqueo");
    }

    public void actualizaTabla() {
        getCollectionDataModel().setWrappedData(escuelaSesionBean.busca());
    }

    public void buscaColonias() {
        direccionModelo = direccionSesionBean.busca(codigoPostal);
        DireccionModelo direccionModelo1 = direccionSesionBean.busca(direccionModelo.getColoniaModeloLista().get(0).getId());
        direccionModelo.setEstado(direccionModelo1.getEstado());
        direccionModelo.setMunicipio(direccionModelo1.getMunicipio());
    }

    public void inserta() {
        escuelaSesionBean.inserta(escuelaDetalleModelo);
        actualizaTabla();
        escuelaDetalleModelo = new EscuelaDetalleModelo();
        codigoPostal = null;
    }

    public void bloquea() {
        escuelaSesionBean.bloqueo(escuelaBaseModelo, motivoBloqueoModelo);
        motivoBloqueoModelo = new MotivoBloqueoModelo();
        escuelaBaseModelo = new EscuelaBaseModelo();
        actualizaTabla();
    }

    public void activa() {
        escuelaSesionBean.activa(escuelaBaseModelo.getClaveCentroTrabajo());
        actualizaTabla();
    }

    public void elimina() {
        escuelaSesionBean.elimina(escuelaBaseModelo.getClaveCentroTrabajo());
        actualizaTabla();
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
}
