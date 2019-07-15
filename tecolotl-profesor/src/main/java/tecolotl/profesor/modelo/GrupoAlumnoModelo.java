package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoAlumnoEntidad;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class GrupoAlumnoModelo {

    @NotNull
    private Integer idAlumno;

    @NotNull
    private Integer idGrupo;

    public GrupoAlumnoModelo() {
    }

    public GrupoAlumnoModelo(GrupoAlumnoEntidad grupoAlumnoEntidad){
        this.idAlumno = grupoAlumnoEntidad.getGrupoAlumnoEntidadPK().getAlumnoEntidad().getId();
        this.idGrupo = grupoAlumnoEntidad.getGrupoAlumnoEntidadPK().getGrupoEntidad().getId();
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoAlumnoModelo.class.getSimpleName() + "[", "]")
                .add("idAlumno=" + idAlumno)
                .add("idGrupo=" + idGrupo)
                .toString();
    }
}
