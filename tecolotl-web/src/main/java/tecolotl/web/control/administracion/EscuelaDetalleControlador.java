package tecolotl.web.control.administracion;

import tecolotl.administracion.dto.ContactoDto;
import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.dto.TipoContactoDto;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.sesion.TipoContactoSesionBean;
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

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    @Inject
    private ContactoSesionBean contactoSesionBean;

    private EscuelaDetalleDto escuelaDetalleDto;
    private ContactoDto contactoDtoModelo;
    private int tipoContacto;
    private List<TipoContactoDto> tipoContactoDtoLista;

    @PostConstruct
    public void init() {
        Map<String, String> paramentros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        escuelaDetalleDto = escuelaSesionBean.busca(paramentros.get("claveCentroTrabajo"));
        tipoContactoDtoLista = tipoContactoSesionBean.busca();
        contactoDtoModelo = new ContactoDto();

    }

    public void insertar() {
        ContactoDto contactoDto =
                contactoSesionBean.inserta(escuelaDetalleDto.getClaveCentroTrabajo(), tipoContacto, contactoDtoModelo.getNombre(), contactoDtoModelo.getTelefono());
        escuelaDetalleDto.getContactoDtoList().add(contactoDto);
        contactoDtoModelo = new ContactoDto();
    }

    public EscuelaDetalleDto getEscuelaDetalleDto() {
        return escuelaDetalleDto;
    }

    public void setEscuelaDetalleDto(EscuelaDetalleDto escuelaDetalleDto) {
        this.escuelaDetalleDto = escuelaDetalleDto;
    }

    public ContactoDto getContactoDtoModelo() {
        return contactoDtoModelo;
    }

    public void setContactoDtoModelo(ContactoDto contactoDtoModelo) {
        this.contactoDtoModelo = contactoDtoModelo;
    }

    public int getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(int tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public List<TipoContactoDto> getTipoContactoDtoLista() {
        return tipoContactoDtoLista;
    }

    public void setTipoContactoDtoLista(List<TipoContactoDto> tipoContactoDtoLista) {
        this.tipoContactoDtoLista = tipoContactoDtoLista;
    }
}
