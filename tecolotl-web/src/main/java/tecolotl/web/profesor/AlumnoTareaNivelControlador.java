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
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AlumnoTareaNivelControlador {

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
    }

    public Map<Integer, List<AlumnoTareasNivelModelo>> getAlumnoTareaNivelMapa() {
        return alumnoTareaNivelMapa;
    }

    public void setAlumnoTareaNivelMapa(Map<Integer, List<AlumnoTareasNivelModelo>> alumnoTareaNivelMapa) {
        this.alumnoTareaNivelMapa = alumnoTareaNivelMapa;
    }
}
