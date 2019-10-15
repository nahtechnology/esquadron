package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.TareaAlumnoGrupoEntidad;

public class TareaAlumnoGrupoModelo {

    private String actividad;
    private Integer idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idTarea;
    private short totalTarea;
    private short totalRespuesta;

    public TareaAlumnoGrupoModelo() {
    }

    public TareaAlumnoGrupoModelo(TareaAlumnoGrupoEntidad tareaAlumnoGrupoEntidad) {
        this.actividad = tareaAlumnoGrupoEntidad.getActividad();
        this.idAlumno = tareaAlumnoGrupoEntidad.getIdAlumno();
        this.idTarea = tareaAlumnoGrupoEntidad.getIdTarea();
        this.totalTarea = tareaAlumnoGrupoEntidad.getTotalTarea();
        this.totalRespuesta = tareaAlumnoGrupoEntidad.getTotalRespuesta();
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public short getTotalTarea() {
        return totalTarea;
    }

    public void setTotalTarea(short totalTarea) {
        this.totalTarea = totalTarea;
    }

    public short getTotalRespuesta() {
        return totalRespuesta;
    }

    public void setTotalRespuesta(short totalRespuesta) {
        this.totalRespuesta = totalRespuesta;
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
}
