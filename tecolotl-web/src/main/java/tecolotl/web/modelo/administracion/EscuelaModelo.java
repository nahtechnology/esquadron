package tecolotl.web.modelo.administracion;

import tecolotl.administracion.dto.CodigoPostal;
import tecolotl.administracion.dto.EscuelaDetalleDto;

import java.util.List;
import java.util.Objects;

public class EscuelaModelo {

    private String claveCentroTrabajo;
    private String nombre;
    private String idColonia;
    private String calle;
    private String numeroInterior;
    private String numeroExterior;
    private String codigoPostal;
    private String estado;
    private String municipio;

    public EscuelaModelo() {
    }

    public EscuelaModelo(EscuelaDetalleDto escuelaDetalleDto) {
        claveCentroTrabajo = escuelaDetalleDto.getClaveCentroTrabajo();
        nombre = escuelaDetalleDto.getNombre();
        calle = escuelaDetalleDto.getCalle();
        numeroInterior = escuelaDetalleDto.getNumeroInterior();
        numeroExterior = escuelaDetalleDto.getNumeroExterior();
        codigoPostal = escuelaDetalleDto.getCodigoPostal();
        estado = escuelaDetalleDto.getEstado();
        municipio = escuelaDetalleDto.getMunicipio();
    }

    private List<CodigoPostal> codigoPostalList;

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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public List<CodigoPostal> getCodigoPostalList() {
        return codigoPostalList;
    }

    public void setCodigoPostalList(List<CodigoPostal> codigoPostalList) {
        this.codigoPostalList = codigoPostalList;
    }
}
