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

    private List<EscuelaBaseModelo> escuelaBaseModeloLista;

    public String busca(String claveCentroTrabajo) {
        return escuelaSesionBean.nombre(claveCentroTrabajo);
    }

    public void busca(){
        escuelaBaseModeloLista = escuelaSesionBean.nombre();
    }

    public List<EscuelaBaseModelo> getEscuelaBaseModeloLista() {
        return escuelaBaseModeloLista;
    }

    public void setEscuelaBaseModeloLista(List<EscuelaBaseModelo> escuelaBaseModeloLista) {
        this.escuelaBaseModeloLista = escuelaBaseModeloLista;
    }
}