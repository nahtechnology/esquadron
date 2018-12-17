package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EscuelaDetalleControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private EscuelaSesionBean escuelaSesionBean;
    private EscuelaDetalleDto escuelaDetalleDto;

    @PostConstruct
    public void init() {
        Map<String, String> paramentros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        escuelaDetalleDto = escuelaSesionBean.busca(paramentros.get("claveCentroTrabajo"));
    }

    public EscuelaDetalleDto getEscuelaDetalleDto() {
        return escuelaDetalleDto;
    }

    public void setEscuelaDetalleDto(EscuelaDetalleDto escuelaDetalleDto) {
        this.escuelaDetalleDto = escuelaDetalleDto;
    }

}
