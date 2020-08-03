package tecolotl.administracion.persistencia.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(schema = "administracion", name = "alumnos_totales")
@NamedQuery(name = "AlumnoTotalesVista.busca", query = "SELECT at FROM AlumnoTotalesVista at")
public class AlumnoTotalesVista {

    private String clveCentroTrabajo;
    private Integer totalLicencia;
    private Integer totalAlumnosLicencia;
    private Integer totalAlumnos;

    @Id
    @Column(name = "clave_centro_trabajo")
    public String getClveCentroTrabajo() {
        return clveCentroTrabajo;
    }

    public void setClveCentroTrabajo(String clveCentroTrabajo) {
        this.clveCentroTrabajo = clveCentroTrabajo;
    }

    @Column(name = "total_licencias")
    public Integer getTotalLicencia() {
        return totalLicencia;
    }

    public void setTotalLicencia(Integer totalLicencia) {
        this.totalLicencia = totalLicencia;
    }

    @Column(name = "total_alumnos_licencia")
    public Integer getTotalAlumnosLicencia() {
        return totalAlumnosLicencia;
    }

    public void setTotalAlumnosLicencia(Integer totalAlumnosLicencia) {
        this.totalAlumnosLicencia = totalAlumnosLicencia;
    }

    @Column(name = "total_alumno")
    public Integer getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(Integer totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }
}
