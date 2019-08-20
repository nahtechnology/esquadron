package tecolotl.profesor.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "grupo", schema = "profesor")
@NamedQueries({
    @NamedQuery(name ="GrupoEntidad.busca", query = "SELECT g FROM GrupoEntidad g"),
    @NamedQuery(
        name = "GrupoEntidad.buscaProfesor",
        query = "SELECT g FROM GrupoEntidad g WHERE g.grupoEntidadPK.profesorEntidad.id = :idProfesor")
})
public class GrupoEntidad {

    private GrupoEntidadPK grupoEntidadPK;
    private Short grado;
    private Character grupo;

    public GrupoEntidad() {
    }

    @EmbeddedId
    public GrupoEntidadPK getGrupoEntidadPK() {
        return grupoEntidadPK;
    }

    public void setGrupoEntidadPK(GrupoEntidadPK grupoEntidadPK) {
        this.grupoEntidadPK = grupoEntidadPK;
    }

    @Basic
    @NotNull
    @Column(name = "grado")
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @NotNull
    @Basic
    @Column(name = "grupo")
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntidad that = (GrupoEntidad) o;
        return grupoEntidadPK.equals(that.grupoEntidadPK) &&
                grado.equals(that.grado) &&
                grupo.equals(that.grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoEntidadPK, grado, grupo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoEntidad.class.getSimpleName() + "[", "]")
                .add("grupoEntidadPK=" + grupoEntidadPK)
                .add("grado=" + grado)
                .add("grupo=" + grupo)
                .toString();
    }

}
