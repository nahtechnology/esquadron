package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;

import java.util.ArrayList;
import java.util.List;

public class EscuelaDetalleDto extends EscuelaBaseDto {

    private String numeroExterior;
    private String numeroInterior;
    private String calle;
    private String codigoPostal;
    private String colonia;
    private String municipio;
    private String estado;
    private String motivoBlqueo;
    private List<LicenciaDto> licenciaDtoList;
    private List<ContactoDto> contactoDtoList;

    public EscuelaDetalleDto() {
    }

    public EscuelaDetalleDto(String claveCentroTrabajo) {
        super(claveCentroTrabajo);
    }

    public EscuelaDetalleDto (EscuelaEntidad escuelaEntidad) {
        setClaveCentroTrabajo(escuelaEntidad.getClaveCentroTrabajo());
        setNombre(escuelaEntidad.getNombre());
        calle = escuelaEntidad.getDomicilio();
        numeroExterior = escuelaEntidad.getNumeroExterior();
        numeroInterior = escuelaEntidad.getNumeroInterior();
        codigoPostal = escuelaEntidad.getColoniaEntidad().getCodigoPostal();
        colonia = escuelaEntidad.getColoniaEntidad().getNombre();
        municipio = escuelaEntidad.getColoniaEntidad().getMunicipio().getNombre();
        estado = escuelaEntidad.getColoniaEntidad().getMunicipio().getEstado().getNombre();
        motivoBlqueo = escuelaEntidad.getMotivoBloqueoEntidad().getDescripcion();
        licenciaDtoList = new ArrayList<>();
        for (LicenciaEntidad licenciaEntidad : escuelaEntidad.getLicencia()) {
            licenciaDtoList.add(new LicenciaDto(licenciaEntidad));
        }
        contactoDtoList = new ArrayList<>();
        for (ContactoEntidad contactoEntidad : escuelaEntidad.getContacto()) {
            contactoDtoList.add(new ContactoDto(contactoEntidad));
        }
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoBlqueo() {
        return motivoBlqueo;
    }

    public void setMotivoBlqueo(String motivoBlqueo) {
        this.motivoBlqueo = motivoBlqueo;
    }

    public List<LicenciaDto> getLicenciaDtoList() {
        return licenciaDtoList;
    }

    public void setLicenciaDtoList(List<LicenciaDto> licenciaDtoList) {
        this.licenciaDtoList = licenciaDtoList;
    }

    public List<ContactoDto> getContactoDtoList() {
        return contactoDtoList;
    }

    public void setContactoDtoList(List<ContactoDto> contactoDtoList) {
        this.contactoDtoList = contactoDtoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EscuelaDetalleDto{");
        sb.append("numeroExterior='").append(numeroExterior).append('\'');
        sb.append(", numeroInterior='").append(numeroInterior).append('\'');
        sb.append(", calle='").append(calle).append('\'');
        sb.append(", codigoPostal='").append(codigoPostal).append('\'');
        sb.append(", colonia='").append(colonia).append('\'');
        sb.append(", municipio='").append(municipio).append('\'');
        sb.append(", estado='").append(estado).append('\'');
        sb.append(", motivoBlqueo='").append(motivoBlqueo).append('\'');
        sb.append(", licenciaDtoList=").append(licenciaDtoList);
        sb.append(" super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
