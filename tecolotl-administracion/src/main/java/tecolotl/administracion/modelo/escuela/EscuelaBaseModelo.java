package tecolotl.administracion.modelo.escuela;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.EscuelaLlavePrimariaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

public class EscuelaBaseModelo implements Comparable<EscuelaBaseModelo> {

    @NotNull(message = "Clave centro de trabajo no puede ser nulo", groups = {EscuelaLlavePrimariaValidacion.class})
    @Size(min = 10, max = 14)
    private String claveCentroTrabajo;

    @NotNull
    @Size(min = 4, max = 70)
    private String nombre;

    @NotNull
    @Size(min = 2, max = 60)
    private String domicilio;

    @Size(max = 15, min = 1)
    private String numeroInterior;

    @NotNull
    @Size(max = 15, min = 1)
    private String numeroExterior;

    private Integer galaxia;

    public EscuelaBaseModelo() {
    }

    public EscuelaBaseModelo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public EscuelaBaseModelo(EscuelaEntidad escuelaEntidad) {
        claveCentroTrabajo = escuelaEntidad.getClaveCentroTrabajo();
        nombre = escuelaEntidad.getNombre();
        domicilio = escuelaEntidad.getDomicilio();
        numeroExterior = escuelaEntidad.getNumeroExterior();
        numeroInterior = escuelaEntidad.getNumeroInterior();
        galaxia = escuelaEntidad.getGalaxia();
    }

    @Override
    public int compareTo(EscuelaBaseModelo o) {
        return claveCentroTrabajo.compareTo(o.claveCentroTrabajo);
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Integer getGalaxia() {
        return galaxia;
    }

    public void setGalaxia(Integer galaxia) {
        this.galaxia = galaxia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaBaseModelo that = (EscuelaBaseModelo) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscuelaBaseModelo.class.getSimpleName() + "[", "]")
                .add("claveCentroTrabajo='" + claveCentroTrabajo + "'")
                .add("nombre='" + nombre + "'")
                .add("domicilio='" + domicilio + "'")
                .add("numeroInterior='" + numeroInterior + "'")
                .add("numeroExterior='" + numeroExterior + "'")
                .toString();
    }
}
