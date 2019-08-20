package tecolotl.profesor.entidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class GrupoEntidadPK implements Serializable {

    private ProfesorEntidad profesorEntidad;
    private CicloEscolarEntidad cicloEscolarEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "id_profesor")
    public ProfesorEntidad getProfesorEntidad() {
        return profesorEntidad;
    }

    public void setProfesorEntidad(ProfesorEntidad profesorEntidad) {
        this.profesorEntidad = profesorEntidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumns(value = {
            @JoinColumn(name = "inicio"),
            @JoinColumn(name = "fin")
    })
    public CicloEscolarEntidad getCicloEscolarEntidad() {
        return cicloEscolarEntidad;
    }

    public void setCicloEscolarEntidad(CicloEscolarEntidad cicloEscolarEntidad) {
        this.cicloEscolarEntidad = cicloEscolarEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntidadPK that = (GrupoEntidadPK) o;
        return profesorEntidad.equals(that.profesorEntidad) &&
                cicloEscolarEntidad.equals(that.cicloEscolarEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profesorEntidad, cicloEscolarEntidad);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoEntidadPK.class.getSimpleName() + "[", "]")
                .add("profesorEntidad=" + profesorEntidad)
                .add("cicloEscolarEntidad=" + cicloEscolarEntidad)
                .toString();
    }
}
