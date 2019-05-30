package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.MotivoBloqueoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class EscuelaControlador {

    private List<MotivoBloqueoModelo> motivoBloqueoModeloLista;
    private MotivoBloqueoModelo motivoBloqueoModelo;
    private EscuelaBaseModelo escuelaBaseModelo;

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @PostConstruct
    public void init() {
        motivoBloqueoModeloLista = motivoBloqueoSesionBean.busca("Sin bloqueo");
        escuelaBaseModelo = new EscuelaBaseModelo();
    }

    public void bloquear() {
        escuelaSesionBean.bloqueo(escuelaBaseModelo, motivoBloqueoModelo);
    }

    public List<MotivoBloqueoModelo> getMotivoBloqueoModeloLista() {
        return motivoBloqueoModeloLista;
    }

    public void setMotivoBloqueoModeloLista(List<MotivoBloqueoModelo> motivoBloqueoModeloLista) {
        this.motivoBloqueoModeloLista = motivoBloqueoModeloLista;
    }

    public MotivoBloqueoModelo getMotivoBloqueoModelo() {
        return motivoBloqueoModelo;
    }

    public void setMotivoBloqueoModelo(MotivoBloqueoModelo motivoBloqueoModelo) {
        this.motivoBloqueoModelo = motivoBloqueoModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

}