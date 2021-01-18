package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.CoordinadorSesionBean;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.GrupoSesionBean;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named(value = "administracionProfesorControlador")
public class ProfesorControlador implements Serializable {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private Logger logger;

    private String claveCentroTrabajo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private List<ProfesorModelo> profesorModeloLista;
    private ProfesorModelo profesorModelo;
    private UIInput uiInputApodo;
    private Long totalGrupos;

    @PostConstruct
    public void init() {
        profesorModelo = new ProfesorModelo();
    }

    public void inicio() {
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
        escuelaBaseModelo = new EscuelaBaseModelo(claveCentroTrabajo);
        escuelaBaseModelo.setGalaxia(escuelaSesionBean.galaxia(claveCentroTrabajo));
    }

    public void inserta() {
        if (grupoSesionBean.existeAlumnoProfesor(escuelaBaseModelo.getClaveCentroTrabajo(), profesorModelo.getApodo()) ||
                coordinadorSesionBean.exite(profesorModelo.getApodo(), escuelaBaseModelo.getClaveCentroTrabajo())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este apodo ya existe", null);
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), facesMessage);
        } else {
            profesorModelo.setEscuelaBaseModelo(escuelaBaseModelo);
            profesorSesionBean.inserta(profesorModelo);
            profesorModeloLista.add(profesorModelo);
            profesorModelo = new ProfesorModelo();
        }
    }

    public void actualiza() {
        profesorSesionBean.actualiza(profesorModelo);
        logger.info(profesorModelo.toString());
        profesorModelo = new ProfesorModelo();
    }

    public void buscaTotalGlumno() {
        totalGrupos = grupoSesionBean.totalPorProfesor(profesorModelo.getId());
    }

    public void elimina(){
       profesorSesionBean.elimina(profesorModelo.getId());
       profesorModeloLista.removeIf(profe -> profe.getId().compareTo(profesorModelo.getId()) == 0);
        profesorModelo = new ProfesorModelo();
    }

    public void limpiaModelo() {
        profesorModelo = new ProfesorModelo();
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }

    public UIInput getUiInputApodo() {
        return uiInputApodo;
    }

    public void setUiInputApodo(UIInput uiInputApodo) {
        this.uiInputApodo = uiInputApodo;
    }

    public Long getTotalGrupos() {
        return totalGrupos;
    }

    public void setTotalGrupos(Long totalGrupos) {
        this.totalGrupos = totalGrupos;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }
}
