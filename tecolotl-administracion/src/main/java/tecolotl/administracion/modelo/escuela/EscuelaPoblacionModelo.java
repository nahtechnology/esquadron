package tecolotl.administracion.modelo.escuela;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class EscuelaPoblacionModelo {

    private int totalProfesores;
    private int totalAlumnos;

    @NotNull
    @Min(0)
    public int getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(int totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    @NotNull
    @Min(0)
    public int getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscuelaPoblacionModelo.class.getSimpleName() + "[", "]")
                .add("totalProfesores=" + totalProfesores)
                .add("totalAlumnos=" + totalAlumnos)
                .toString();
    }
}
