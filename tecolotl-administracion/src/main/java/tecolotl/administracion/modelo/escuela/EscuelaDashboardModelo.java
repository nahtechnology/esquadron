package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

/**
 * Modelo con los datos de una escuela con los datos para una visualización
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
        setActivo(escuelaEntidad.getMotivoBloqueoEntidad().getValor().equals("Sin Bloqueo"));
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
}
