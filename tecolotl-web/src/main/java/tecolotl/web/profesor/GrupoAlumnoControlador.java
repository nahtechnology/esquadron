package tecolotl.web.profesor;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.sesion.NivelLenguajeSesionBean;
import tecolotl.profesor.modelo.TareaAlumnoGrupoModelo;
import tecolotl.profesor.scope.AlumnoGrupoScope;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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

    private Integer idGrupo;
    private List<TareaAlumnoGrupoModelo> tareaAlumnoGrupoModeloLista;
    private List<NivelLenguajeModelo> nivelLenguajeModeloLista;
    private AlumnoModelo alumnoModelo;

    private UIInput uiInputApodo;

    @PostConstruct
    public void inicio() {
        nivelLenguajeModeloLista = nivelLenguajeSesionBean.busca();
        alumnoModelo = new AlumnoModelo();
        alumnoModelo.setNivelLenguajeModelo(new NivelLenguajeModelo());
    }

    public void insertaAlumno() {
        FacesMessage facesMessage = new FacesMessage();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (grupoSesionBean.existeAlumno(profesorControlador.getEscuelaBaseModelo().getClaveCentroTrabajo(), alumnoModelo.getApodo())) {
            facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
            facesMessage.setSummary("ya existe este apodo");
            facesContext.addMessage(uiInputApodo.getClientId(facesContext), facesMessage);
        } else {
            alumnoGrupoScope.inserta(alumnoModelo, idGrupo);
            alumnoModelo = new AlumnoModelo();
            alumnoModelo.setNivelLenguajeModelo(new NivelLenguajeModelo());
            buscaDetalleAlumnos();
        }
    }

    public void buscaDetalleAlumnos() {

        tareaAlumnoGrupoModeloLista = grupoAlumnoSesionBean.busca(idGrupo);
        profesorGrupoControlador.detalleGrupo(idGrupo);
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
}
