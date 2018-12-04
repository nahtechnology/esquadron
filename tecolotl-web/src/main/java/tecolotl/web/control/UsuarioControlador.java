package tecolotl.web.control;

import tecolotl.nucleo.sesion.AdministradorSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.Principal;

@Named
@SessionScoped
public class UsuarioControlador {

    private String nombre;

    @Inject
    private AdministradorSesionBean administradorSesionBean;

    @PostConstruct
    public void init() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        nombre = administradorSesionBean.busca(principal.getName());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
