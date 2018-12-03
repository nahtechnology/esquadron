package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@RequestScoped
public class EscuelaControlador {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    public Collection<EscuelaDto> obtener() {
        Collection<EscuelaDto> escuelaDtoCollection = escuelaSesionBean.busca();
        return escuelaDtoCollection;
    }
}
