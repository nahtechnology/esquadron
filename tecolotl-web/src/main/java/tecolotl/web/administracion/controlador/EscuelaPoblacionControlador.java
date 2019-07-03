package tecolotl.web.administracion.controlador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class EscuelaPoblacionControlador {

    private int totalAlumnos;
    private int totalProfesores;

    public int getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    public int getTotalProfesores() {
        return totalProfesores;
    }

    public void setTotalProfesores(int totalProfesores) {
        this.totalProfesores = totalProfesores;
    }
}
