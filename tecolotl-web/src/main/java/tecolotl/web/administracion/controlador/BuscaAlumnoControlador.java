package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaAlumnoModelo;
import tecolotl.administracion.sesion.BusquedaEscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class BuscaAlumnoControlador {

    @Inject
    private BusquedaEscuelaSesionBean busquedaEscuelaSesionBean;

    @Inject
    private HeredarAlumnoControlador heredarAlumnoControlador;

    @Inject
    private Logger logger;

    private List<EscuelaAlumnoModelo> escuelaAlumnoModeloLista;
    private String palabraClave;

    public void busca() {
        escuelaAlumnoModeloLista =
                busquedaEscuelaSesionBean.busca(heredarAlumnoControlador.getEscuelaBaseModelo().getClaveCentroTrabajo(), palabraClave);
    }

    public List<EscuelaAlumnoModelo> getEscuelaAlumnoModeloLista() {
        return escuelaAlumnoModeloLista;
    }

    public void setEscuelaAlumnoModeloLista(List<EscuelaAlumnoModelo> escuelaAlumnoModeloLista) {
        this.escuelaAlumnoModeloLista = escuelaAlumnoModeloLista;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }
}
