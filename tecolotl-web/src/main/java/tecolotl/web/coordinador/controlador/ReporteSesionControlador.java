package tecolotl.web.coordinador.controlador;

import tecolotl.alumno.modelo.AlumnoControlSesionModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.sesion.ControlSesionSesionBean;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.modelo.ProfesorSesionControlModelo;
import tecolotl.profesor.sesion.ProfesorSesionControlSessionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ReporteSesionControlador implements Serializable {

    private String idProfesor;
    private ProfesorModelo profesorModelo;
    private String idAlumno;
    private AlumnoModelo alumnoModelo;
    private TimeZone timeZone;
    private String zona;
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
        profesorModelo = idProfesor == null ? null : new ProfesorModelo(UUID.fromString(idProfesor));
        alumnoModelo = idAlumno == null ? null : new AlumnoModelo(UUID.fromString(idAlumno));
        Calendar calendarFin = Calendar.getInstance();
        fechaFin = calendarFin.getTime();
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.add(Calendar.MONTH, -1);
        timeZone = TimeZone.getTimeZone(zona);
        fechaInicio = calendarInicio.getTime();
        if (profesorModelo == null) {
            listaAlumno = controlSesionSesionBean.busca(alumnoModelo.getId(), fechaInicio, fechaFin, timeZone);
        } else {
            listaProfesor = profesorSesionControlSessionBean.busca(profesorModelo.getId(), fechaInicio, fechaFin);
        }
    }

    public void actualiza(AjaxBehaviorEvent ajaxBehaviorEvent) {
        if (profesorModelo == null) {
            listaAlumno = controlSesionSesionBean.busca(alumnoModelo.getId(), fechaInicio, fechaFin, timeZone);
        } else {
            listaProfesor = profesorSesionControlSessionBean.busca(profesorModelo.getId(), fechaInicio, fechaFin);
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
