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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    private Set<EscuelaDto> escuelas;
    private List<MotivoBloqueoDto> motivos;
    private EscuelaModelo escuelaModelo;
    private int motivoBloqueo;

    @PostConstruct
    public void init() {
        escuelas = new TreeSet<>(escuelaSesionBean.busca());
        escuelaModelo = new EscuelaModelo();
        motivos = motivoBloqueoSesionBean.motivoBloque();
    }

    public void inserta() {
        logger.info(escuelaModelo.getClaveCentroTrabajo());
        EscuelaDto escuelaDto = escuelaSesionBean.insertar(escuelaModelo.getClaveCentroTrabajo(),
                Integer.parseInt(escuelaModelo.getIdColonia()), escuelaModelo.getNombre(), escuelaModelo.getCalle(),
                escuelaModelo.getNumeroInterior(), escuelaModelo.getNumeroExterior());
        escuelas.add(escuelaDto);
        escuelaModelo = new EscuelaModelo();
    }

    public void bloquear(String claveCentroTrabajo , boolean activo) {
        EscuelaDto escuelaDto = ((TreeSet<EscuelaDto>)escuelas).floor(new EscuelaDto(claveCentroTrabajo));
        escuelaDto.setEstatus(!activo);
        if (activo) {
            escuelaSesionBean.bloqueo(claveCentroTrabajo, motivoBloqueo);
        } else {
            escuelaSesionBean.bloqueo(claveCentroTrabajo, 0);
        }
    }

    public void actualiza(String claveCentroTrabajo) {
        EscuelaDetalleDto escuelaDetalleDto = escuelaSesionBean.busca(claveCentroTrabajo);
        escuelaModelo = new EscuelaModelo(escuelaDetalleDto);
    }

    public void eliminar(String claveCentroTrabajo) {
        EscuelaDto escuelaDto = escuelaSesionBean.elimina(claveCentroTrabajo);
        escuelas.remove(escuelaDto);
    }

    public void buscaColinas() {
        ColoniaDto coloniaDto = coloniaSesionBean.busca(escuelaModelo.getCodigoPostal());
        escuelaModelo.setEstado(coloniaDto.getEstado());
        escuelaModelo.setMunicipio(coloniaDto.getMunicipio());
        escuelaModelo.setCodigoPostalList(coloniaDto.getListaCodigoPostal());
    }

    public Set<EscuelaDto> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(Set<EscuelaDto> escuelas) {
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
