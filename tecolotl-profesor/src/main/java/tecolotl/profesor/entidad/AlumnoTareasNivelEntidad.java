package tecolotl.profesor.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@SqlResultSetMapping(name = "AlumnoTareasNivelEntidadMapping",
    entities = {
        @EntityResult(
            entityClass = AlumnoTareasNivelEntidad.class,
            fields = {
                @FieldResult(name = "id_grupo", column = "idGrupo"),
                @FieldResult(name = "id_alumn", column = "idAlumno"),
                @FieldResult(name = "nombre", column = "nombre"),
                @FieldResult(name = "apellido_paterno", column = "apellidoPaterno"),
                @FieldResult(name = "apellido_materno", column = "apellidoMaterno"),
                @FieldResult(name = "id_tarea", column = "idTarea"),
                @FieldResult(name = "total_tareas_resueltas", column = "totalTareasResueltas"),
                @FieldResult(name = "nivel_lenguaje", column = "nivelLenguaje"),
            }
        )
    }
)
@Entity
public class AlumnoTareasNivelEntidad implements Serializable {

    private UUID idGrupo;
    private UUID idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private UUID idTarea;
    private Integer totalTareas;
    private String nivelLenguaje;

    public AlumnoTareasNivelEntidad() {
    }

    public AlumnoTareasNivelEntidad(UUID idGrupo, UUID idAlumno, String nombre, String apellidoPaterno, String apellidoMaterno, UUID idTarea, Integer totalTareas, String nivelLenguaje) {
        this.idGrupo = idGrupo;
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idTarea = idTarea;
        this.totalTareas = totalTareas;
        this.nivelLenguaje = nivelLenguaje;
    }

    @Basic
    @Column(name = "id_grupo")
    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Basic
    @Column(name = "id_alumno")
    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
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

    @Basic
    @Column(name = "id_tarea")
    public UUID getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(UUID idTarea) {
        this.idTarea = idTarea;
    }

    @Id
    @Column(name = "total_tareas_resueltas")
    public Integer getTotalTareas() {
        return totalTareas;
    }

    public void setTotalTareas(Integer totalTareas) {
        this.totalTareas = totalTareas;
    }

    @Id
    @Column(name = "nivel_lenguaje")
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
