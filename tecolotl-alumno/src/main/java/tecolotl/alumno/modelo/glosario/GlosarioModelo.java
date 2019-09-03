package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.StringJoiner;

public class GlosarioModelo {

    private String palabra;
    private ClaseGlosarioModelo claseGlosarioModelo;
    private byte[] imagen;
    private String significado;

    public GlosarioModelo() {
    }

    public GlosarioModelo(String palabra) {
        this.palabra = palabra;
    }

    public GlosarioModelo(GlosarioEntidad glosarioEntidad) {
        this.palabra = glosarioEntidad.getGlosarioEntidadPK().getPalabra();
        this.claseGlosarioModelo = new ClaseGlosarioModelo(glosarioEntidad.getGlosarioEntidadPK().getClaseGlosarioEntidad());
        this.significado = glosarioEntidad.getSignificado();
    }

    @NotNull(groups = {GlosarioNuevoValidacion.class, GlosarioLlavePrimariaValidacion.class})
    @Size(max = 20, groups = {GlosarioNuevoValidacion.class, GlosarioLlavePrimariaValidacion.class})
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @NotNull(groups = {GlosarioLlavePrimariaValidacion.class})
    @Valid
    public ClaseGlosarioModelo getClaseGlosarioModelo() {
        return claseGlosarioModelo;
    }

    public void setClaseGlosarioModelo(ClaseGlosarioModelo claseGlosarioModelo) {
        this.claseGlosarioModelo = claseGlosarioModelo;
    }

    @NotNull
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @NotNull
    @Size(max = 300)
    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioModelo.class.getSimpleName() + "[", "]")
                .add("palabra='" + palabra + "'")
                .add("claseGlosarioModelo=" + claseGlosarioModelo)
                .add("imagen=" + Arrays.toString(imagen))
                .add("significado='" + significado + "'")
                .toString();
    }
}
