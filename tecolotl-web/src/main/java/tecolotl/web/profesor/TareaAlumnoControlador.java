package tecolotl.web.profesor;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.profesor.modelo.TareaAlumnoModelo;
import tecolotl.profesor.sesion.CalificaTareaMapaMentalSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class TareaAlumnoControlador {

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    private List<TareaAlumnoModelo> tareaAlumnoModeloLista;
    private String idAlumno;

    public void detalle() {
        tareaAlumnoModeloLista = calificaTareaMapaMentalSesionBean.busca(UUID.fromString(idAlumno));
        profesorGrupoControlador.detalleAlumno(UUID.fromString(idAlumno));
    }

    public List<TareaAlumnoModelo> getTareaAlumnoModeloLista() {
        return tareaAlumnoModeloLista;
    }

    public void setTareaAlumnoModeloLista(List<TareaAlumnoModelo> tareaAlumnoModeloLista) {
        this.tareaAlumnoModeloLista = tareaAlumnoModeloLista;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }
}
