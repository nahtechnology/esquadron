package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.vista.DetalleAlumno2Modelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.alumno.sesion.DetalleAlumnoSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;
import tecolotl.nucleo.modelo.PersonaActivaModelo;
import tecolotl.nucleo.modelo.UsuarioSesionModelo;
import tecolotl.nucleo.sesion.SesionControlSingleton;

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
import java.util.UUID;
import java.util.logging.Logger;

@SessionScoped
@Named
public class AlumnoControlador implements Serializable {

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private DetalleAlumnoSesionBean detalleAlumnoSesionBean;

    @Inject
    private HttpServletRequest httpServletRequest;

    @Inject
    private SesionControlSingleton sesionControlSingleton;

    private AlumnoModelo alumnoModelo;
    private List<DetalleAlumno2Modelo> detalleAlumno2ModeloLista;
    private List<TareaActividadModelo> tareaActvidadModeloLista;
    private TareaActividadModelo tareaActividadModelo;
    private DetalleAlumno2Modelo detalleAlumno2Modelo;
    private ActividadModelo actividadModeloBibliotecaLibre;
    private PersonaActivaModelo personaActivaModelo;
    private String idGrupoSeleccionado;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Principal principal = externalContext.getUserPrincipal();
        alumnoModelo = alumnoSesionBean.busca(principal.getName(), true);
        personaActivaModelo = alumnoSesionBean.activo(alumnoModelo.getId());
        detalleAlumno2ModeloLista = detalleAlumnoSesionBean.busca(alumnoModelo.getId());
        if (!detalleAlumno2ModeloLista.isEmpty()) { detalleAlumno2Modelo = detalleAlumno2ModeloLista.get(0); }
        if (personaActivaModelo.isBloqueado()) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/sin-permiso.xhtml?tipo=alumno");
                externalContext.invalidateSession();
            } catch (IOException ioException) {

            }
        } else {
            buscaTareas(principal);
        }
    }

    public void buscaTareas(Principal principal) {
        tareaActvidadModeloLista = tareaSesionBean.buscaActividad(alumnoModelo.getId(), detalleAlumno2Modelo == null ? "" : detalleAlumno2Modelo.getIdGrupo());
        if (principal != null) {
            String datosUsuario[] = principal.getName().split(",");
            sesionControlSingleton.inicio(httpServletRequest.getRequestedSessionId(),
                    Integer.parseInt(datosUsuario[1]),
                    datosUsuario[0],
                    UsuarioSesionModelo.Tipo.ALUMNO,
                    new Date(),
                    alumnoModelo.getId());
        }
    }

    public String seleccion(TareaActividadModelo tareaActividadModelo){
        this.tareaActividadModelo = tareaActividadModelo;
        tareaActividadModelo.agregarDatos(tareaSesionBean.detalles(tareaActividadModelo.getId()));
        return "transcript";
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public String actividadBiliotecaLibre(String idActividad) {
        actividadModeloBibliotecaLibre = new ActividadModelo(idActividad);
        return "actividad";
    }

    public void buscaEscuela() {
        detalleAlumno2Modelo = detalleAlumno2ModeloLista.stream().filter(detalle -> detalle.getIdGrupo().equals(idGrupoSeleccionado)).findFirst().get();
        buscaTareas(null);
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }

    public List<TareaActividadModelo> getTareaActvidadModeloLista() {
        return tareaActvidadModeloLista;
    }

    public void setTareaActvidadModeloLista(List<TareaActividadModelo> tareaActvidadModeloLista) {
        this.tareaActvidadModeloLista = tareaActvidadModeloLista;
    }

    public TareaActividadModelo getTareaActividadModelo() {
        return tareaActividadModelo;
    }

    public void setTareaActividadModelo(TareaActividadModelo tareaActividadModelo) {
        this.tareaActividadModelo = tareaActividadModelo;
    }

    public ActividadModelo getActividadModeloBibliotecaLibre() {
        return actividadModeloBibliotecaLibre;
    }

    public void setActividadModeloBibliotecaLibre(ActividadModelo actividadModeloBibliotecaLibre) {
        this.actividadModeloBibliotecaLibre = actividadModeloBibliotecaLibre;
    }

    public PersonaActivaModelo getPersonaActivaModelo() {
        return personaActivaModelo;
    }

    public void setPersonaActivaModelo(PersonaActivaModelo personaActivaModelo) {
        this.personaActivaModelo = personaActivaModelo;
    }

    public List<DetalleAlumno2Modelo> getDetalleAlumno2ModeloLista() {
        return detalleAlumno2ModeloLista;
    }

    public void setDetalleAlumno2ModeloLista(List<DetalleAlumno2Modelo> detalleAlumno2ModeloLista) {
        this.detalleAlumno2ModeloLista = detalleAlumno2ModeloLista;
    }

    public DetalleAlumno2Modelo getDetalleAlumno2Modelo() {
        return detalleAlumno2Modelo;
    }

    public void setDetalleAlumno2Modelo(DetalleAlumno2Modelo detalleAlumno2Modelo) {
        this.detalleAlumno2Modelo = detalleAlumno2Modelo;
    }

    public String getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public void setIdGrupoSeleccionado(String idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
    }
}
