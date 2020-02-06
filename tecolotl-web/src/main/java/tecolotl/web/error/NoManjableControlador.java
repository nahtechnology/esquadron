package tecolotl.web.error;

import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.Principal;
import java.util.logging.Logger;

@RequestScoped
@Named
public class NoManjableControlador {

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Inject
    private Principal principal;

    @Inject
    private Logger logger;

    private String rol;

    public void inicio() {
        rol = personaSesionBean.rol(principal.getName());
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}