package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.TipoContactoSesionBean;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.web.controlador.CatalogoControlador;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named(value = "tipoContactoControlador")
public class CatalogoTipoContactoControlador extends CatalogoControlador implements Serializable {

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    @Override
    protected CatalogoSesionBean getCatalogoSesionBean() {
        return tipoContactoSesionBean;
    }
}
