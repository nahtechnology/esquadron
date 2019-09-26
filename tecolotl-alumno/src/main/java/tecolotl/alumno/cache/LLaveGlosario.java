package tecolotl.alumno.cache;

import tecolotl.alumno.modelo.glosario.GlosarioModelo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

public class LLaveGlosario {

    @NotNull
    @Size(min = 1)
    private String palabra;

    @NotNull
    @Min(0)
    private Short clase;

    public LLaveGlosario() {
    }

    public LLaveGlosario(String palabra, short clase) {
        this.palabra = palabra;
        this.clase = clase;
    }

    public LLaveGlosario(GlosarioModelo glosarioModelo) {
        this(glosarioModelo.getPalabra(), glosarioModelo.getClaseGlosarioModelo().getClave());
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Short getClase() {
        return clase;
    }

    public void setClase(Short clase) {
        this.clase = clase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LLaveGlosario that = (LLaveGlosario) o;
        return palabra.equals(that.palabra) &&
                clase.equals(that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabra, clase);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LLaveGlosario.class.getSimpleName() + "[", "]")
                .add("palabra='" + palabra + "'")
                .add("clase=" + clase)
                .toString();
    }

}
