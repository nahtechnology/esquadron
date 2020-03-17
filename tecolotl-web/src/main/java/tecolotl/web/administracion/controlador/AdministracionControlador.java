package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.AdministradorModelo;
import tecolotl.administracion.sesion.AdministradorSesionBean;
import tecolotl.nucleo.modelo.PersonaModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

@SessionScoped
@Named
public class AdministracionControlador implements Serializable {

    private AdministradorModelo administradorModelo;

    @Inject
    private AdministradorSesionBean administradorSesionBean;

    @PostConstruct
    public void inicio() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        administradorModelo = administradorSesionBean.busca(principal.getName(), true);
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public AdministradorModelo getAdministradorModelo() {
        return administradorModelo;
    }

    public void setAdministradorModelo(AdministradorModelo administradorModelo) {
        this.administradorModelo = administradorModelo;
    }

}
