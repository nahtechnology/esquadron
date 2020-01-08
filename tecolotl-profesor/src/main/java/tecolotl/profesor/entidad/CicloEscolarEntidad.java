package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "ciclo_escolar", schema = "profesor")
@NamedQueries(value = {
    @NamedQuery(name = "CicloEscolarEntidad.busca", query = "SELECT ce FROM CicloEscolarEntidad ce"),
    @NamedQuery(
        name = "CicloEscolarEntidad.buscaIdEscuela",
        query = "SELECT DISTINCT ce FROM CicloEscolarEntidad ce JOIN ce.grupoEntidadLista g " +
                "WHERE ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND ce.activo = :activo " +
                "AND g.profesorEntidad.id = :idProfesor"
    ),
    @NamedQuery(
        name = "CicloEscolarEntidad.buscaEscuela",
        query = "SELECT ce FROM CicloEscolarEntidad ce WHERE ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo")
})
public class CicloEscolarEntidad {

    private CicloEscolarEntidadPK cicloEscolarPK;
    private Boolean activo;
    private String descripcion;
    private List<GrupoEntidad> grupoEntidadLista;

    public CicloEscolarEntidad() {
    }

    public CicloEscolarEntidad(CicloEscolarEntidadPK cicloEscolarPK) {
        this.cicloEscolarPK = cicloEscolarPK;
    }

    public CicloEscolarEntidad(CicloEscolarEntidadPK cicloEscolarPK, String descripcion) {
        this.cicloEscolarPK = cicloEscolarPK;
        this.descripcion = descripcion;
    }

    @EmbeddedId
    public CicloEscolarEntidadPK getCicloEscolarPK() {
        return cicloEscolarPK;
    }

    public void setCicloEscolarPK(CicloEscolarEntidadPK cicloEscolarPK) {
        this.cicloEscolarPK = cicloEscolarPK;
    }

    @Basic
    @Column(name = "descripcion")
    @NotNull
    @Size(max = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "activo", insertable = false)
    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @OneToMany(mappedBy = "cicloEscolarEntidad")
    public List<GrupoEntidad> getGrupoEntidadLista() {
        return grupoEntidadLista;
    }

    public void setGrupoEntidadLista(List<GrupoEntidad> grupoEntidadLista) {
        this.grupoEntidadLista = grupoEntidadLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloEscolarEntidad that = (CicloEscolarEntidad) o;
        return cicloEscolarPK.equals(that.cicloEscolarPK) &&
                activo.equals(that.activo) &&
                descripcion.equals(that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cicloEscolarPK, activo, descripcion);
    }
}
