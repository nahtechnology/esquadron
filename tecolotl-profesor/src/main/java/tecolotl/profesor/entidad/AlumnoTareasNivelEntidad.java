package tecolotl.profesor.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class AlumnoTareasNivelEntidad implements Serializable {

    private Integer idGrupo;
    private Integer idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Short totalTareas;
    private String nivelLenguaje;

    @Basic
    @Column(name = "id_grupo")
    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Basic
    @Column(name = "id_alumno")
    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido_paterno")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Basic
    @Column(name = "apellido_materno")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Id
    @Column(name = "total_tareas")
    public Short getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Short totalTareas) {
        this.totalTareas = totalTareas;
    }

    @Id
    @Column(name = "valor")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoTareasNivelEntidad that = (AlumnoTareasNivelEntidad) o;
        return idGrupo.equals(that.idGrupo) &&
                idAlumno.equals(that.idAlumno) &&
                nombre.equals(that.nombre) &&
                apellidoPaterno.equals(that.apellidoPaterno) &&
                apellidoMaterno.equals(that.apellidoMaterno) &&
                totalTareas.equals(that.totalTareas) &&
                nivelLenguaje.equals(that.nivelLenguaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupo, idAlumno, nombre, apellidoPaterno, apellidoMaterno, totalTareas, nivelLenguaje);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoTareasNivelEntidad.class.getSimpleName() + "[", "]")
                .add("idGrupo=" + idGrupo)
                .add("idAlumno=" + idAlumno)
                .add("totalTareas=" + totalTareas)
                .add("nivelLenguaje='" + nivelLenguaje + "'")
                .toString();
    }
}
