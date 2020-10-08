package tecolotl.web.profesor;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ViewScoped
@Named
public class AsignarActividadBuscarControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private ActividadSesionBean actividadSesionBean;

    private UUID grupo;
    private String busqueda;
    private Map<String, List<ActividadModelo>> actividadModeloMapa;

    public void busca() {
        actividadModeloMapa = actividadSesionBean.buscaLenguaje(busqueda).stream().collect(Collectors.groupingBy(actividadModelo -> actividadModelo.getNivelLenguajeModeloLista().get(0).getValor()));
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public UUID getGrupo() {
        return grupo;
    }

    public void setGrupo(UUID grupo) {
        this.grupo = grupo;
    }

    public Map<String, List<ActividadModelo>> getActividadModeloMapa() {
        return actividadModeloMapa;
    }

    public void setActividadModeloMapa(Map<String, List<ActividadModelo>> actividadModeloMapa) {
        this.actividadModeloMapa = actividadModeloMapa;
    }
}
