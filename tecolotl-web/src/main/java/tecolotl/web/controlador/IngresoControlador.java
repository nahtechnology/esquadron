package tecolotl.web.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class IngresoControlador {

    private List<EscuelaBaseModelo> escuelaBaseModeloLista;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @PostConstruct
    public void init() {
        escuelaBaseModeloLista = escuelaSesionBean.nombre();
    }

    public List<EscuelaBaseModelo> getEscuelaBaseModeloLista() {
        return escuelaBaseModeloLista;
    }

    public void setEscuelaBaseModeloLista(List<EscuelaBaseModelo> escuelaBaseModeloLista) {
        this.escuelaBaseModeloLista = escuelaBaseModeloLista;
    }

}
