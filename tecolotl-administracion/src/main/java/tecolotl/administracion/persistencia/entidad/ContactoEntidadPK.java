package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class ContactoEntidadPK implements Serializable {

    private EscuelaEntidad escuelaEntidad;
    private Short contador;

    public ContactoEntidadPK() {
    }

    public ContactoEntidadPK(EscuelaEntidad escuelaEntidad, Short contador) {
        this.escuelaEntidad = escuelaEntidad;
        this.contador = contador;
    }

    @ManyToOne
    @JoinColumn(name="id_escuela", referencedColumnName="clave_centro_trabajo")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Basic
    @Column(name = "contador", insertable = false, updatable = false)
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactoEntidadPK that = (ContactoEntidadPK) o;
        return escuelaEntidad.equals(that.escuelaEntidad) &&
                contador.equals(that.contador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escuelaEntidad, contador);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactoEntidadPK.class.getSimpleName() + "[", "]")
                .add("escuelaEntidad=" + escuelaEntidad)
                .add("contador=" + contador)
                .toString();
    }
}
