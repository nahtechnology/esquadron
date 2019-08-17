package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "ciclo_escolar",schema = "profesor")
public class CicloEscolar {

    private CicloEscolarPK cicloEscolarPK;
    private EscuelaEntidad escuelaEntidad;
    private String descripcion;

    @EmbeddedId
    public CicloEscolarPK getCicloEscolarPK() {
        return cicloEscolarPK;
    }

    public void setCicloEscolarPK(CicloEscolarPK cicloEscolarPK) {
        this.cicloEscolarPK = cicloEscolarPK;
    }

    @ManyToOne
    @JoinColumn(name = "id_escuela")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloEscolar that = (CicloEscolar) o;
        return cicloEscolarPK.equals(that.cicloEscolarPK) &&
                escuelaEntidad.equals(that.escuelaEntidad) &&
                descripcion.equals(that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cicloEscolarPK, escuelaEntidad, descripcion);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CicloEscolar.class.getSimpleName() + "[", "]")
                .add("cicloEscolarPK=" + cicloEscolarPK)
                .add("escuelaEntidad=" + escuelaEntidad)
                .add("descripcion='" + descripcion + "'")
                .toString();
    }
}
