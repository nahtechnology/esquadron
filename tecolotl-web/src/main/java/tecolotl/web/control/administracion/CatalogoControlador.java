package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.CodigoPostal;
import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.administracion.sesion.TipoContactoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CatalogoControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    private SortedSet<MotivoBloqueoDto> motivoBloqueos;
    private SortedSet<CodigoPostal> codigoPostales;
    private SortedSet<TipoContactoDto> tipoContactos;
    private MotivoBloqueoDto motivoBloqueoModelo;
    private int paginacion;
    private int primera;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    @PostConstruct
    public void init() {
        motivoBloqueos = new TreeSet<>(motivoBloqueoSesionBean.motivoBloque());
        tipoContactos = (SortedSet<TipoContactoDto>)tipoContactoSesionBean.busca();
        motivoBloqueoModelo = new MotivoBloqueoDto();
        paginacion = 5;
        primera = 0;
    }

    public void inserta() {
        if (motivoBloqueoModelo.getId() == 0) {
            motivoBloqueoSesionBean.inserta(motivoBloqueoModelo.getDescripcion());
            motivoBloqueos.add(motivoBloqueoModelo);
        } else {
            motivoBloqueoSesionBean.actualiza(motivoBloqueoModelo);
            MotivoBloqueoDto motivoBloqueoDtoLocal = ((TreeSet<MotivoBloqueoDto>)motivoBloqueos).floor(motivoBloqueoModelo);
            motivoBloqueoDtoLocal.setDescripcion(motivoBloqueoModelo.getDescripcion());
        }
        motivoBloqueoModelo = new MotivoBloqueoDto();
    }

    public void actualiza(MotivoBloqueoDto motivoBloqueoDto) {
        logger.info("paginacion siguiente");
        motivoBloqueoModelo = motivoBloqueoDto;
    }

    public void siguiente() {
        primera += paginacion;
    }

    public SortedSet<MotivoBloqueoDto> getMotivoBloqueos() {
        return motivoBloqueos;
    }

    public void setMotivoBloqueos(SortedSet<MotivoBloqueoDto> motivoBloqueos) {
        this.motivoBloqueos = motivoBloqueos;
    }

    public MotivoBloqueoDto getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoDto motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }

    public int getPrimera() {
        return primera;
    }

    public void setPrimera(int primera) {
        this.primera = primera;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    public SortedSet<CodigoPostal> getCodigoPostales() {
        return codigoPostales;
    }

    public void setCodigoPostales(SortedSet<CodigoPostal> codigoPostales) {
        this.codigoPostales = codigoPostales;
    }
}
