package tecolotl.profesor.entidad;

import javax.persistence.*;
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
    @NotNull
    @Min(0)
    @JoinColumn(name = "grado")
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @Basic
    @NotNull
    @JoinColumn(name = "grupo")
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_profesor")
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
