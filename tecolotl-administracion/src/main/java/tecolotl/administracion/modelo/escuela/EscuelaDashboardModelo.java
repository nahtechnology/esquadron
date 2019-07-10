package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import java.util.StringJoiner;

/**
 * modelo con los datos de una escuela con los datos para una visualización
 * rápida para que el usuario no entre en detalles de dicha escuela.
 */
public class EscuelaDashboardModelo extends EscuelaBaseModelo {

    private boolean activo;
    private int diasRestantes;
    private int licencias;

    public EscuelaDashboardModelo() {
    }

    public EscuelaDashboardModelo(String claveCentroTrabajo) {
        super(claveCentroTrabajo);
    }

    public EscuelaDashboardModelo(EscuelaEntidad escuelaEntidad) {
        super(escuelaEntidad);
        setActivo(escuelaEntidad.getMotivoBloqueoEntidad().getValor().equals("Sin bloqueo"));
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public int getLicencias() {
        return licencias;
    }

    public void setLicencias(int licencias) {
        this.licencias = licencias;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscuelaDashboardModelo.class.getSimpleName() + "[", "]")
                .add("activo=" + activo)
                .add("diasRestantes=" + diasRestantes)
                .add("licencias=" + licencias)
                .add("super" + super.toString())
                .toString();
    }
}
