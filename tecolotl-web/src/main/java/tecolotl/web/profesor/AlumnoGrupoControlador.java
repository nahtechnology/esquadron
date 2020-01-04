package tecolotl.web.profesor;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class AlumnoGrupoControlador {

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private Logger logger;

    private List<AlumnoModelo> alumnoModeloLista;

    @PostConstruct
    public void inicio() {
        alumnoModeloLista = grupoAlumnoSesionBean.detalleAlumnos(profesorGrupoControlador.getGrupoModelo().getId());
    }

    public List<AlumnoModelo> getAlumnoModeloLista() {
        return alumnoModeloLista;
    }

    public void setAlumnoModeloLista(List<AlumnoModelo> alumnoModeloLista) {
        this.alumnoModeloLista = alumnoModeloLista;
    }
}
