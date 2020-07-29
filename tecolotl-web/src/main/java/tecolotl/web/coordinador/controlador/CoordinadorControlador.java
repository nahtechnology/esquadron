package tecolotl.web.coordinador.controlador;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.nucleo.modelo.PersonaActivaModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

@SessionScoped
@Named(value = "coordinadorControladorLocal")
public class CoordinadorControlador implements Serializable {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    private CoordinadorModelo coordinadorModelo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private PersonaActivaModelo personaActivaModelo;

    @PostConstruct
    public void inicio() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Principal principal = externalContext.getUserPrincipal();
        String busqueda[] = principal.getName().split(",");
        coordinadorModelo = coordinadorSesionBean.busca(Integer.parseInt(busqueda[1]), busqueda[0]);
        escuelaBaseModelo = escuelaSesionBean.busca(Integer.parseInt(busqueda[1]));
        personaActivaModelo = coordinadorSesionBean.activo(coordinadorModelo.getContador(), coordinadorModelo.getClaveCentroTrabajo());
        if (personaActivaModelo.isBloqueado()) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/sin-permiso.xhtml?tipo=coordinador");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public CoordinadorModelo getCoordinadorModelo() {
        return coordinadorModelo;
    }

    public void setCoordinadorModelo(CoordinadorModelo coordinadorModelo) {
        this.coordinadorModelo = coordinadorModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public PersonaActivaModelo getPersonaActivaModelo() {
        return personaActivaModelo;
    }

    public void setPersonaActivaModelo(PersonaActivaModelo personaActivaModelo) {
        this.personaActivaModelo = personaActivaModelo;
    }
}
