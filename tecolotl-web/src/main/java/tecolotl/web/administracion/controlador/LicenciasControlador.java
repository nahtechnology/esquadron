package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named
public class LicenciasControlador extends TablaControlador<LicenciaModelo> implements Serializable {

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @Inject
    private EstatusControlador estatusControlador;

    @Inject private Logger logger;

    private LicenciaModelo licenciaModelo;
    private String  claveCentroTrabajo;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        setCollectionDataModel(new CollectionDataModel<>(licenciaSesionBean.busca(claveCentroTrabajo)));
        licenciaModelo = new LicenciaModelo();
    }

    public void inserta() {
        licenciaSesionBean.inserta(claveCentroTrabajo, licenciaModelo);
        actualizaDataModel();
    }

    public void elimina() {
        licenciaModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        licenciaSesionBean.elimina(licenciaModelo);
        actualizaDataModel();
    }

    public void actualiza(AjaxBehaviorEvent ajaxBehaviorEvent) {
        licenciaModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        licenciaSesionBean.actualiza(licenciaModelo);
        estatusControlador.init();
        actualizaDataModel();
    }

    public void limpiaModal() {
        licenciaModelo = new LicenciaModelo();
    }

    @Override
    public void actualizaDataModel() {
        setCollectionDataModel(new CollectionDataModel<>(licenciaSesionBean.busca(claveCentroTrabajo)));
    }

    public LicenciaModelo getLicenciaModelo() {
        return licenciaModelo;
    }

    public void setLicenciaModelo(LicenciaModelo licenciaModelo) {
        this.licenciaModelo = licenciaModelo;
    }
}
