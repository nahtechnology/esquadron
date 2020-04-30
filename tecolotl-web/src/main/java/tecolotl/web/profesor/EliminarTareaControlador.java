package tecolotl.web.profesor;

import tecolotl.alumno.modelo.vista.TareasGrupoVistaModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class EliminarTareaControlador implements Serializable {

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private Logger logger;

    private List<TareasGrupoVistaModelo> tareasGrupoVistaModeloLista;
    private String idGrupo;

    public void carga() {
        tareasGrupoVistaModeloLista = tareaSesionBean.buscaPorGrupo(UUID.fromString(idGrupo));
    }

    public void elimina(String idVideo) {
        tareaSesionBean.elimina(idVideo, UUID.fromString(idGrupo));
        tareasGrupoVistaModeloLista.removeIf(tareasGrupoVistaModelo -> tareasGrupoVistaModelo.getIdVideo().equals(idVideo));
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<TareasGrupoVistaModelo> getTareasGrupoVistaModeloLista() {
        return tareasGrupoVistaModeloLista;
    }

    public void setTareasGrupoVistaModeloLista(List<TareasGrupoVistaModelo> tareasGrupoVistaModeloLista) {
        this.tareasGrupoVistaModeloLista = tareasGrupoVistaModeloLista;
    }

}
