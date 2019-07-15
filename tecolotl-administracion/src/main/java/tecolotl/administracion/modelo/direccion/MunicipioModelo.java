package tecolotl.administracion.modelo.direccion;

import tecolotl.administracion.persistencia.entidad.MunicipioEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class MunicipioModelo {

    private Integer id;
    private String nombre;

    public MunicipioModelo() {
    }

    public MunicipioModelo(Integer id) {
        this.id = id;
    }

    public MunicipioModelo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MunicipioModelo(MunicipioEntidad municipioEntidad) {
        id = municipioEntidad.getId();
        nombre = municipioEntidad.getNombre();
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 50, groups = {ColoniaNuevaValidacion.class})
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MunicipioModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nombre='" + nombre + "'")
                .toString();
    }
}
