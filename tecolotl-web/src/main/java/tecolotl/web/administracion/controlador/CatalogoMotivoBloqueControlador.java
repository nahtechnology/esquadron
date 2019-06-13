package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.web.controlador.CatalogoControlador;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class CatalogoMotivoBloqueControlador extends CatalogoControlador implements Serializable {

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Override
    protected CatalogoSesionBean getCatalogoSesionBean() {
        return motivoBloqueoSesionBean;
    }

}
