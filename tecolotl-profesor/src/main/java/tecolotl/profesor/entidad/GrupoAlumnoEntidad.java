package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "grupo_alumno", schema = "profesor")
@NamedQueries({
    @NamedQuery(name = "GrupoAlumnoEntidad.busca", query = "SELECT ga FROM GrupoAlumnoEntidad ga")
})
public class GrupoAlumnoEntidad {

    private GrupoAlumnoEntidadPK grupoAlumnoEntidadPK;

    @EmbeddedId
    public GrupoAlumnoEntidadPK getGrupoAlumnoEntidadPK() {
        return grupoAlumnoEntidadPK;
    }

    public void setGrupoAlumnoEntidadPK(GrupoAlumnoEntidadPK grupoAlumnoEntidadPK) {
        this.grupoAlumnoEntidadPK = grupoAlumnoEntidadPK;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoAlumnoEntidad.class.getSimpleName() + "[", "]")
                .add("grupoAlumnoEntidadPK=" + grupoAlumnoEntidadPK)
                .toString();
    }
}
