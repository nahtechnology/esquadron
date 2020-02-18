package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@RequestScoped
@Named
public class EscuelaPoblacionControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private ResourceBundle resourceBundle;

    private String claveCentroTrabajo;
    private String nombreEscuela;
    private int totalAlumnos;
    private int totalProfesores;
    private int totalAlumnosLicencias;

    public void actualiza() {
        EscuelaPoblacionModelo escuelaPoblacionModelo = profesorSesionBean.total(claveCentroTrabajo);
        nombreEscuela = escuelaSesionBean.nombre(claveCentroTrabajo);
        totalAlumnos = escuelaPoblacionModelo.getTotalAlumnos();
        totalProfesores = escuelaPoblacionModelo.getTotalProfesores();
        totalAlumnosLicencias = licenciaSesionBean.totalAlumno(claveCentroTrabajo);
    }

    public void buscaEscuela() {
        nombreEscuela = escuelaSesionBean.nombre(claveCentroTrabajo);
    }

    public int getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    public int getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(int totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public int getTotalAlumnosLicencias() {
        return totalAlumnosLicencias;
    }

    public void setTotalAlumnosLicencias(int totalAlumnosLicencias) {
        this.totalAlumnosLicencias = totalAlumnosLicencias;
    }
}
