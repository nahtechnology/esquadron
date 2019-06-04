package tecolotl.profesor.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrupoEntidadPK implements Serializable {

    private Short grado;
    private Character grupo;
    private ProfesorEntidad profesorEntidad;

    @Basic
    @Column(name = "grado")
    @NotNull
    @Min(0)
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @Basic
    @Column(name = "grupo")
    @NotNull
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @ManyToOne
    @Column(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntidadPK that = (GrupoEntidadPK) o;
        return grado.equals(that.grado) &&
                grupo.equals(that.grupo) &&
                profesorEntidad.equals(that.profesorEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grado, grupo, profesorEntidad);
    }

}
