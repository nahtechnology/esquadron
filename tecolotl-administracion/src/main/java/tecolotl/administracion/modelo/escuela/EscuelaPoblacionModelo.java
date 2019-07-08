package tecolotl.administracion.modelo.escuela;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EscuelaPoblacionModelo {

    private Integer totalProfesores;
    private Integer totalAlumnos;

    @NotNull
    @Min(0)
    public Integer getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(Integer totalProfesores) {
        this.totalProfesores = totalProfesores;
    }

    @NotNull
    @Min(0)
    public Integer getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(Integer totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }
}
