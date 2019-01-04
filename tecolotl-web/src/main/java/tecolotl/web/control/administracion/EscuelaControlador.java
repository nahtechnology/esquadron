package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.dto.MotivoBloqueoDto;
import tecolotl.administracion.sesion.ColoniaSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;
import tecolotl.web.modelo.administracion.EscuelaModelo;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EscuelaControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private ColoniaSesionBean coloniaSesionBean;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    private Collection<EscuelaDto> escuelas;
    private List<MotivoBloqueoDto> motivos;
    private EscuelaModelo escuelaModelo;
    private int motivoBloqueo;

    @PostConstruct
    public void init() {
        escuelas = escuelaSesionBean.busca();
        escuelaModelo = new EscuelaModelo();
        motivos = motivoBloqueoSesionBean.motivoBloque();
    }

    public void inserta() {
        logger.info(escuelaModelo.getClaveCentroTrabajo());
        escuelaSesionBean.insertar(escuelaModelo.getClaveCentroTrabajo(),
                Integer.parseInt(escuelaModelo.getIdColonia()), escuelaModelo.getNombre(), escuelaModelo.getCalle(),
                escuelaModelo.getNumeroInterior(), escuelaModelo.getNumeroExterior());
        escuelaModelo = new EscuelaModelo();
    }

    public void bloquear(String claveCentroTrabajo) {
        escuelaSesionBean.bloqueo(claveCentroTrabajo, motivoBloqueo);
    }

    public void eliminar(String claveCentroTrabajo) {
        escuelaSesionBean.elimina(claveCentroTrabajo);
    }

    public void buscaColinas() {
        ColoniaDto coloniaDto = coloniaSesionBean.busca(escuelaModelo.getCodigoPostal());
        escuelaModelo.setEstado(coloniaDto.getEstado());
        escuelaModelo.setMunicipio(coloniaDto.getMunicipio());
        escuelaModelo.setCodigoPostalList(coloniaDto.getListaCodigoPostal());
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

    public int getMotivoBloqueo() {
        return motivoBloqueo;
    }

    public void setMotivoBloqueo(int motivoBloqueo) {
        this.motivoBloqueo = motivoBloqueo;
    }

    public List<MotivoBloqueoDto> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<MotivoBloqueoDto> motivos) {
        this.motivos = motivos;
    }
}
