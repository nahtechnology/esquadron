package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoNuevoValidacion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ContactoModelo {

    @NotNull(groups = {ContactoNuevoValidacion.class, ContactoLlavePrimariaValidacion.class})
    @Size(min = 2, max = 14, groups = {ContactoNuevoValidacion.class, ContactoLlavePrimariaValidacion.class})
    private String claveCentroTrabajo;

    @NotNull(groups = {ContactoNuevoValidacion.class, ContactoLlavePrimariaValidacion.class})
    private TipoContactoModelo tipoContactoModelo;

    @NotNull(groups = {ContactoLlavePrimariaValidacion.class})
    @Min(value = 0, groups = {ContactoLlavePrimariaValidacion.class})
    private Short contador;

    @NotNull(groups = ContactoNuevoValidacion.class)
    @Size(min = 11, max = 110, groups = ContactoNuevoValidacion.class)
    private String nombre;

    @NotNull(groups = ContactoNuevoValidacion.class)
    @Size(min = 7, max = 20,groups = ContactoNuevoValidacion.class)
    private String telefono;

    @Size(max = 100, groups = ContactoNuevoValidacion.class)
    private String correoElectronico;

    public ContactoModelo() {
    }

    public ContactoModelo(ContactoEntidad contactoEntidad) {
        claveCentroTrabajo = contactoEntidad.getContactoEntidadPK().getEscuelaEntidad().getClaveCentroTrabajo();
        tipoContactoModelo = new TipoContactoModelo(contactoEntidad.getTipoContactoEntidad());
        contador = contactoEntidad.getContactoEntidadPK().getContador();
        nombre = contactoEntidad.getNombre();
        telefono= contactoEntidad.getTelefono();
        correoElectronico = contactoEntidad.getCorreoElectronico();
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public TipoContactoModelo getTipoContactoModelo() {
        return tipoContactoModelo;
    }

    public void setTipoContactoModelo(TipoContactoModelo tipoContactoModelo) {
        this.tipoContactoModelo = tipoContactoModelo;
    }

    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactoModelo that = (ContactoModelo) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo) &&
                tipoContactoModelo.equals(that.tipoContactoModelo) &&
                contador.equals(that.contador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo, tipoContactoModelo, contador);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactoModelo{");
        sb.append("claveCentroTrabajo='").append(claveCentroTrabajo).append('\'');
        sb.append(", tipoContactoModelo=").append(tipoContactoModelo);
        sb.append(", contador=").append(contador);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", correoElectronico='").append(correoElectronico).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
