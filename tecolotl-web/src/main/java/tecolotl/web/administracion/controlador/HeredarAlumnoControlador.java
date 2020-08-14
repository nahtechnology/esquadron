package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class HeredarAlumnoControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    private String grupo;
    private String escuela;
    private EscuelaBaseModelo escuelaBaseModelo;
    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private List<ProfesorModelo> profesorModeloLista;
    private int idCicloEscolar;
    private boolean busca;
    private String idProfeosor;
    private List<GrupoModelo> grupoModeloLista;

    public void inicio() {
        escuelaBaseModelo = new EscuelaBaseModelo(escuela);
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(escuela);
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(escuela);
    }

    public void buscaGrupo() {
        busca = true;
        Optional<CicloEscolarModelo> cicloEscolarModeloOptional = cicloEscolarModeloLista.stream().filter(ce -> ce.hashCode() == idCicloEscolar).findFirst();
        cicloEscolarModeloOptional.ifPresent(ce -> {
            grupoModeloLista = grupoSesionBean.busca(ce.getInicio(), ce.getFin(), escuelaBaseModelo.getClaveCentroTrabajo(), UUID.fromString(idProfeosor));
        });
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }

    public int getIdCicloEscolar() {
        return idCicloEscolar;
    }

    public void setIdCicloEscolar(int idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public String getIdProfeosor() {
        return idProfeosor;
    }

    public void setIdProfeosor(String idProfeosor) {
        this.idProfeosor = idProfeosor;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }

    public boolean isBusca() {
        return busca;
    }

    public void setBusca(boolean busca) {
        this.busca = busca;
    }
}
