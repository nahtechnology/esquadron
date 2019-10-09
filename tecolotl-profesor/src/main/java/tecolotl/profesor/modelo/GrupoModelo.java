package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.validacion.GrupoLlavePrimariaValidacion;
import tecolotl.profesor.validacion.GrupoNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class GrupoModelo {

    private Integer id;
    private Short grado;
    private Character grupo;
    private Integer idProfesor;

    public GrupoModelo() {
    }

    public GrupoModelo(Integer id) {
        this.id = id;
    }

    public GrupoModelo(GrupoEntidad grupoEntidad) {
        this.id = grupoEntidad.getId();
        this.grado = grupoEntidad.getGrado();
        this.grupo = grupoEntidad.getGrupo();
    }

    @NotNull(groups = {GrupoNuevoValidacion.class, GrupoLlavePrimariaValidacion.class})
    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    @NotNull(groups = {GrupoNuevoValidacion.class})
    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(Character grupo) {
        this.grupo = grupo;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(groups = {GrupoNuevoValidacion.class})
    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoModelo that = (GrupoModelo) o;
        return id.equals(that.id) &&
                grado.equals(that.grado) &&
                grupo.equals(that.grupo) &&
                idProfesor.equals(that.idProfesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grado, grupo, idProfesor);
    }

    @Override
    public String toString() {
        return "GrupoModelo{" +
                "id=" + id +
                ", grado=" + grado +
                ", grupo=" + grupo +
                ", idProfesor=" + idProfesor +
                '}';
    }

}
