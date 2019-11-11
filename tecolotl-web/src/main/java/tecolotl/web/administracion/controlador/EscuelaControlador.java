package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class EscuelaControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    public String busca(String claveCentroTrabajo) {
        return escuelaSesionBean.nombre(claveCentroTrabajo);
    }

    public List<EscuelaBaseModelo> busca(){
           return escuelaSesionBean.nombre();
    }

    public EscuelaSesionBean getEscuelaSesionBean() {
        return escuelaSesionBean;
    }

    public void setEscuelaSesionBean(EscuelaSesionBean escuelaSesionBean) {
        this.escuelaSesionBean = escuelaSesionBean;
    }
}