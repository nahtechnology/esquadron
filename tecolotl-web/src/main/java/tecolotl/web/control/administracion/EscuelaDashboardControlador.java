package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.*;
import tecolotl.administracion.sesion.ColoniaSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.web.control.PaginadorControlador;
import tecolotl.web.enumeracion.EscuelaOrdenamiento;
import tecolotl.web.modelo.administracion.PaginacionModeloDato;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class EscuelaDashboardControlador extends PaginadorControlador<EscuelaDto> implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private ColoniaSesionBean coloniaSesionBean;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    private List<EscuelaDto> escuelaDtoLista;
    private List<EscuelaDto> escuelaDtoSubLista;
    private EscuelaBaseDto escuelaBaseDtoModelo;
    private EscuelaDetalleDto escuelaDetalleDtoModelo;
    private List<ColoniaDto> coloniaDtoLista;
    private List<MotivoBloqueoDto> motivoBloqueoDtoLista;

    @PostConstruct
    public void init() {
        escuelaDtoLista = new ArrayList<>(escuelaSesionBean.busca());
        motivoBloqueoDtoLista = motivoBloqueoSesionBean.busca();
        ordernar(EscuelaOrdenamiento.NOMBRE);
        escuelaBaseDtoModelo = new EscuelaBaseDto();
        escuelaDetalleDtoModelo = new EscuelaDetalleDto();
        setFilasEnPagina(5);
        setTotalFilas(escuelaDtoLista.size());
        cargaDatos(0);
    }

    public void filtrar(EscuelaOrdenamiento escuelaOrdenamiento) {
        escuelaDtoSubLista = new ArrayList<>();
        switch (escuelaOrdenamiento) {
            case CCT:
                for (EscuelaDto escuelaDto : escuelaDtoLista) {
                    if (escuelaDto.getClaveCentroTrabajo().toLowerCase().contains(escuelaBaseDtoModelo.getClaveCentroTrabajo().toLowerCase())) {
                        escuelaDtoSubLista.add(escuelaDto);
                    }
                }
                break;
            case NOMBRE:
                for (EscuelaDto escuelaDto : escuelaDtoLista) {
                    if (escuelaDto.getNombre().toLowerCase().contains(escuelaBaseDtoModelo.getNombre().toLowerCase())) {
                        escuelaDtoSubLista.add(escuelaDto);
                    }
                }
                break;
        }
        setTotalFilas(escuelaDtoSubLista.size());
        cargaDatos(0);
    }

    public void limpiarFiltro() {
        escuelaDtoSubLista = null;
        setTotalFilas(escuelaDtoLista.size());
        cargaDatos(0);
    }

    public void agrega() {
        EscuelaDto escuelaDto = escuelaSesionBean.insertar(
                escuelaDetalleDtoModelo.getClaveCentroTrabajo(),
                escuelaDetalleDtoModelo.getColoniaDto().getId(),
                escuelaDetalleDtoModelo.getNombre(),
                escuelaDetalleDtoModelo.getCalle(),
                escuelaDetalleDtoModelo.getNumeroInterior(),
                escuelaDetalleDtoModelo.getNumeroExterior()
        );
        escuelaDtoSubLista = null;
        escuelaDtoLista.add(escuelaDto);
        ordernar(EscuelaOrdenamiento.NOMBRE);
        escuelaDetalleDtoModelo = new EscuelaDetalleDto();
        setTotalFilas(escuelaDtoLista.size());
        getHtmlDataTable().setFirst(0);
        cargaDatos(getHtmlDataTable().getFirst());
    }

    public void actualiza() {
        escuelaSesionBean.actualizar(
                escuelaDetalleDtoModelo.getClaveCentroTrabajo(),
                escuelaDetalleDtoModelo.getColoniaDto().getId(),
                escuelaDetalleDtoModelo.getNombre(),
                escuelaDetalleDtoModelo.getCalle()
        );
        escuelaDtoSubLista = null;
        EscuelaDto escuelaDto = escuelaDtoLista.get(escuelaDtoLista.indexOf(new EscuelaDto(escuelaDetalleDtoModelo.getClaveCentroTrabajo())));
        escuelaDto.setNombre(escuelaDetalleDtoModelo.getNombre());
        escuelaDetalleDtoModelo = new EscuelaDetalleDto();
    }

    public void buscaColonia() {
        coloniaDtoLista = coloniaSesionBean.busca(escuelaDetalleDtoModelo.getColoniaDto().getCodigoPostal());
        escuelaDetalleDtoModelo.setMunicipioDto(coloniaSesionBean.buscaMunicipio(escuelaDetalleDtoModelo.getColoniaDto().getCodigoPostal()));
        escuelaDetalleDtoModelo.setEstadoDto(coloniaSesionBean.buscaEstado(escuelaDetalleDtoModelo.getColoniaDto().getCodigoPostal()));
    }

    public void busca(EscuelaDto escuelaDto) {
        escuelaDetalleDtoModelo = escuelaSesionBean.busca(escuelaDto.getClaveCentroTrabajo());
        coloniaDtoLista = coloniaSesionBean.busca(escuelaDetalleDtoModelo.getColoniaDto().getCodigoPostal());
    }

    public void eliminia() {
        escuelaDtoSubLista = null;
        escuelaSesionBean.elimina(escuelaBaseDtoModelo.getClaveCentroTrabajo());
        escuelaDtoLista.remove(escuelaBaseDtoModelo);
        setTotalFilas(escuelaDtoLista.size());
        getHtmlDataTable().setFirst(0);
        cargaDatos(getHtmlDataTable().getFirst());
        escuelaBaseDtoModelo = new EscuelaBaseDto();
    }

    public void bloquea() {
        escuelaSesionBean.bloqueo(escuelaBaseDtoModelo.getClaveCentroTrabajo(), escuelaDetalleDtoModelo.getMotivoBlqueoDto().getId());
        EscuelaBaseDto escuelaBaseDto = escuelaDtoLista.get(escuelaDtoLista.indexOf(escuelaBaseDtoModelo));
        ((EscuelaDto) escuelaBaseDto).setEstatus(false);
    }

    @Override
    public List<EscuelaDto> getDatos() {
        return escuelaDtoSubLista == null ? escuelaDtoLista : escuelaDtoSubLista;
    }

    public void ordernar(final EscuelaOrdenamiento escuelaOrdenamiento) {
        Collections.sort(escuelaDtoLista, new Comparator<EscuelaDto>() {
            @Override
            public int compare(EscuelaDto o1, EscuelaDto o2) {
                switch (escuelaOrdenamiento) {
                    case NOMBRE:
                        return o1.getNombre().compareTo(o2.getNombre());
                    case CCT:
                        return o1.getClaveCentroTrabajo().compareTo(o2.getClaveCentroTrabajo());
                    default:
                        throw new IllegalArgumentException();
                }
            }
        });
    }

    public EscuelaBaseDto getEscuelaBaseDtoModelo() {
        return escuelaBaseDtoModelo;
    }

    public void setEscuelaBaseDtoModelo(EscuelaBaseDto escuelaBaseDtoModelo) {
        this.escuelaBaseDtoModelo = escuelaBaseDtoModelo;
    }

    public EscuelaDetalleDto getEscuelaDetalleDtoModelo() {
        return escuelaDetalleDtoModelo;
    }

    public void setEscuelaDetalleDtoModelo(EscuelaDetalleDto escuelaDetalleDtoModelo) {
        this.escuelaDetalleDtoModelo = escuelaDetalleDtoModelo;
    }

    public List<ColoniaDto> getColoniaDtoLista() {
        return coloniaDtoLista;
    }

    public void setColoniaDtoLista(List<ColoniaDto> coloniaDtoLista) {
        this.coloniaDtoLista = coloniaDtoLista;
    }

    public List<EscuelaDto> getEscuelaDtoSubLista() {
        return escuelaDtoSubLista;
    }

    public void setEscuelaDtoSubLista(List<EscuelaDto> escuelaDtoSubLista) {
        this.escuelaDtoSubLista = escuelaDtoSubLista;
    }

    public List<MotivoBloqueoDto> getMotivoBloqueoDtoLista() {
        return motivoBloqueoDtoLista;
    }

    public void setMotivoBloqueoDtoLista(List<MotivoBloqueoDto> motivoBloqueoDtoLista) {
        this.motivoBloqueoDtoLista = motivoBloqueoDtoLista;
    }
}
