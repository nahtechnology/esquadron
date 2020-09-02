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
    private ProfesorControlador profesorControlador;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista;
    private CicloEscolarModelo cicloEscolarModelo;
    private int hashCode;
    private List<GrupoModelo> grupoModeloLista;

    @PostConstruct
    public void init(){
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(
                profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo(), true, profesorControlador.getProfesorModelo().getId());
        if (!cicloEscolarModeloLista.isEmpty()) {
            cicloEscolarModelo = cicloEscolarModeloLista.get(0);
            grupoModeloLista = grupoSesionBean.buscaTotalAlumno(
                    cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(),
                    profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo(),
                    profesorControlador.getProfesorModelo().getId());
            if (grupoModeloLista != null && !grupoModeloLista.isEmpty()) {
                alumnoTareasNivelModeloLista = grupoAlumnoSesionBean.buscaAlumnoNivel(
                        grupoModeloLista.stream().map(GrupoModelo::getId).collect(Collectors.toList()));
            }
        }
    }

    public void seleccion() {
        for (CicloEscolarModelo cicloEscolarModelo : cicloEscolarModeloLista) {
            if (cicloEscolarModelo.hashCode() == hashCode) {
                this.cicloEscolarModelo = cicloEscolarModelo;
                break;
            }

        }
        grupoModeloLista = grupoSesionBean.buscaTotalAlumno(
                cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(),
                profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo(),
                profesorControlador.getProfesorModelo().getId());
        if (grupoModeloLista != null && !grupoModeloLista.isEmpty()) {
            alumnoTareasNivelModeloLista = grupoAlumnoSesionBean.buscaAlumnoNivel(
                    grupoModeloLista.stream().map(GrupoModelo::getId).collect(Collectors.toList()));
        }
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

    public List<AlumnoTareasNivelModelo> getAlumnoTareasNivelModeloLista() {
        return alumnoTareasNivelModeloLista;
    }

    public void setAlumnoTareasNivelModeloLista(List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista) {
        this.alumnoTareasNivelModeloLista = alumnoTareasNivelModeloLista;
    }
}
