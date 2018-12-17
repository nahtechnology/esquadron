package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;

import java.util.Objects;

public class EscuelaDto extends EscuelaBaseDto{

    private boolean estatus;
    private int licencias;
    private int diasRestantes;

    public EscuelaDto(EscuelaEntidad escuelaEntidad) {
        setClaveCentroTrabajo(escuelaEntidad.getClaveCentroTrabajo());
        setNombre(escuelaEntidad.getNombre());
        estatus = escuelaEntidad.getMotivoBloqueoEntidad().getId() == 0;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaDto that = (EscuelaDto) o;
        return getClaveCentroTrabajo().equals(that.getClaveCentroTrabajo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClaveCentroTrabajo());
    }
}
