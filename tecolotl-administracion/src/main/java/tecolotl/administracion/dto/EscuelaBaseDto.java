package tecolotl.administracion.dto;

import java.util.Objects;

public class EscuelaBaseDto {

    private String claveCentroTrabajo;
    private String nombre;

    public EscuelaBaseDto() {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaBaseDto(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaBaseDto that = (EscuelaBaseDto) o;
        return Objects.equals(claveCentroTrabajo, that.claveCentroTrabajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EscuelaBaseDto{");
        sb.append("claveCentroTrabajo='").append(claveCentroTrabajo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
