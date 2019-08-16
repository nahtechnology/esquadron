package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;

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
        this.palabra = glosarioEntidad.getPalabra();
        this.claseGlosarioModelo = new ClaseGlosarioModelo(glosarioEntidad.getClaseGlosarioEntidad());
        this.imagen = glosarioEntidad.getImagen();
        this.significado = glosarioEntidad.getSignificado();
    }

    @NotNull(groups = {GlosarioNuevoValidacion.class})
    @Size(max = 20, groups = {GlosarioNuevoValidacion.class})
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public ClaseGlosarioModelo getClaseGlosarioModelo() {
        return claseGlosarioModelo;
    }

    public void setClaseGlosarioModelo(ClaseGlosarioModelo claseGlosarioModelo) {
        this.claseGlosarioModelo = claseGlosarioModelo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

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
