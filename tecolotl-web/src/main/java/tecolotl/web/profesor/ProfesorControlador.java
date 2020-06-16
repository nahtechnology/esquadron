package tecolotl.web.profesor;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.nucleo.modelo.UsuarioSesionModelo;
import tecolotl.nucleo.sesion.SesionControlSingleton;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@Named
public class ProfesorControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private SesionControlSingleton sesionControlSingleton;

    @Inject
    private HttpServletRequest httpServletRequest;

    private ProfesorModelo profesorModelo;

    @PostConstruct
    public void inicio() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        profesorModelo = profesorSesionBean.busca(principal.getName(), true);
        String cadena[] = principal.getName().split(",");
        sesionControlSingleton.inicio(httpServletRequest.getRequestedSessionId(),
                Integer.parseInt(cadena[1]),
                cadena[0],
                UsuarioSesionModelo.Tipo.PROFESOR,
                new Date(),
                profesorModelo.getId());
    }

    public String cerrarSesion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

}
