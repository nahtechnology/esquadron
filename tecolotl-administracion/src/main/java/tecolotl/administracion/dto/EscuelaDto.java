package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import java.util.Objects;

public class EscuelaDto {

    private String claveCentroTrabajo;
    private String nombre;
    private boolean estatus;
    private int licencias;
    private int diasRestantes;
    private String numeroExterior;
    private String numeroInterior;

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

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaDto that = (EscuelaDto) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo);
    }
}
