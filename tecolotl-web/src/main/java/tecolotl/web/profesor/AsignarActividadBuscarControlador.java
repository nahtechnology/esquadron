package tecolotl.web.profesor;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class AsignarActividadBuscarControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private GrupoModelo grupoModelo;
    private String grupo;
    private String busqueda;
    private String actividad;
    private boolean verTarea;
    private Map<String, List<ActividadModelo>> actividadModeloMapa;

    public void inicio() {
        grupoModelo = new GrupoModelo(UUID.fromString(grupo));
    }

    public void busca() {
        List<ActividadModelo> actividadModeloLista = actividadSesionBean.buscaLenguaje(busqueda);
        actividadModeloMapa = actividadModeloLista
                .stream().collect(Collectors.groupingBy(actividadModelo -> actividadModelo.getNivelLenguajeModeloLista().get(0).getValor()));
        for (Map.Entry<String, List<ActividadModelo>> entry : actividadModeloMapa.entrySet()) {
            entry.setValue(entry.getValue().stream().distinct().collect(Collectors.toList()));
        }
    }

    public String asignar() {
        grupoAlumnoSesionBean.tareaTotal(grupoModelo.getId(), actividad, verTarea);
        return "asignar";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Map<String, List<ActividadModelo>> getActividadModeloMapa() {
        return actividadModeloMapa;
    }

    public void setActividadModeloMapa(Map<String, List<ActividadModelo>> actividadModeloMapa) {
        this.actividadModeloMapa = actividadModeloMapa;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public boolean isVerTarea() {
        return verTarea;
    }

    public void setVerTarea(boolean verTarea) {
        this.verTarea = verTarea;
    }
}
