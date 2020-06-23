package tecolotl.web.coordinador.controlador;

import tecolotl.alumno.modelo.AlumnoControlSesionModelo;
import tecolotl.alumno.sesion.ControlSesionSesionBean;
import tecolotl.profesor.modelo.ProfesorSesionControlModelo;
import tecolotl.profesor.sesion.ProfesorSesionControlSessionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ReporteSesionControlador implements Serializable {

    private String idProfesor;
    private String idAlumno;
    private Date fechaInicio;
    private Date fechaFin;
    private List<AlumnoControlSesionModelo> listaAlumno;
    private List<ProfesorSesionControlModelo> listaProfesor;

    @Inject
    private ControlSesionSesionBean controlSesionSesionBean;

    @Inject
    private ProfesorSesionControlSessionBean profesorSesionControlSessionBean;

    @Inject
    private Logger logger;

    public void inicio() {
        Calendar calendarFin = Calendar.getInstance();
        fechaFin = calendarFin.getTime();
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.add(Calendar.MONTH, -1);
        fechaInicio = calendarInicio.getTime();
        if (idProfesor == null) {
            listaAlumno = controlSesionSesionBean.busca(UUID.fromString(idAlumno), fechaInicio, fechaFin);
        } else {
            listaProfesor = profesorSesionControlSessionBean.busca(UUID.fromString(idProfesor), fechaInicio, fechaFin);
        }
    }

    public void actualiza(AjaxBehaviorEvent ajaxBehaviorEvent) {
        if (idProfesor == null) {
            listaAlumno = controlSesionSesionBean.busca(UUID.fromString(idAlumno), fechaInicio, fechaFin);
        } else {
            listaProfesor = profesorSesionControlSessionBean.busca(UUID.fromString(idProfesor), fechaInicio, fechaFin);
        }
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<AlumnoControlSesionModelo> getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno(List<AlumnoControlSesionModelo> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    public List<ProfesorSesionControlModelo> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<ProfesorSesionControlModelo> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }
}
