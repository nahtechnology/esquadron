package tecolotl.web.profesor;

import tecolotl.profesor.modelo.AlumnoTareasNivelModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AlumnoTareaNivelControlador {

    @Inject
    private Logger logger;

    @Inject
    private DashboardProfesorControlador dashboardProfesorControlador;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private Map<Integer, List<AlumnoTareasNivelModelo>> alumnoTareaNivelMapa;

    @PostConstruct
    public void inicio() {
        alumnoTareaNivelMapa = grupoAlumnoSesionBean.buscaAlumnoNivel(
                dashboardProfesorControlador.getGrupoModeloLista().stream().map(GrupoModelo::getId).collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(AlumnoTareasNivelModelo::getIdGrupo));
        logger.info(alumnoTareaNivelMapa.toString());
        logger.info(dashboardProfesorControlador.getGrupoModeloLista().stream().map(GrupoModelo::getId).collect(Collectors.toList()).toString());
    }

    public List<Integer> transforma(){
        return alumnoTareaNivelMapa.keySet().stream().collect(Collectors.toList());
    }

    public Map<Integer, List<AlumnoTareasNivelModelo>> getAlumnoTareaNivelMapa() {
        return alumnoTareaNivelMapa;
    }

    public void setAlumnoTareaNivelMapa(Map<Integer, List<AlumnoTareasNivelModelo>> alumnoTareaNivelMapa) {
        this.alumnoTareaNivelMapa = alumnoTareaNivelMapa;
    }
}
