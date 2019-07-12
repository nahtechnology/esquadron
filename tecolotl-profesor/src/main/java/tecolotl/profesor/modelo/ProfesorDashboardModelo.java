package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.ProfesorEntidad;

import java.util.StringJoiner;

public class ProfesorDashboardModelo extends ProfesorModelo {

    private Integer totalGrupos;

    public ProfesorDashboardModelo() {
    }

    public ProfesorDashboardModelo(ProfesorModelo profesorModelo) {
        super(profesorModelo);
        totalGrupos =  0;
    }

    public ProfesorDashboardModelo(ProfesorModelo profesorModelo, int totalGrupos) {
        super(profesorModelo);
        this.totalGrupos = totalGrupos;
    }

    public ProfesorDashboardModelo(ProfesorEntidad profesorEntidad) {
        super(profesorEntidad);
    }

    public Integer getTotalGrupos() {
        return totalGrupos;
    }

    public void setTotalGrupos(Integer totalGrupos) {
        this.totalGrupos = totalGrupos;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProfesorDashboardModelo.class.getSimpleName() + "[", "]")
                .add("totalGrupos=" + totalGrupos)
                .toString();
    }
}
