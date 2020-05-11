package tecolotl.web.profesor;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

    @Inject
    private Logger logger;

    private List<ActividadModelo> actividadModeloLista;
    private String grupo;
    private String alumno;
    private String actividad;

    public void inicio() {
        actividadModeloLista = alumno == null ? tareasAlumnoSesionBean.busca(UUID.fromString(grupo)) : tareasAlumnoSesionBean.buscaAlumno(UUID.fromString(alumno), alumnoSesionBean.busca(UUID.fromString(alumno)).getNivelLenguajeModelo().getClave());
        if (alumno != null) {
            String valor = alumnoSesionBean.busca(UUID.fromString(alumno)).getNivelLenguajeModelo().getValor();
            actividadModeloLista.forEach(actividadModelo -> {
                actividadModelo.setNivelLenguaje(valor);
            });
        }
    }

    public String asiganarTarea() {
        if (alumno == null) {
            for (String string : actividad.split(",")) {
                grupoAlumnoSesionBean.tarea(UUID.fromString(grupo), string);
            }
            return "dashboard";
        } else {
            Short clave = nivelLenguajeSesionBean.busca(actividadModeloLista.get(0).getNivelLenguaje());
            grupoAlumnoSesionBean.tarea(UUID.fromString(grupo), actividad, UUID.fromString(alumno), 1, clave);
            return "admin-alumnos";
        }
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
}
