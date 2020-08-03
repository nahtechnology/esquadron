package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.nucleo.modelo.PersonaMotivoBloqueoModelo;
import tecolotl.nucleo.sesion.PersonaMoitvoBloqueoSesionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;
import tecolotl.web.controlador.TablaControlador;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.CollectionDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CoordinadorControlador implements Serializable {

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private Logger logger;

    private List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista;
    private List<CoordinadorModelo> coordinadorModeloLista;
    private CoordinadorModelo coordinadorModelo;
    private PersonaMotivoBloqueoModelo personaMotivoBloqueoModelo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private String claveCentroTrabajo;
    private UIInput uiInput;

    public void inicio() {
        escuelaBaseModelo = new EscuelaBaseModelo(claveCentroTrabajo);
        escuelaBaseModelo.setGalaxia(escuelaSesionBean.galaxia(claveCentroTrabajo));
        coordinadorModeloLista = coordinadorSesionBean.busca(claveCentroTrabajo);
        limpiaCoordinador();
    }

    public void agregar() {
        if (grupoSesionBean.existeAlumnoProfesor(escuelaBaseModelo.getClaveCentroTrabajo(), coordinadorModelo.getApodo()) ||
                coordinadorSesionBean.exite(coordinadorModelo.getApodo(), escuelaBaseModelo.getClaveCentroTrabajo())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El apodo: " + coordinadorModelo.getApodo() + " ya está registrado", null);
            facesContext.addMessage(uiInput.getClientId(facesContext), facesMessage);
        } else {
            coordinadorModelo.setClaveCentroTrabajo(escuelaBaseModelo.getClaveCentroTrabajo());
            coordinadorSesionBean.agreaga(coordinadorModelo);
            coordinadorModeloLista = coordinadorSesionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo());
            limpiaCoordinador();
        }
    }

    public void elimina() {
        coordinadorModelo.setClaveCentroTrabajo(escuelaBaseModelo.getClaveCentroTrabajo());
        coordinadorSesionBean.elimina(coordinadorModelo);
        coordinadorModeloLista.remove(coordinadorModelo);
        limpiaCoordinador();
    }

    public void actualiza() {
        coordinadorModelo.setClaveCentroTrabajo(escuelaBaseModelo.getClaveCentroTrabajo());
        coordinadorSesionBean.actualiza(coordinadorModelo);
        coordinadorModeloLista.remove(coordinadorModelo);
        coordinadorModeloLista.add(coordinadorModelo);
        limpiaCoordinador();
    }

    public void limpiaCoordinador() {
        coordinadorModelo = new CoordinadorModelo();
    }

    public CoordinadorModelo getCoordinadorModelo() {
        return coordinadorModelo;
    }

    public void setCoordinadorModelo(CoordinadorModelo coordinadorModelo) {
        this.coordinadorModelo = coordinadorModelo;
    }

    public List<PersonaMotivoBloqueoModelo> getPersonaMotivoBloqueoModeloLista() {
        return personaMotivoBloqueoModeloLista;
    }

    public void setPersonaMotivoBloqueoModeloLista(List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista) {
        this.personaMotivoBloqueoModeloLista = personaMotivoBloqueoModeloLista;
    }

    public PersonaMotivoBloqueoModelo getPersonaMotivoBloqueoModelo() {
        return personaMotivoBloqueoModelo;
    }

    public void setPersonaMotivoBloqueoModelo(PersonaMotivoBloqueoModelo personaMotivoBloqueoModelo) {
        this.personaMotivoBloqueoModelo = personaMotivoBloqueoModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public List<CoordinadorModelo> getCoordinadorModeloLista() {
        return coordinadorModeloLista;
    }

    public void setCoordinadorModeloLista(List<CoordinadorModelo> coordinadorModeloLista) {
        this.coordinadorModeloLista = coordinadorModeloLista;
    }

    public UIInput getUiInput() {
        return uiInput;
    }

    public void setUiInput(UIInput uiInput) {
        this.uiInput = uiInput;
    }
}
