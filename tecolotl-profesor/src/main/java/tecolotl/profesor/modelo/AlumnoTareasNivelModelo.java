package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.AlumnoTareasNivelEntidad;

public class AlumnoTareasNivelModelo {

    private Integer idGrupo;
    private Integer idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idTarea;
    private Integer totalTareas;
    private String  nivelLenguaje;

    public AlumnoTareasNivelModelo() {
    }

    public AlumnoTareasNivelModelo(AlumnoTareasNivelEntidad alumnoTareasNivelEntidad) {
        this.idGrupo = alumnoTareasNivelEntidad.getIdGrupo();
        this.idAlumno = alumnoTareasNivelEntidad.getIdAlumno();
        this.nombre = alumnoTareasNivelEntidad.getNombre();
        this.apellidoPaterno = alumnoTareasNivelEntidad.getApellidoPaterno();
        this.apellidoMaterno = alumnoTareasNivelEntidad.getApellidoMaterno();
        this.idTarea = alumnoTareasNivelEntidad.getIdTarea();
        this.totalTareas = alumnoTareasNivelEntidad.getTotalTareas();
        this.nivelLenguaje = alumnoTareasNivelEntidad.getNivelLenguaje();
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
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

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }
}
