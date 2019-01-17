package tecolotl.administracion.persistencia.entidad;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContactoEntidadPK implements Serializable {

    private TipoContactoEntidad tipoContactoEntidad;
    private EscuelaEntidad escuelaEntidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_contacto", referencedColumnName="id")
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
