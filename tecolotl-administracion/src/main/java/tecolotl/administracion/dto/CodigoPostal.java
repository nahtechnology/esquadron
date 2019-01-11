package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

import java.io.Serializable;
import java.util.Objects;

public class CodigoPostal implements Serializable, Comparable<CodigoPostal> {

    private String nombre;
    private String id;

    public CodigoPostal(ColoniaEntidad coloniaEntidad) {
        id = String.valueOf(coloniaEntidad.getId());
        nombre = coloniaEntidad.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public int compareTo(CodigoPostal codigoPostal) {
        return id.compareTo(codigoPostal.id);
    }
}
