package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoAlumnoEntidad;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;
import java.util.UUID;

public class GrupoAlumnoModelo {

    @NotNull
    private Integer idAlumno;

    @NotNull
    private UUID idGrupo;

    public GrupoAlumnoModelo() {
    }

    public GrupoAlumnoModelo(GrupoAlumnoEntidad grupoAlumnoEntidad){
        this.idAlumno = grupoAlumnoEntidad.getGrupoAlumnoEntidadPK().getAlumnoEntidad().getId();
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
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
