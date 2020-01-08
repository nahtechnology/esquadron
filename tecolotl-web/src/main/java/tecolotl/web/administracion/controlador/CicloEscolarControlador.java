package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class CicloEscolarControlador {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;

    public void busca(String claveCentroTrabajo, String idProfesor) {
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(claveCentroTrabajo, true, UUID.fromString(idProfesor));
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

}
