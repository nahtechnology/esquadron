package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.TipoContactoModelo;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.sesion.TipoContactoSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ContactoControlador extends TablaControlador implements Serializable {

    private List<TipoContactoModelo> tipoContactoModeloLista;
    private TipoContactoModelo tipoContactoModelo;
    private ContactoModelo contactoModelo;
    private String claveCentroTrabajo;

    @Inject
    private ContactoSesionBean contactoSesionBean;

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    @PostConstruct
    public void init() {
        claveCentroTrabajo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("escuela");
        setCollectionDataModel(new CollectionDataModel(contactoSesionBean.busca(claveCentroTrabajo)));
        tipoContactoModeloLista = tipoContactoSesionBean.busca();
        contactoModelo = new ContactoModelo();
        tipoContactoModelo = new TipoContactoModelo();
    }

    public void agregar() {
        contactoModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        contactoModelo.setTipoContactoModelo(tipoContactoModelo);
        contactoSesionBean.inserta(contactoModelo);
    }

    public List<TipoContactoModelo> getTipoContactoModeloLista() {
        return tipoContactoModeloLista;
    }

    public void setTipoContactoModeloLista(List<TipoContactoModelo> tipoContactoModeloLista) {
        this.tipoContactoModeloLista = tipoContactoModeloLista;
    }

    public TipoContactoModelo getTipoContactoModelo() {
        return tipoContactoModelo;
    }

    public void setTipoContactoModelo(TipoContactoModelo tipoContactoModelo) {
        this.tipoContactoModelo = tipoContactoModelo;
    }

    public ContactoModelo getContactoModelo() {
        return contactoModelo;
    }

    public void setContactoModelo(ContactoModelo contactoModelo) {
        this.contactoModelo = contactoModelo;
    }
}
