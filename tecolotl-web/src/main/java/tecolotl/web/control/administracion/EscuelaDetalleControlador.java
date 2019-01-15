package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.ContactoDto;
import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.modelo.administracion.EscuelaModelo;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EscuelaDetalleControlador implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    private Set<ContactoDto> contactoDtoSet;


    @Inject
    private EscuelaSesionBean escuelaSesionBean;
    private EscuelaDetalleDto escuelaDetalleDto;
    private ContactoDto contactoDto;
    private EscuelaModelo escuelaModelo;

    @Inject
    private ContactoSesionBean contactoSesionBean;

    @PostConstruct
    public void init() {
        Map<String, String> paramentros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        escuelaDetalleDto = escuelaSesionBean.busca(paramentros.get("claveCentroTrabajo"));
        contactoDto = new ContactoDto();
        escuelaModelo = new EscuelaModelo();

    }

    public EscuelaDetalleDto getEscuelaDetalleDto() {
        return escuelaDetalleDto;
    }

    public void setEscuelaDetalleDto(EscuelaDetalleDto escuelaDetalleDto) {
        this.escuelaDetalleDto = escuelaDetalleDto;
    }

    public void inserta(){
        logger.info(escuelaModelo.getClaveCentroTrabajo());
        ContactoDto contactoDtoss = contactoSesionBean.inserta(escuelaDetalleDto.getClaveCentroTrabajo(),Integer.parseInt(contactoDto.getTipo()),contactoDto.getNombre(),contactoDto.getTelefono());
        contactoDtoSet.add(contactoDtoss);
        contactoDto = new ContactoDto();

    }

}
