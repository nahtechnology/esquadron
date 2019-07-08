package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class EscuelaPoblacionControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    private String claveCentroTrabajo;
    private int totalAlumnos;
    private int totalProfesores;
    private int totalLicencias;

    public void actualiza() {
        EscuelaPoblacionModelo escuelaPoblacionModelo = profesorSesionBean.total(claveCentroTrabajo);
        totalAlumnos = escuelaPoblacionModelo.getTotalAlumnos();
        totalProfesores = escuelaPoblacionModelo.getTotalProfesores();
        totalLicencias = licenciaSesionBean.cuenta(claveCentroTrabajo);
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

    public int getTotalLicencias() {
        return totalLicencias;
    }

    public void setTotalLicencias(int totalLicencias) {
        this.totalLicencias = totalLicencias;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }
}
