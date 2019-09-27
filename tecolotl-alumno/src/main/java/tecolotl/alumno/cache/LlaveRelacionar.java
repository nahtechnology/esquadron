package tecolotl.alumno.cache;

import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarOriginalModelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

public class LlaveRelacionar {

    @NotNull
    @Size(min = 32, max = 32)
    private String codigo;

    public LlaveRelacionar() {
    }

    public LlaveRelacionar(String codigo) {
        this.codigo = codigo;
    }

    public LlaveRelacionar(RelacionarOriginalModelo relacionarOriginalModelo){
        this.codigo = relacionarOriginalModelo.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LlaveRelacionar that = (LlaveRelacionar) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LlaveRelacionar.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .toString();
    }
}
