package tecolotl.profesor.entidad;

import tecolotl.alumno.entidad.AlumnoEntidad;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(name = "grupo_alumno", schema = "profesor")
@NamedQueries({
    @NamedQuery(name = "GrupoAlumnoEntidad.busca", query = "SELECT ga FROM GrupoAlumnoEntidad ga"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.buscaAlumnosPorGrupo",
        query = "SELECT new AlumnoEntidad(a.id, a.nombre, a.apellidoPaterno, a.apellidoMaterno) FROM GrupoAlumnoEntidad ga " +
                "LEFT JOIN ga.grupoAlumnoEntidadPK.alumnoEntidad a WHERE ga.grupoAlumnoEntidadPK.grupoEntidad.id = :idGrupo"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.buscaTotalAlumno",
        query = "SELECT COUNT(ga.grupoAlumnoEntidadPK.alumnoEntidad.id) FROM GrupoAlumnoEntidad ga JOIN ga.grupoAlumnoEntidadPK.grupoEntidad g " +
                "JOIN g.cicloEscolarEntidad ce JOIN ga.grupoAlumnoEntidadPK.alumnoEntidad a " +
                "WHERE ce.activo = TRUE AND ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND a.estatus = TRUE " +
                "GROUP BY ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.buscaContrasenia",
        query = "SELECT ga " +
                "FROM GrupoAlumnoEntidad ga JOIN FETCH ga.grupoAlumnoEntidadPK.alumnoEntidad a WHERE ga.grupoAlumnoEntidadPK.grupoEntidad.id = :idGrupo"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.buscaTotalAlumnosPorGrupo",
        query = "SELECT count(ga.grupoAlumnoEntidadPK.alumnoEntidad.id) FROM GrupoAlumnoEntidad ga where ga.grupoAlumnoEntidadPK.grupoEntidad.id = :idGrupo"),
    @NamedQuery(
        name = "GrupoAlumnoEntidad.elimina",
        query = "DELETE FROM GrupoAlumnoEntidad ga WHERE ga.grupoAlumnoEntidadPK.alumnoEntidad.id = :idAlumno AND ga.grupoAlumnoEntidadPK.grupoEntidad.id = :idGrupo")
})
public class GrupoAlumnoEntidad {

    private GrupoAlumnoEntidadPK grupoAlumnoEntidadPK;

    public GrupoAlumnoEntidad() {
    }

    public GrupoAlumnoEntidad(GrupoAlumnoEntidadPK grupoAlumnoEntidadPK) {
        this.grupoAlumnoEntidadPK = grupoAlumnoEntidadPK;
    }

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
