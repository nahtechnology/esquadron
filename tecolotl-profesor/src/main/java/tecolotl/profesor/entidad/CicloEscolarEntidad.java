package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "ciclo_escolar", schema = "profesor")
@NamedQueries(value = {
    @NamedQuery(name = "CicloEscolarEntidad.busca", query = "SELECT ce FROM CicloEscolarEntidad ce"),
    @NamedQuery(
        name = "CicloEscolarEntidad.buscaIdEscuela",
        query = "SELECT DISTINCT ce FROM CicloEscolarEntidad ce JOIN ProfesorEntidad p ON ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = p.escuelaEntidad.claveCentroTrabajo " +
                "WHERE ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo AND ce.activo = :activo " +
                "AND p.id = :idProfesor"),
    @NamedQuery(
        name = "CicloEscolarEntidad.buscaEscuela",
        query = "SELECT ce FROM CicloEscolarEntidad ce WHERE ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"),
    @NamedQuery(
        name = "CicloEscolarEntidad.cuentaGrupo",
        query = "SELECT COUNT (g) FROM CicloEscolarEntidad ce JOIN ce.grupoEntidadLista g WHERE ce.cicloEscolarPK.fin = :fin AND " +
                "ce.cicloEscolarPK.inicio = :inicio AND ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo"),
    @NamedQuery(
        name = "CicloEscolarEntidad.cuentaEscuelaCuentaGrupo",
        query = "SELECT new CicloEscolarEntidad (ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo, ce.cicloEscolarPK.inicio, ce.cicloEscolarPK.fin, ce.activo, ce.descripcion, count (ge.id)) " +
                "FROM CicloEscolarEntidad ce LEFT JOIN ce.grupoEntidadLista ge WHERE ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo " +
                "GROUP BY ce.cicloEscolarPK.escuelaEntidad.claveCentroTrabajo, ce.cicloEscolarPK.inicio, ce.cicloEscolarPK.fin, ce.activo, ce.descripcion ")
})
public class CicloEscolarEntidad {

    private CicloEscolarEntidadPK cicloEscolarPK;
    private Boolean activo;
    private String descripcion;
    private Long totalGrupo;
    private List<GrupoEntidad> grupoEntidadLista;

    public CicloEscolarEntidad() {
    }

    public CicloEscolarEntidad(String claveCentroTrabajo, Date inicio, Date fin, Boolean activo, String descripcion, Long totalGrupo) {
        this.cicloEscolarPK = new CicloEscolarEntidadPK(inicio, fin, claveCentroTrabajo);
        this.activo = activo;
        this.descripcion = descripcion;
        this.totalGrupo = totalGrupo;
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

    @Transient
    public Long getTotalGrupo() {
        return totalGrupo;
    }

    public void setTotalGrupo(Long totalGrupo) {
        this.totalGrupo = totalGrupo;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", CicloEscolarEntidad.class.getSimpleName() + "[", "]")
                .add("cicloEscolarPK=" + cicloEscolarPK)
                .add("activo=" + activo)
                .add("descripcion='" + descripcion + "'")
                .add("grupoEntidadLista=" + grupoEntidadLista)
                .toString();
    }
}
