package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;
import tecolotl.administracion.validacion.escuela.LicenciaActualizaValidacion;
import tecolotl.administracion.validacion.escuela.LicenciaNuevaValidacion;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class LicenciaModelo {

    private Short contador;
    private String claveCentroTrabajo;
    private Date inicio;
    private Date adquisicion;
    private Short alumnos;

    public LicenciaModelo() {
    }

    public LicenciaModelo(LicenciaEntidad licenciaEntidad) {
        contador = licenciaEntidad.getLicenciaEntidadPk().getContador();
        claveCentroTrabajo = licenciaEntidad.getLicenciaEntidadPk().getEscuelaEntidad().getClaveCentroTrabajo();
        inicio = licenciaEntidad.getInicio();
        adquisicion = licenciaEntidad.getAdquisicion();
        alumnos = licenciaEntidad.getAlumnos();
    }

    public LicenciaModelo(String claveCentroTrabajo) {
        this.contador = contador;
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public LicenciaModelo(Short contador, String claveCentroTrabajo) {
        this.contador = contador;
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    @NotNull(groups = {LicenciaActualizaValidacion.class})
    @Min(value = 0, groups = {LicenciaActualizaValidacion.class})
    public Short getContador() {
        return contador;
    }

    public void setContador(Short contador) {
        this.contador = contador;
    }

    @NotNull(groups = {LicenciaNuevaValidacion.class, LicenciaActualizaValidacion.class})
    @Size(min = 10, max = 14, groups = {LicenciaNuevaValidacion.class, LicenciaActualizaValidacion.class})
    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    @NotNull(groups = {LicenciaActualizaValidacion.class})
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Date adquisicion) {
        this.adquisicion = adquisicion;
    }

    @NotNull
    @Max(250)
    public Short getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Short alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenciaModelo that = (LicenciaModelo) o;
        return contador.equals(that.contador) &&
                claveCentroTrabajo.equals(that.claveCentroTrabajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contador, claveCentroTrabajo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LicenciaModelo{");
        sb.append("contador=").append(contador);
        sb.append(", claveCentroTrabajo='").append(claveCentroTrabajo).append('\'');
        sb.append(", inicio=").append(inicio);
        sb.append(", adquisicion=").append(adquisicion);
        sb.append(", alumnos=").append(alumnos);
        sb.append('}');
        return sb.toString();
    }
}
