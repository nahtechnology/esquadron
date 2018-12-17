package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.modelo.administracion.EscuelaModelo;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EscuelaControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private Collection<EscuelaDto> escuelas;
    private EscuelaModelo escuelaModelo;
    private EscuelaDetalleDto escuelaDetalleDto;

    @PostConstruct
    public void init() {
        escuelas = escuelaSesionBean.busca();
        escuelaModelo = new EscuelaModelo();
    }

    public void inserta() {
        logger.info(escuelaModelo.getClaveCentroTrabajo());
        escuelaSesionBean.insertar(escuelaModelo.getClaveCentroTrabajo(),
                Integer.parseInt(escuelaModelo.getIdColonia()), escuelaModelo.getNombre(), escuelaModelo.getCalle(),
                escuelaModelo.getNumeroInterior(), escuelaModelo.getNumeroExterior());
    }

    public String detalle(String claveCentroTrabajo) {
        escuelaDetalleDto = escuelaSesionBean.busca(claveCentroTrabajo);
        return "/administracion/detalle-escuela.xhtml";
    }

    public void busca(String claveCentroTrabajo) {
        escuelaDetalleDto = escuelaSesionBean.busca(claveCentroTrabajo);
    }

    public Collection<EscuelaDto> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(Collection<EscuelaDto> escuelas) {
        this.escuelas = escuelas;
    }

    public EscuelaModelo getEscuelaModelo() {
        return escuelaModelo;
    }

    public void setEscuelaModelo(EscuelaModelo escuelaModelo) {
        this.escuelaModelo = escuelaModelo;
    }

    public EscuelaDetalleDto getEscuelaDetalleDto() {
        return escuelaDetalleDto;
    }

    public void setEscuelaDetalleDto(EscuelaDetalleDto escuelaDetalleDto) {
        this.escuelaDetalleDto = escuelaDetalleDto;
    }
}
