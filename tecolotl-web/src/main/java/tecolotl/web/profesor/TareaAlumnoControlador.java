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

@RequestScoped
@Named
public class TareaAlumnoControlador {

    @Inject
    private CalificaTareaMapaMentalSesionBean calificaTareaMapaMentalSesionBean;

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    private List<TareaAlumnoModelo> tareaAlumnoModeloLista;
    private AlumnoModelo alumnoModelo;
    private Integer idAlumno;

    public void detalle() {
        tareaAlumnoModeloLista = calificaTareaMapaMentalSesionBean.busca(idAlumno);
        alumnoModelo = alumnoSesionBean.busca(idAlumno);
    }

    public List<TareaAlumnoModelo> getTareaAlumnoModeloLista() {
        return tareaAlumnoModeloLista;
    }

    public void setTareaAlumnoModeloLista(List<TareaAlumnoModelo> tareaAlumnoModeloLista) {
        this.tareaAlumnoModeloLista = tareaAlumnoModeloLista;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
}
