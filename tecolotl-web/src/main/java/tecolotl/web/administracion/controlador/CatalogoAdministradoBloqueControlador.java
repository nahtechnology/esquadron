package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.web.controlador.CatalogoControlador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class CatalogoAdministradoBloqueControlador  extends CatalogoControlador {

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Override
    protected CatalogoSesionBean getCatalogoSesionBean() {
        return motivoBloqueoSesionBean;
    }

}
