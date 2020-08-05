package tecolotl.administracion.modelo;

import tecolotl.administracion.persistencia.vista.LicenciaTiempoVista;

public class LicenciaVistaModelo {

    private String claveCentroTrabajo;
    private int totalLicencia;
    private int dias;

    public LicenciaVistaModelo() {
    }

    public LicenciaVistaModelo(LicenciaTiempoVista licenciaTiempoVista) {
        claveCentroTrabajo = licenciaTiempoVista.getClaveCentroTrabajo();
        totalLicencia = licenciaTiempoVista.getTotalLicencia();
        dias = licenciaTiempoVista.getDias();
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getTotalLicencia() {
        return totalLicencia;
    }

    public void setTotalLicencia(int totalLicencia) {
        this.totalLicencia = totalLicencia;
    }
}
