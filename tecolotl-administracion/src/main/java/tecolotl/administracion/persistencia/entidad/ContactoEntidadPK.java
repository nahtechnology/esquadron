package tecolotl.administracion.persistencia.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContactoEntidadPK implements Serializable {

    private TipoContactoEntidad tipoContactoEntidad;
    private EscuelaEntidad escuelaEntidad;
    private Short contador;

    public ContactoEntidadPK() {
    }

    public ContactoEntidadPK(TipoContactoEntidad tipoContactoEntidad, EscuelaEntidad escuelaEntidad, Short contador) {
        this.tipoContactoEntidad = tipoContactoEntidad;
        this.escuelaEntidad = escuelaEntidad;
        this.contador = contador;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_contacto", referencedColumnName="clave")
    public TipoContactoEntidad getTipoContactoEntidad() {
        return tipoContactoEntidad;
    }

    public void setTipoContactoEntidad(TipoContactoEntidad tipoContactoEntidad) {
        this.tipoContactoEntidad = tipoContactoEntidad;
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
        return tipoContactoEntidad.equals(that.tipoContactoEntidad) &&
                escuelaEntidad.equals(that.escuelaEntidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoContactoEntidad, escuelaEntidad);
    }
}
