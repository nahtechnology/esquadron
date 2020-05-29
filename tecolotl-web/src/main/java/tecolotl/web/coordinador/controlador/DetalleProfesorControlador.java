package tecolotl.web.coordinador.controlador;

import tecolotl.profesor.modelo.CalificacionPendientesModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class DetalleProfesorControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private String idProfesor;
    private List<CalificacionPendientesModelo> calificacionPendientesModeloLista;

    public void inicio() {
        calificacionPendientesModeloLista = profesorSesionBean.buscaTotalCalificado(UUID.fromString(idProfesor));
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public List<CalificacionPendientesModelo> getCalificacionPendientesModeloLista() {
        return calificacionPendientesModeloLista;
    }

    public void setCalificacionPendientesModeloLista(List<CalificacionPendientesModelo> calificacionPendientesModeloLista) {
        this.calificacionPendientesModeloLista = calificacionPendientesModeloLista;
    }

}
