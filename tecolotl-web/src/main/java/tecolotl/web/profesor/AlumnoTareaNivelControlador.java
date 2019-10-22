package tecolotl.web.profesor;

import tecolotl.profesor.modelo.AlumnoTareasNivelModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AlumnoTareaNivelControlador {

    @Inject
    private DashboardProfesorControlador dashboardProfesorControlador;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista;

    @PostConstruct
    public void inicio() {
        alumnoTareasNivelModeloLista = grupoAlumnoSesionBean.buscaAlumnoNivel(
                dashboardProfesorControlador.getGrupoModeloLista().stream().map(GrupoModelo::getId).collect(Collectors.toList())
        );
    }

    public List<AlumnoTareasNivelModelo> getAlumnoTareasNivelModeloLista() {
        return alumnoTareasNivelModeloLista;
    }

    public void setAlumnoTareasNivelModeloLista(List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista) {
        this.alumnoTareasNivelModeloLista = alumnoTareasNivelModeloLista;
    }
}
