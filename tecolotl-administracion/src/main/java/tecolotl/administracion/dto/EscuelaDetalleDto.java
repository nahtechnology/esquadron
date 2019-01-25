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
    private ColoniaDto coloniaDto;
    private MunicipioDto municipioDto;
    private EstadoDto estadoDto;
    private MotivoBloqueoDto motivoBlqueoDto;
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
        coloniaDto = new ColoniaDto(escuelaEntidad.getColoniaEntidad());
        municipioDto = new MunicipioDto(escuelaEntidad.getColoniaEntidad().getMunicipio());
        estadoDto = new EstadoDto(escuelaEntidad.getColoniaEntidad().getMunicipio().getEstado());
        motivoBlqueoDto = new MotivoBloqueoDto(escuelaEntidad.getMotivoBloqueoEntidad());
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

    public ColoniaDto getColoniaDto() {
        return coloniaDto;
    }

    public void setColoniaDto(ColoniaDto coloniaDto) {
        this.coloniaDto = coloniaDto;
    }

    public MunicipioDto getMunicipioDto() {
        return municipioDto;
    }

    public void setMunicipioDto(MunicipioDto municipioDto) {
        this.municipioDto = municipioDto;
    }

    public EstadoDto getEstadoDto() {
        return estadoDto;
    }

    public void setEstadoDto(EstadoDto estadoDto) {
        this.estadoDto = estadoDto;
    }

    public MotivoBloqueoDto getMotivoBlqueoDto() {
        return motivoBlqueoDto;
    }

    public void setMotivoBlqueoDto(MotivoBloqueoDto motivoBlqueoDto) {
        this.motivoBlqueoDto = motivoBlqueoDto;
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
        sb.append(", coloniaDto=").append(coloniaDto);
        sb.append(", municipioDto=").append(municipioDto);
        sb.append(", estadoDto='").append(estadoDto).append('\'');
        sb.append(", motivoBlqueoDto=").append(motivoBlqueoDto);
        sb.append(", licenciaDtoList=").append(licenciaDtoList);
        sb.append(", contactoDtoList=").append(contactoDtoList);
        sb.append('}');
        return sb.toString();
    }
}
