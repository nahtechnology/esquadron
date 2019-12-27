package tecolotl.web.profesor;

import tecolotl.administracion.sesion.LicenciaSesionBean;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.sesion.NivelLenguajeSesionBean;
import tecolotl.profesor.modelo.TareaAlumnoGrupoModelo;
import tecolotl.profesor.scope.AlumnoGrupoScope;
import tecolotl.profesor.sesion.CicloEscolarSessionBean;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class GrupoAlumnoControlador implements Serializable {

    @Inject
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private AlumnoGrupoScope alumnoGrupoScope;

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private ProfesorGrupoControlador profesorGrupoControlador;

    @Inject
    private LicenciaSesionBean licenciaSesionBean;

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private Logger logger;

    private Integer idGrupo;
    private List<TareaAlumnoGrupoModelo> tareaAlumnoGrupoModeloLista;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private AlumnoModelo alumnoModelo;
    private int totalAlumno;
    private int alumnosAsignado;

    private UIInput uiInputApodo;

    @PostConstruct
    public void inicio() {
        nivelLenguajeModeloLista = nivelLenguajeSesionBean.busca();
        alumnoModelo = new AlumnoModelo();
        alumnoModelo.setNivelLenguajeModelo(new NivelLenguajeModelo());
        totalAlumno = licenciaSesionBean.busca(new Date(), profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo()).getAlumnos();
        buscaTotalAlumno();
    }

    public void insertaAlumno() throws IOException {
        logger.info(alumnoModelo.toString());
        FacesMessage facesMessage = new FacesMessage();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (grupoSesionBean.existeAlumno(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo(), alumnoModelo.getApodo())) {
            facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
            facesMessage.setSummary("ya existe este apodo");
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), facesMessage);
        } else {
            alumnoGrupoScope.inserta(alumnoModelo, idGrupo);
            alumnoModelo = new AlumnoModelo();
            buscaDetalleAlumnos();
            buscaTotalAlumno();
        }
    }

    public void buscaDetalleAlumnos() throws IOException {
        if (grupoSesionBean.pertenece(profesorControlador.getProfesorModelo().getId(), idGrupo)) {
            tareaAlumnoGrupoModeloLista = grupoAlumnoSesionBean.busca(idGrupo);
            profesorGrupoControlador.detalleGrupo(idGrupo);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("no-grupo.xhtml");
        }
    }

    public void validaParametro() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && facesContext.isValidationFailed()) {
            facesContext.getExternalContext().redirect("no-grupo.xhtml");
        }
    }

    public void buscaTotalAlumno() {
        alumnosAsignado = cicloEscolarSessionBean.totalAlumnos(profesorControlador.getProfesorModelo().getEscuelaBaseModelo().getClaveCentroTrabajo()).intValue();
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<TareaAlumnoGrupoModelo> getTareaAlumnoGrupoModeloLista() {
        return tareaAlumnoGrupoModeloLista;
    }

    public void setTareaAlumnoGrupoModeloLista(List<TareaAlumnoGrupoModelo> tareaAlumnoGrupoModeloLista) {
        this.tareaAlumnoGrupoModeloLista = tareaAlumnoGrupoModeloLista;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<NivelLenguajeModelo> getNivelLenguajeModeloLista() {
        return nivelLenguajeModeloLista;
    }

    public void setNivelLenguajeModeloLista(List<NivelLenguajeModelo> nivelLenguajeModeloLista) {
        this.nivelLenguajeModeloLista = nivelLenguajeModeloLista;
    }

    public UIInput getUiInputApodo() {
        return uiInputApodo;
    }

    public void setUiInputApodo(UIInput uiInputApodo) {
        this.uiInputApodo = uiInputApodo;
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
}
