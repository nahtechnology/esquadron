package tecolotl.web.error;

import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.Principal;

@RequestScoped
@Named
public class NoManjableControlador {

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Inject
    private Principal principal;

    private String rol;

    @PostConstruct
    public void inicio() {
        rol = personaSesionBean.rol(principal.getName());
    }

    public String principal() {
        return rol + "/dashboard.xhtml?faces-redirect=true";
    }
}