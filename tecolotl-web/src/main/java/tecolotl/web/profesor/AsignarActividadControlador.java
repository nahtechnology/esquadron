package tecolotl.web.profesor;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.alumno.sesion.NivelLenguajeSesionBean;
import tecolotl.profesor.modelo.ActividadModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.TareasAlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class AsignarActividadControlador implements Serializable {

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private TareasAlumnoSesionBean tareasAlumnoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private Logger logger;

    private List<ActividadModelo> actividadModeloLista;
    private String grupo;
    private String alumno;
    private String actividad;
    private String verTarea;

    public void inicio() {
        actividadModeloLista = alumno == null ?
                tareasAlumnoSesionBean.busca(UUID.fromString(grupo)) :
                tareasAlumnoSesionBean.buscaAlumno(UUID.fromString(alumno));
    }

    public String asiganarTarea() {
        if (alumno == null) {
            String tarea[] = actividad.split(",");
            String ver[] = verTarea.split(",");
            for (int indice = 0; indice < tarea.length; indice++) {
                grupoAlumnoSesionBean.tarea(UUID.fromString(grupo), tarea[indice], Boolean.parseBoolean(ver[indice]));
            }
            return "dashboard";
        } else {
            grupoAlumnoSesionBean.tarea(UUID.fromString(grupo), actividad, UUID.fromString(alumno), 1, (short)actividadModeloLista.get(0).getCodigoNivelLenguaje(), Boolean.parseBoolean(verTarea));
            return "admin-alumnos";
        }
    }

    public void incrementaDescargables() {
        escuelaSesionBean.incrementaDescargables(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo());
    }

    public List<String> buscaNiveles() {
        return actividadModeloLista.stream().map(ActividadModelo::getNivelLenguaje).distinct().collect(Collectors.toList());
    }

    public List<ActividadModelo> getActividadModeloLista() {
        return actividadModeloLista;
    }

    public void setActividadModeloLista(List<ActividadModelo> actividadModeloLista) {
        this.actividadModeloLista = actividadModeloLista;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getVerTarea() {
        return verTarea;
    }

    public void setVerTarea(String verTarea) {
        this.verTarea = verTarea;
    }
}
