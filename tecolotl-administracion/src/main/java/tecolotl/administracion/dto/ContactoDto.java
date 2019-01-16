package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ContactoEntidad;

public class ContactoDto {

    private String claveCentroTrabajo;
    private String tipo;
    private String nombre;
    private String telefono;

    public ContactoDto() {
    }

    public ContactoDto(ContactoEntidad contactoEntidad) {
        claveCentroTrabajo = contactoEntidad.getContactoEntidadPK().getEscuelaEntidad().getClaveCentroTrabajo();
        tipo = contactoEntidad.getContactoEntidadPK().getTipoContactoEntidad().getDescripcion();
        nombre = contactoEntidad.getNombre();
        telefono = contactoEntidad.getTelefono();
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
}
