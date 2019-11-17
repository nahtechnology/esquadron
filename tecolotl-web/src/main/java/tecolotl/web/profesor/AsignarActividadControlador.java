package tecolotl.web.profesor;

import tecolotl.profesor.modelo.ActividadModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.TareasAlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AsignarActividadControlador {

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private TareasAlumnoSesionBean tareasAlumnoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private List<ActividadModelo> actividadModeloLista;

    @NotNull
    private Integer grupo;

    @NotNull
    private String actividad;

    public void inicio() {
        actividadModeloLista = tareasAlumnoSesionBean.busca(grupo);
    }

    public void asiganarTarea() {
        grupoAlumnoSesionBean.tarea(grupo, actividad);
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

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
