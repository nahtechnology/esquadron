package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.ProfesorEntidad;

public class ProfesorDashboardModelo extends ProfesorModelo {

    private Integer totalGrupos;

    public ProfesorDashboardModelo() {
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
}
