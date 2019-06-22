package tecolotl.web.coordinador.controlador;

import tecolotl.profesor.modelo.ProfesorDashboardModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named(value = "coordinadorDashboarControlador")
public class DashboarControloador extends TablaControlador<ProfesorDashboardModelo> implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @PostConstruct
    public void init() {
        actualizaDataModel();
    }

    @Override
    public void actualizaDataModel() {
        setCollectionDataModel(new CollectionDataModel<>(profesorSesionBean.busca("21DBS0029K")));
    }
}
