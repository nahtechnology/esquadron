package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.GrupoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named
public class AgregarControlador implements Serializable {

    private String escuela;
    private String persona;
    private EscuelaBaseModelo escuelaBaseModelo;
    private ProfesorModelo profesorModelo;
    private CoordinadorModelo coordinadorModelo;
    private UIInput uiInputProfesor;
    private UIInput uiInputCoordinador;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private Logger logger;

    public void inicio() {
        escuelaBaseModelo = new EscuelaBaseModelo(escuela);
        switch (persona) {
            case "profesor" :
                profesorModelo = new ProfesorModelo();
                break;
            case "coordinador" :
                coordinadorModelo = new CoordinadorModelo();
                break;
            default :
                logger.severe("No existe para la persona: " + persona);
                break;
        }
    }

    public String agregar() {
        if (grupoSesionBean.existeAlumnoProfesor(escuelaBaseModelo.getClaveCentroTrabajo(), profesorModelo == null ? coordinadorModelo.getApodo() :  profesorModelo.getApodo()) ||
                coordinadorSesionBean.exite(profesorModelo == null ? coordinadorModelo.getApodo() :  profesorModelo.getApodo(), escuelaBaseModelo.getClaveCentroTrabajo())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este apodo ya existe", null);
            facesContext.addMessage(profesorModelo == null ? uiInputCoordinador.getClientId(facesContext) : uiInputProfesor.getClientId(facesContext), facesMessage );
            return null;
        } else {
            if (profesorModelo != null) {
                profesorModelo.setEscuelaBaseModelo(escuelaBaseModelo);
                profesorSesionBean.inserta(profesorModelo);
            } else {
                coordinadorModelo.setClaveCentroTrabajo(escuelaBaseModelo.getClaveCentroTrabajo());
                coordinadorSesionBean.agreaga(coordinadorModelo);
            }
            return "success";
        }
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public CoordinadorModelo getCoordinadorModelo() {
        return coordinadorModelo;
    }

    public void setCoordinadorModelo(CoordinadorModelo coordinadorModelo) {
        this.coordinadorModelo = coordinadorModelo;
    }

    public UIInput getUiInputProfesor() {
        return uiInputProfesor;
    }

    public void setUiInputProfesor(UIInput uiInputProfesor) {
        this.uiInputProfesor = uiInputProfesor;
    }

    public UIInput getUiInputCoordinador() {
        return uiInputCoordinador;
    }

    public void setUiInputCoordinador(UIInput uiInputCoordinador) {
        this.uiInputCoordinador = uiInputCoordinador;
    }
}
