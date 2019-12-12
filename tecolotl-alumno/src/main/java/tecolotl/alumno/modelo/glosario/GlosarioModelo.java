package tecolotl.alumno.modelo.glosario;

import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class GlosarioModelo implements Comparable<GlosarioModelo>{

    private String palabra;
    private ClaseGlosarioModelo claseGlosarioModelo;
    private String significado;

    public GlosarioModelo() {
    }

    /**
     * Crea una glosario con una palabra
     * @param palabra Palabra clave
     */
    public GlosarioModelo(String palabra) {
        this.palabra = palabra;
    }

    /**
     * Contruye un glosario modelo a partir de una entidad.
     * @param glosarioEntidad Entidad de glosario.
     */
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
    @Size(max = 300)
    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    @Override
    public int compareTo(GlosarioModelo o) {
        int hash  = this.palabra.compareTo(o.palabra);
        if(hash == 0) return this.claseGlosarioModelo.getClave().compareTo(o.claseGlosarioModelo.getClave());
        return 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GlosarioModelo.class.getSimpleName() + "[", "]")
                .add("palabra='" + palabra + "'")
                .add("claseGlosarioModelo=" + claseGlosarioModelo)
                .add("significado='" + significado + "'")
                .toString();
    }
}
