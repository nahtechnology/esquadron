package tecolotl.administracion.dto;

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
}
