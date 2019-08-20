package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;

import javax.persistence.*;
import java.util.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoAlumnoEntidad that = (GrupoAlumnoEntidad) o;
        return grupoAlumnoEntidadPK.equals(that.grupoAlumnoEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoAlumnoEntidadPK);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoAlumnoEntidad.class.getSimpleName() + "[", "]")
                .add("grupoAlumnoEntidadPK=" + grupoAlumnoEntidadPK)
                .toString();
    }

}
