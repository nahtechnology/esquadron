package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;

import java.util.Date;
import java.util.Objects;

public class LicenciaDto {

    private int id;
    private Date inicio;

    public LicenciaDto() {
    }

    public LicenciaDto(int id) {
        this.id = id;
    }

    public LicenciaDto(LicenciaEntidad licenciaEntidad) {
        id = licenciaEntidad.getId();
        inicio = licenciaEntidad.getInicio();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenciaDto that = (LicenciaDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LicenciaDto{");
        sb.append("id=").append(id);
        sb.append(", fechaInicio=").append(inicio);
        sb.append('}');
        return sb.toString();
    }
}
