package tecolotl.administracion.modelo.direccion;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Representa los datos de una colonia.
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
public class ColoniaModelo {

    @NotNull
    @Min(0)
    private Integer id;

    @NotNull(groups = {ColoniaNuevaValidacion.class})
    @Size(min = 4, max = 5, groups = {ColoniaNuevaValidacion.class})
    private String codigoPostal;

    @NotNull(groups = {ColoniaNuevaValidacion.class})
    @Size(min = 3, max = 70, groups = {ColoniaNuevaValidacion.class})
    private String nombre;

    public ColoniaModelo() {
        id = new Integer(0);
    }

    /**
     * Construye una colonia por identificador.
     * @param id Indentificador de la colonia.
     */
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
