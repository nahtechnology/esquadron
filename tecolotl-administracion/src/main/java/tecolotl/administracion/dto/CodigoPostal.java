package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

public class CodigoPostal {

    private String nombre;
    private int id;

    public CodigoPostal(ColoniaEntidad coloniaEntidad) {
        id = coloniaEntidad.getId();
        nombre = coloniaEntidad.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
