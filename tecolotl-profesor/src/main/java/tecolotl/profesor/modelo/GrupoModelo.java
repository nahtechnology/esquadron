package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoEntidad;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class GrupoModelo {

    private Short grado;
    private Character grupo;

    public GrupoModelo() {
    }

    public GrupoModelo(GrupoEntidad grupoEntidad) {
        this.grado = grupoEntidad.getGrado();
        this.grupo = grupoEntidad.getGrupo();
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

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
        GrupoModelo that = (GrupoModelo) o;
        return grado.equals(that.grado) &&
                grupo.equals(that.grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grado, grupo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoModelo.class.getSimpleName() + "[", "]")
                .add("grado=" + grado)
                .add("grupo=" + grupo)
                .toString();
    }

}
