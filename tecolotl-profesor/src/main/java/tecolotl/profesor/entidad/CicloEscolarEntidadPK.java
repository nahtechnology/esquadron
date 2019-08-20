package tecolotl.profesor.entidad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class CicloEscolarEntidadPK implements Serializable {

    private Date inicio;
    private Date fin;

    @Basic
    @Column(name = "inicio")
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @Basic
    @Column(name = "fin")
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloEscolarEntidadPK that = (CicloEscolarEntidadPK) o;
        return inicio.equals(that.inicio) &&
                fin.equals(that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fin);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CicloEscolarEntidadPK.class.getSimpleName() + "[", "]")
                .add("inicio=" + inicio)
                .add("fin=" + fin)
                .toString();
    }
}
