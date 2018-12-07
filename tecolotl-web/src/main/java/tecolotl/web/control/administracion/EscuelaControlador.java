package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@RequestScoped
public class EscuelaControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private Collection<EscuelaDto> escuelas;

    @PostConstruct
    public void init() {
        escuelas = escuelaSesionBean.busca();
    }

    public Collection<EscuelaDto> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(Collection<EscuelaDto> escuelas) {
        this.escuelas = escuelas;
    }
}
