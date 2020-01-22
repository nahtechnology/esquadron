package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.validacion.GrupoLlavePrimariaValidacion;
import tecolotl.profesor.validacion.GrupoNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

public class GrupoModelo implements Cloneable{

    private UUID id;
    private Short grado;
    private Character grupo;
    private UUID idProfesor;
    private CicloEscolarModelo cicloEscolarModelo;
    private int totalAlumno;

    public GrupoModelo() {
    }

    public GrupoModelo(UUID id) {
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
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotNull(groups = {GrupoNuevoValidacion.class})
    public UUID getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(UUID idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getTotalAlumno() {
        return totalAlumno;
    }

    public void setTotalAlumno(int totalAlumno) {
        this.totalAlumno = totalAlumno;
    }

    public CicloEscolarModelo getCicloEscolarModelo() {
        return cicloEscolarModelo;
    }

    public void setCicloEscolarModelo(CicloEscolarModelo cicloEscolarModelo) {
        this.cicloEscolarModelo = cicloEscolarModelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoModelo that = (GrupoModelo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GrupoModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("grado=" + grado)
                .add("grupo=" + grupo)
                .add("idProfesor=" + idProfesor)
                .add("cicloEscolarModelo=" + cicloEscolarModelo)
                .add("totalAlumno=" + totalAlumno)
                .toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GrupoModelo grupoModelo = new GrupoModelo();
        grupoModelo.setId(this.getId());
        grupoModelo.setGrupo(new Character(this.grupo));
        grupoModelo.setGrado(new Short(this.grado));
        return grupoModelo;
    }

}
