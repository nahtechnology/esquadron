package tecolotl.profesor.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TareaAlumnoGrupoEntidad implements Serializable {

    private String actividad;
    private Integer idAlumno;
    private Integer idTarea;
    private short totalTarea;
    private short totalRespuesta;

    @Id
    @Column(name = "actividad")
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    @Id
    @Column(name = "id_alumno")
    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Id
    @Column(name = "id_tarea")
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    @Column(name = "total_tarea")
    public short getTotalTarea() {
        return totalTarea;
    }

    public void setTotalTarea(short totalTarea) {
        this.totalTarea = totalTarea;
    }

    @Column(name = "total_respuesta")
    public short getTotalRespuesta() {
        return totalRespuesta;
    }

    public void setTotalRespuesta(short totalRespuesta) {
        this.totalRespuesta = totalRespuesta;
    }

}
