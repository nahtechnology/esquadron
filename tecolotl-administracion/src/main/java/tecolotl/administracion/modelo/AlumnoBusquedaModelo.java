package tecolotl.administracion.modelo;

import tecolotl.administracion.persistencia.vista.AlumnoEscuelaVista;

public class AlumnoBusquedaModelo {

    private String idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Boolean estatus;

    public AlumnoBusquedaModelo() {
    }

    public AlumnoBusquedaModelo(AlumnoEscuelaVista alumnoEscuelaVista) {
        idAlumno = alumnoEscuelaVista.getIdAlumno();
        nombre = alumnoEscuelaVista.getNombre();
        apellidoPaterno = alumnoEscuelaVista.getApellidPaterno();
        apellidoMaterno = alumnoEscuelaVista.getApellidoMaterno();
        estatus = alumnoEscuelaVista.getEstatus();
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
