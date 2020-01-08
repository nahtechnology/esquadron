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
    //TODO cambiar nombre de la variable por idTarea de acuerdo al metodo del SesionBean
    private UUID idAlumno;

    public void detalle() {
        tareaAlumnoModeloLista = calificaTareaMapaMentalSesionBean.busca(idAlumno);
        profesorGrupoControlador.detalleAlumno(idAlumno);
    }

    public List<TareaAlumnoModelo> getTareaAlumnoModeloLista() {
        return tareaAlumnoModeloLista;
    }

    public void setTareaAlumnoModeloLista(List<TareaAlumnoModelo> tareaAlumnoModeloLista) {
        this.tareaAlumnoModeloLista = tareaAlumnoModeloLista;
    }

    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
        this.idAlumno = idAlumno;
    }
}
