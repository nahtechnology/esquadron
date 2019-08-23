package tecolotl.profesor.entidad;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class CicloEscolarEntidadPK implements Serializable {

    private Date inicio;
    private Date fin;
    private EscuelaEntidad escuelaEntidad;

    @Basic
    @Column(name = "inicio")
    @NotNull
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @Basic
    @Column(name = "fin")
    @NotNull
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escuela")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloEscolarEntidadPK that = (CicloEscolarEntidadPK) o;
        return inicio.equals(that.inicio) &&
                fin.equals(that.fin) &&
                escuelaEntidad.equals(that.escuelaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fin, escuelaEntidad);
    }

    @Override
    public String toString() {
        return "CicloEscolarEntidadPK{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", escuelaEntidad=" + escuelaEntidad +
                '}';
    }
}
