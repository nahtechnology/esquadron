package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

public class EscuelaDto {

    private String claveCentroTrabajo;
    private String nombre;
    private boolean estatus;
    private int licencias;
    private int diasrestantes;

    public EscuelaDto() {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaDto(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaDto(EscuelaEntidad escuelaEntidad) {
        claveCentroTrabajo = escuelaEntidad.getClaveCentroTrabajo();
        nombre = escuelaEntidad.getNombre();
        estatus = escuelaEntidad.getMotivoBloqueoEntidad().getId() == 0;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public int getLicencias() {
        return licencias;
    }

    public void setLicencias(int licencias) {
        this.licencias = licencias;
    }

    public int getDiasrestantes() {
        return diasrestantes;
    }

    public void setDiasrestantes(int diasrestantes) {
        this.diasrestantes = diasrestantes;
    }
}
