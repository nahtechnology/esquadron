package tecolotl.web.profesor;

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
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private TareasAlumnoSesionBean tareasAlumnoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private Logger logger;

    private List<ActividadModelo> actividadModeloLista;
    private UUID grupo;
    private String actividad;

    public void inicio() {
        actividadModeloLista = tareasAlumnoSesionBean.busca(grupo);
    }

    public String asiganarTarea() {
        for (String string : actividad.split(",")) {
            grupoAlumnoSesionBean.tarea(grupo, string);
        }
        return "dashboard";
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

    public UUID getGrupo() {
        return grupo;
    }

    public void setGrupo(UUID grupo) {
        this.grupo = grupo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
