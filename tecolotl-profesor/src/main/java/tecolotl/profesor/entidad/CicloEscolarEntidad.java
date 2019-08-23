package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "ciclo_escolar", schema = "profesor")
@NamedQueries(value = {
    @NamedQuery(name = "CicloEscolarEntidad.busca", query = "SELECT ce FROM CicloEscolarEntidad ce"),
    @NamedQuery(
        name = "CicloEscolarEntidad.buscaEscuela",
        query = "SELECT ce FROM CicloEscolarEntidad ce JOIN FETCH ce.cicloEscolarPK.escuelaEntidad e WHERE e.claveCentroTrabajo = :claveCentroTrabajo"
    )
})
public class CicloEscolarEntidad {

    private CicloEscolarEntidadPK cicloEscolarPK;
    private Boolean activo;
    private String descripcion;

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
