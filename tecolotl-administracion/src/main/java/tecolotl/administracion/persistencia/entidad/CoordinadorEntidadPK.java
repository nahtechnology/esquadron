package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CoordinadorEntidadPK implements Serializable {

    private EscuelaEntidad escuelaEntidad;
    private Short contador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_escuela", referencedColumnName = "clave_centro_trabajo")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Basic
    @Column(name = "contador")
    @NotNull
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
        CoordinadorEntidadPK that = (CoordinadorEntidadPK) o;
        return escuelaEntidad.equals(that.escuelaEntidad) &&
                contador.equals(that.contador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escuelaEntidad, contador);
    }
}
