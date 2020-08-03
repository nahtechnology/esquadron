package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.vista.AlumnoTotalesVista;

public class AlumnoTatalesModelo {

    private String clveCentroTrabajo;
    private Integer totalLicencia;
    private Integer totalAlumnosLicencia;
    private Integer totalAlumnos;

    public AlumnoTatalesModelo() {
    }

    public AlumnoTatalesModelo(AlumnoTotalesVista alumnoTotalesVista) {
        this.clveCentroTrabajo = alumnoTotalesVista.getClveCentroTrabajo();
        this.totalLicencia = alumnoTotalesVista.getTotalLicencia();
        totalAlumnosLicencia = alumnoTotalesVista.getTotalAlumnosLicencia();
        totalAlumnos = alumnoTotalesVista.getTotalAlumnos();
    }

    public String getClveCentroTrabajo() {
        return clveCentroTrabajo;
    }

    public void setClveCentroTrabajo(String clveCentroTrabajo) {
        this.clveCentroTrabajo = clveCentroTrabajo;
    }

    public Integer getTotalLicencia() {
        return totalLicencia;
    }

    public void setTotalLicencia(Integer totalLicencia) {
        this.totalLicencia = totalLicencia;
    }

    public Integer getTotalAlumnosLicencia() {
        return totalAlumnosLicencia;
    }

    public void setTotalAlumnosLicencia(Integer totalAlumnosLicencia) {
        this.totalAlumnosLicencia = totalAlumnosLicencia;
    }

    public Integer getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(Integer totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }
}
