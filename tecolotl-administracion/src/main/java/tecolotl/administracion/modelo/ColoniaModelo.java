package tecolotl.administracion.modelo;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ColoniaModelo {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 4, max = 5)
    private String codigoPostal;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    public ColoniaModelo() {
    }

    public ColoniaModelo(Integer id) {
        this.id = id;
    }

    public ColoniaModelo(ColoniaEntidad coloniaEntidad) {
        id = coloniaEntidad.getId();
        codigoPostal = coloniaEntidad.getCodigoPostal();
        nombre = coloniaEntidad.getNombre();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColoniaModelo that = (ColoniaModelo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColoniaModelo{");
        sb.append("id=").append(id);
        sb.append(", codigoPostal='").append(codigoPostal).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
