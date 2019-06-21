package tecolotl.web.administracion.controlador;

import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class EscuelaControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    public String busca(String claveCentroTrabajo) {
        return escuelaSesionBean.nombre(claveCentroTrabajo);
    }
}