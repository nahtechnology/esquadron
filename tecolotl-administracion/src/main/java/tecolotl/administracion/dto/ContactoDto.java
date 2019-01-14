package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ContactoEntidad;

public class ContactoDto {

    private int id;
    private String tipo;
    private String nombre;
    private String telefono;

    public ContactoDto() {
    }

    public ContactoDto(ContactoEntidad contactoEntidad) {
        id = contactoEntidad.getTipoContactoEntidad().getId();
        tipo = contactoEntidad.getTipoContactoEntidad().getDescripcion();
        nombre = contactoEntidad.getNombre();
        telefono = contactoEntidad.getTelefono();
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
