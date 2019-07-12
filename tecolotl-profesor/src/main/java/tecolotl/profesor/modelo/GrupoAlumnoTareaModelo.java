package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoAlumnoTareaEntidad;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.StringJoiner;

public class GrupoAlumnoTareaModelo {
    @NotNull
    private Integer idGrupo;
    @NotNull
    private Integer idAlumno;
    @NotNull
    private Integer idTarea;
    @NotNull
    private Timestamp asignacion;

    public GrupoAlumnoTareaModelo() {
    }

    public GrupoAlumnoTareaModelo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public GrupoAlumnoTareaModelo (GrupoAlumnoTareaEntidad grupoAlumnoTareaEntidad){
        this.idGrupo = grupoAlumnoTareaEntidad.getGrupoAlumnoTareaEntidadPK().getGrupoEntidad().getId();
        this.idAlumno = grupoAlumnoTareaEntidad.getGrupoAlumnoTareaEntidadPK().getAlumnoEntidad().getId();
        this.idTarea = grupoAlumnoTareaEntidad.getGrupoAlumnoTareaEntidadPK().getTareaEntidad().getId();
        this.asignacion = grupoAlumnoTareaEntidad.getAsignacion();
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

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Timestamp getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Timestamp asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoAlumnoTareaModelo.class.getSimpleName() + "[", "]")
                .add("idGrupo=" + idGrupo)
                .add("idAlumno=" + idAlumno)
                .add("idTarea=" + idTarea)
                .add("asignacion=" + asignacion)
                .toString();
    }
}
