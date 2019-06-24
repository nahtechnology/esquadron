package tecolotl.web.coordinador.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "coordinadorDashboardControlador")
@ViewScoped
public class DashboardControlador extends TablaControlador<ProfesorDashboardModelo> implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private Logger logger;

    private EscuelaBaseModelo escuelaBaseModelo;

    @PostConstruct
    public void init() {
        escuelaBaseModelo = new EscuelaBaseModelo("21DBS0029K");
        actualizaDataModel();
        limpiaModelo();
    }

    public void agrega() {
        modelo.setEscuelaBaseModelo(escuelaBaseModelo);
        profesorSesionBean.inserta(modelo);
        actualizaDataModel();
    }

    public void actualiza() {
        logger.info(modelo.toString());
        profesorSesionBean.actualiza(modelo);
    }

    @Override
    public void actualizaDataModel() {
        setCollectionDataModel(new CollectionDataModel<>(profesorSesionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo())));
    }

}
