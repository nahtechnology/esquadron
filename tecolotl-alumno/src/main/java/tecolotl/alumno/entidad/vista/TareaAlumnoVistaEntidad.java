package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Immutable
@Table(name = "tarea_alumno", schema = "alumno")
@NamedQuery(
        name = "TareaAlumnoVistaEntidad.buscaAlumno",
        query = "SELECT tav FROM TareaAlumnoVistaEntidad tav WHERE tav.idAlumno = :idAlumno")
public class TareaAlumnoVistaEntidad {

    private Integer totalTareas;
    private Integer idAlumno;
    private Character grupo;
    private Short grado;
    private Date inicio;
    private Date fin;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @Id
    @Column(name = "total_tareas")
    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    @Column(name = "id_alumno")
    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido_paterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TareaAlumnoVistaEntidad.class.getSimpleName() + "[", "]")
                .add("totalTareas=" + totalTareas)
                .add("idAlumno=" + idAlumno)
                .add("grupo=" + grupo)
                .add("grado=" + grado)
                .add("inicio=" + inicio)
                .add("fin=" + fin)
                .add("nombre='" + nombre + "'")
                .add("apellidoPaterno='" + apellidoPaterno + "'")
                .add("apellidoMaterno='" + apellidoMaterno + "'")
                .toString();
    }
}
