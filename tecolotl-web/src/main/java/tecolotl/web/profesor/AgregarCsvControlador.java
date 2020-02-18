package tecolotl.web.profesor;

import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.sesion.NivelLenguajeSesionBean;
import tecolotl.nucleo.sesion.PersonaSesionBean;
import tecolotl.profesor.scope.AlumnoGrupoScope;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class AgregarCsvControlador implements Serializable {

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private AlumnoGrupoScope alumnoGrupoScope;

    @Inject
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private Logger logger;

    private int totalAlumno;
    private int alumnosAsignado;
    private List<String> apodos;
    private AlumnoModelo alumnoModelo;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;

    private UIInput uiInputApodo;

    @PostConstruct
    public void inicio() {
        alumnoModelo = new AlumnoModelo();
        nivelLenguajeModeloLista = nivelLenguajeSesionBean.busca();
        totalAlumno = licenciaSesionBean.totalAlumno(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo());
        alumnosAsignado = cicloEscolarSessionBean.totalAlumnos(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo()).intValue();
        apodos = personaSesionBean.buscaApodo(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo());
    }

    public void inserta(AjaxBehaviorEvent ajaxBehaviorEvent) throws AbortProcessingException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (grupoSesionBean.existeAlumnoProfesor(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo(), alumnoModelo.getApodo())) {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
            facesMessage.setSummary("ya existe este apodo");
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), facesMessage);
        } else {
            alumnoGrupoScope.inserta(alumnoModelo, profesorGrupoControlador.getGrupoModelo().getId());
            alumnoModelo = new AlumnoModelo();
        }
        this.alumnosAsignado++;
    }

    public void actualizar(AjaxBehaviorEvent ajaxBehaviorEvent) throws AbortProcessingException {
        apodos = personaSesionBean.buscaApodo(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo());
    }

    public int getTotalAlumno() {
        return totalAlumno;
    }

    public void setTotalAlumno(int totalAlumno) {
        this.totalAlumno = totalAlumno;
    }

    public int getAlumnosAsignado() {
        return alumnosAsignado;
    }

    public void setAlumnosAsignado(int alumnosAsignado) {
        this.alumnosAsignado = alumnosAsignado;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<String> getApodos() {
        return apodos;
    }

    public void setApodos(List<String> apodos) {
        this.apodos = apodos;
    }

    public UIInput getUiInputApodo() {
        return uiInputApodo;
    }

    public void setUiInputApodo(UIInput uiInputApodo) {
        this.uiInputApodo = uiInputApodo;
    }

    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }
}
