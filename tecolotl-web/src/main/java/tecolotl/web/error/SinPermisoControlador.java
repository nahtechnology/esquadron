package tecolotl.web.error;

import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.Principal;

@RequestScoped
@Named
public class SinPermisoControlador {

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Resource
    private Principal principal;

    @PostConstruct
    public void inicio() {

    }

}
