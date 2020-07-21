package tecolotl.web.coordinador.controlador;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.modelo.*;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.profesor.sesion.TareasAlumnoSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class DetalleProfesorControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private TareasAlumnoSesionBean tareasAlumnoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private CoordinadorControlador coordinadorControlador;

    @Inject
    private Logger logger;

    private String idProfesor;
    private ProfesorModelo profesorModelo;
    private int cicloEscolarModeloHascode;
    private List<CalificacionPendientesModelo> calificacionPendientesModeloLista;
    private List<PromedioAlumnoModelo> promedioAlumnoModeloLista;
    private List<CicloEscolarModelo> cicloEscolarModeloLists;
    private TareasPendientesGrupoModelo tareasPendientesGrupoModelo;

    public void inicio() {
        profesorModelo = profesorSesionBean.busca(UUID.fromString(idProfesor));
        if (profesorModelo != null) {
            cicloEscolarModeloLists = cicloEscolarSessionBean.busca(
                    coordinadorControlador.getEscuelaBaseModelo().getClaveCentroTrabajo(),
                    true,
                    UUID.fromString(idProfesor));
            if (!cicloEscolarModeloLists.isEmpty()) {
                calificacionPendientesModeloLista = profesorSesionBean.buscaTotalCalificado(UUID.fromString(idProfesor),
                        cicloEscolarModeloLists.get(0).getInicio(),
                        cicloEscolarModeloLists.get(0).getFin());
                logger.info(String.valueOf(cicloEscolarModeloLists.get(0).hashCode()));
            }
        }
    }

    public void buscaTareasPendientes(String idGrupo) {
        tareasPendientesGrupoModelo = tareasAlumnoSesionBean.buscaTareasPendientes(UUID.fromString(idGrupo));
    }

    public void buscaAlumno(String idGrupo) {
        promedioAlumnoModeloLista = grupoAlumnoSesionBean.promedioAlumno(UUID.fromString(idGrupo));
    }

    public void seleccionCicloEscolar() {
        for (CicloEscolarModelo cicloEscolarModelo1 : cicloEscolarModeloLists) {
            if (cicloEscolarModelo1.hashCode() == cicloEscolarModeloHascode) {
                calificacionPendientesModeloLista = profesorSesionBean.buscaTotalCalificado(profesorModelo.getId(),
                        cicloEscolarModelo1.getInicio(),
                        cicloEscolarModelo1.getFin());
                promedioAlumnoModeloLista = null;
                break;
            }
        }
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public List<CalificacionPendientesModelo> getCalificacionPendientesModeloLista() {
        return calificacionPendientesModeloLista;
    }

    public void setCalificacionPendientesModeloLista(List<CalificacionPendientesModelo> calificacionPendientesModeloLista) {
        this.calificacionPendientesModeloLista = calificacionPendientesModeloLista;
    }

    public TareasPendientesGrupoModelo getTareasPendientesGrupoModelo() {
        return tareasPendientesGrupoModelo;
    }

    public void setTareasPendientesGrupoModelo(TareasPendientesGrupoModelo tareasPendientesGrupoModelo) {
        this.tareasPendientesGrupoModelo = tareasPendientesGrupoModelo;
    }

    public List<PromedioAlumnoModelo> getPromedioAlumnoModeloLista() {
        return promedioAlumnoModeloLista;
    }

    public void setPromedioAlumnoModeloLista(List<PromedioAlumnoModelo> promedioAlumnoModeloLista) {
        this.promedioAlumnoModeloLista = promedioAlumnoModeloLista;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLists() {
        return cicloEscolarModeloLists;
    }

    public void setCicloEscolarModeloLists(List<CicloEscolarModelo> cicloEscolarModeloLists) {
        this.cicloEscolarModeloLists = cicloEscolarModeloLists;
    }

    public int getCicloEscolarModeloHascode() {
        return cicloEscolarModeloHascode;
    }

    public void setCicloEscolarModeloHascode(int cicloEscolarModeloHascode) {
        this.cicloEscolarModeloHascode = cicloEscolarModeloHascode;
    }
}
