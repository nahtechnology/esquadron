package tecolotl.web.profesor;

import tecolotl.profesor.modelo.AlumnoTareasNivelModelo;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named("dashboardProfesorControlador")
public class DashboardProfesorControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ProfesorControlador profesorModelo;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private Map<Integer, List<AlumnoTareasNivelModelo>> alumnoTareaNivelMapa;
    private CicloEscolarModelo cicloEscolarModelo;
    private int hashCode;
    private List<GrupoModelo> grupoModeloLista;

    @PostConstruct
    public void init(){
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(
                profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo(), true, profesorModelo.getProfesorModelo().getId());
        cicloEscolarModelo = cicloEscolarModeloLista.get(0);
        grupoModeloLista = grupoSesionBean.busca(
                cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(),
                profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo(),
                profesorModelo.getProfesorModelo().getId());
        alumnoTareaNivelMapa = grupoAlumnoSesionBean.buscaAlumnoNivel(
                grupoModeloLista.stream().map(GrupoModelo::getId).collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(AlumnoTareasNivelModelo::getIdGrupo));
    }

    public void seleccion() {
        for (CicloEscolarModelo cicloEscolarModelo : cicloEscolarModeloLista) {
            if (cicloEscolarModelo.hashCode() == hashCode) {
                this.cicloEscolarModelo = cicloEscolarModelo;
                break;
            }
        }
        grupoModeloLista = grupoSesionBean.busca(
                cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(),
                profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo(),
                profesorModelo.getProfesorModelo().getId());
        alumnoTareaNivelMapa = grupoAlumnoSesionBean.buscaAlumnoNivel(
                grupoModeloLista.stream().map(GrupoModelo::getId).collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(AlumnoTareasNivelModelo::getIdGrupo));
    }

    public List<Integer> transforma(){
        return alumnoTareaNivelMapa.keySet().stream().collect(Collectors.toList());
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public CicloEscolarModelo getCicloEscolarModelo() {
        return cicloEscolarModelo;
    }

    public void setCicloEscolarModelo(CicloEscolarModelo cicloEscolarModelo) {
        this.cicloEscolarModelo = cicloEscolarModelo;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public Map<Integer, List<AlumnoTareasNivelModelo>> getAlumnoTareaNivelMapa() {
        return alumnoTareaNivelMapa;
    }

    public void setAlumnoTareaNivelMapa(Map<Integer, List<AlumnoTareasNivelModelo>> alumnoTareaNivelMapa) {
        this.alumnoTareaNivelMapa = alumnoTareaNivelMapa;
    }
}
