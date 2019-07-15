package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class CoordinadorEntidadPK implements Serializable {

    private EscuelaEntidad escuelaEntidad;
    private Short contador;

    public CoordinadorEntidadPK() {
    }

    public CoordinadorEntidadPK(EscuelaEntidad escuelaEntidad, Short contador) {
        this.escuelaEntidad = escuelaEntidad;
        this.contador = contador;
    }

    public CoordinadorEntidadPK(String claveCentroTrabajo, Short contador) {
        this(new EscuelaEntidad(claveCentroTrabajo), contador);
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escuela", referencedColumnName = "clave_centro_trabajo")
    public EscuelaEntidad getEscuelaEntidad() {
        return escuelaEntidad;
    }

    public void setEscuelaEntidad(EscuelaEntidad escuelaEntidad) {
        this.escuelaEntidad = escuelaEntidad;
    }

    @Column(name = "contador")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public String toString() {
        return new StringJoiner(", ", CoordinadorEntidadPK.class.getSimpleName() + "[", "]")
                .add("escuelaEntidad=" + escuelaEntidad)
                .add("contador=" + contador)
                .toString();
    }
}
