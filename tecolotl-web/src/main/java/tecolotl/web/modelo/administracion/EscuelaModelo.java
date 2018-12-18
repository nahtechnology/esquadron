package tecolotl.web.modelo.administracion;

import tecolotl.administracion.dto.CodigoPostal;

import java.util.Objects;

public class EscuelaModelo {

    private String claveCentroTrabajo;
    private String nombre;
    private String idColonia;
    private String calle;
    private String numeroInterior;
    private String numeroExterior;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(String idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscuelaModelo that = (EscuelaModelo) o;
        return claveCentroTrabajo.equals(that.claveCentroTrabajo) &&
                idColonia.equals(that.idColonia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveCentroTrabajo, idColonia);
    }
}
