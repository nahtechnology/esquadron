package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoPostal that = (CodigoPostal) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
