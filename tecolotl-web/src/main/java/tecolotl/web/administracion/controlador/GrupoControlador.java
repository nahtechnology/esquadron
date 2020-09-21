package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class GrupoControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private ReasignaAlumnoSesionBean reasignaAlumnoSesionBean;

    @Inject
    private Logger logger;

    private Map<Integer, CicloEscolarModelo> cicloEscolarModeloMapa;
    private List<GrupoModelo> grupoModeloLista;
    private List<GrupoModelo> grupoModeloPorProfesorLista;
    private List<ProfesorModelo> profesorModeloLista;
    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private List<AlumnoModelo> alumnoModeloLista;
    private GrupoModelo grupoModelo;
    private CicloEscolarModelo cicloEscolarModelo;
    private Integer cicloEscolar;
    private ProfesorModelo profesorModelo;
    private String alumnoIdReasignar;
    private String profesorIdReasignar;
    private Integer cicloEscolarIdReasignar;
    private String grupoIdReasignar;
    private EscuelaBaseModelo escuelaBaseModelo;

    private String profesor;
    private String claveCentroTrabajo;

    private HtmlInputText htmlInputText;

    @PostConstruct
    public void init() {
        grupoModelo = new GrupoModelo();
        profesorModelo = new ProfesorModelo();
        escuelaBaseModelo = new EscuelaBaseModelo();
    }

    public void inicio() {
        profesorModelo.setId(UUID.fromString(profesor));
        escuelaBaseModelo.setClaveCentroTrabajo(claveCentroTrabajo);
        cicloEscolarModeloMapa = cicloEscolarSessionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo(), true, profesorModelo.getId())
                .stream().collect(Collectors.toMap(c -> c.hashCode(), Function.identity()));
    }

    public void busca() {
        if (cicloEscolar != null) {
            cicloEscolarModelo = cicloEscolarModeloMapa.get(cicloEscolar);
            grupoModeloLista = grupoSesionBean.busca(cicloEscolarModelo.getInicio(), cicloEscolarModelo.getFin(), escuelaBaseModelo.getClaveCentroTrabajo(), profesorModelo.getId());
        } else {
            grupoModeloLista = null;
        }
    }

    public void agrega() {
        if (grupoModeloLista.stream().anyMatch(grupoModeloPredicate())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.validationFailed();
            facesContext.addMessage(
                    htmlInputText.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El grupo: " + grupoModelo.getGrado().toString() + " " + grupoModelo.getGrupo().toString() + " ya existe", null));
        } else {
            grupoModelo.setCicloEscolarModelo(cicloEscolarModelo);
            grupoModelo.setIdProfesor(profesorModelo.getId());
            grupoSesionBean.inserta(grupoModelo);
            grupoModelo.setCicloEscolarModelo(null);
            grupoModeloLista.add(grupoModelo);
            grupoModelo = new GrupoModelo();
        }
    }

    public void actualiza(AjaxBehaviorEvent ajaxBehaviorEvent) {
        if (grupoModeloLista.stream().anyMatch(grupoModeloPredicate())) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.validationFailed();
            facesContext.addMessage(
                    htmlInputText.getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El grupo: " + grupoModelo.getGrado().toString() + " " + grupoModelo.getGrupo().toString() + " ya existe", null));
        } else {
            grupoSesionBean.actualiza(grupoModelo);
            Optional<GrupoModelo> optionalGrupoModelo = grupoModeloLista.stream().filter(grupo -> grupo.getId().equals(grupoModelo.getId())).findFirst();
            optionalGrupoModelo.ifPresent(grupo -> {grupo.setGrado(grupoModelo.getGrado()); grupo.setGrupo(grupoModelo.getGrupo());});
            grupoModelo = new GrupoModelo();
        }
    }

    public void totalAlumnos() {
        grupoModelo.setTotalAlumno(grupoAlumnoSesionBean.buscaTotalAlumnosGrupo(grupoModelo.getId()).intValue());
    }

    public void limpiaGrupo() {
        grupoModelo = new GrupoModelo();
    }

    public void borrar() {
        grupoSesionBean.elimina(grupoModelo.getId());
        grupoModeloLista.remove(grupoModelo);
        grupoModelo = new GrupoModelo();
    }

    public void buscaProfesor() {
        if (profesorModeloLista == null || profesorModeloLista.isEmpty()) {
            profesorModeloLista = profesorSesionBean.buscaPorEscuela(escuelaBaseModelo.getClaveCentroTrabajo());
        }
        buscaGrupo();
    }

    public void buscaGrupo() {
        if (profesorIdReasignar == null) profesorIdReasignar = profesorModeloLista == null || profesorModeloLista.isEmpty() ? null : profesorModeloLista.get(0).getId().toString();
        if (cicloEscolarIdReasignar == null) cicloEscolarIdReasignar = cicloEscolarModeloLista == null || cicloEscolarModeloLista.isEmpty() ? null : cicloEscolarModeloLista.get(0).hashCode();
        if (profesorIdReasignar != null && cicloEscolarIdReasignar != null) {
            grupoModeloPorProfesorLista = grupoSesionBean.busca(UUID.fromString(profesorIdReasignar), busca(cicloEscolarIdReasignar))
                    .stream().filter(g -> g.getId().compareTo(grupoModelo.getId()) != 0).collect(Collectors.toList());
        }
    }

    public void buscaCicloEscolar() {
        if (cicloEscolarModeloLista == null || cicloEscolarModeloLista.isEmpty()) {
            cicloEscolarModeloLista =
                    cicloEscolarSessionBean.busca(escuelaBaseModelo.getClaveCentroTrabajo()).stream().filter(CicloEscolarModelo::getActivo).collect(Collectors.toList());
        }
    }

    private CicloEscolarModelo busca(int hashCode) {
        for (CicloEscolarModelo cicloEscolarModelo1 : cicloEscolarModeloLista) {
            if (cicloEscolarModelo1.hashCode() == hashCode) {
                return cicloEscolarModelo1;
            }
        }
        return null;
    }

    public void cambiaGrupo() {
        grupoSesionBean.reasignar(grupoModelo.getId(), UUID.fromString(profesorIdReasignar), busca(cicloEscolarIdReasignar));
        grupoModeloLista.removeIf(grupo -> grupo.getId().compareTo(grupoModelo.getId()) == 0);
    }

    public void cambiarAlumno() {
        logger.info(alumnoIdReasignar);
    }

    private Predicate<GrupoModelo> grupoModeloPredicate() {
        Predicate<GrupoModelo> predicateGrupo = p -> p.getGrupo().compareTo(grupoModelo.getGrupo()) == 0;
        Predicate<GrupoModelo> predicateGrado = p -> p.getGrado().compareTo(grupoModelo.getGrado()) == 0;
        return predicateGrupo.and(predicateGrado);
    }

    public void buscaGrupoPorProfesor() {
        grupoModeloPorProfesorLista = grupoSesionBean.busca(UUID.fromString(profesorIdReasignar));
    }

    public void buscaAlumno() {
        alumnoModeloLista = grupoAlumnoSesionBean.buscaAlumno(grupoModelo.getId());
    }

    public void reasignarAlumno() {
        reasignaAlumnoSesionBean.reasigar(grupoModelo.getId(), UUID.fromString(grupoIdReasignar), UUID.fromString(alumnoIdReasignar));
    }

    public void clonaGrupo() throws CloneNotSupportedException {
        grupoModelo = (GrupoModelo) grupoModelo.clone();
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public Integer getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(Integer cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public Map<Integer, CicloEscolarModelo> getCicloEscolarModeloMapa() {
        return cicloEscolarModeloMapa;
    }

    public void setCicloEscolarModeloMapa(Map<Integer, CicloEscolarModelo> cicloEscolarModeloMapa) {
        this.cicloEscolarModeloMapa = cicloEscolarModeloMapa;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }

    public GrupoModelo getGrupoModelo() {
        return grupoModelo;
    }

    public void setGrupoModelo(GrupoModelo grupoModelo) {
        this.grupoModelo = grupoModelo;
    }

    public HtmlInputText getHtmlInputText() {
        return htmlInputText;
    }

    public void setHtmlInputText(HtmlInputText htmlInputText) {
        this.htmlInputText = htmlInputText;
    }

    public CicloEscolarModelo getCicloEscolarModelo() {
        return cicloEscolarModelo;
    }

    public void setCicloEscolarModelo(CicloEscolarModelo cicloEscolarModelo) {
        this.cicloEscolarModelo = cicloEscolarModelo;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }

    public String getProfesorIdReasignar() {
        return profesorIdReasignar;
    }

    public void setProfesorIdReasignar(String profesorIdReasignar) {
        this.profesorIdReasignar = profesorIdReasignar;
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public Integer getCicloEscolarIdReasignar() {
        return cicloEscolarIdReasignar;
    }

    public void setCicloEscolarIdReasignar(Integer cicloEscolarIdReasignar) {
        this.cicloEscolarIdReasignar = cicloEscolarIdReasignar;
    }

    public List<AlumnoModelo> getAlumnoModeloLista() {
        return alumnoModeloLista;
    }

    public void setAlumnoModeloLista(List<AlumnoModelo> alumnoModeloLista) {
        this.alumnoModeloLista = alumnoModeloLista;
    }

    public String getAlumnoIdReasignar() {
        return alumnoIdReasignar;
    }

    public void setAlumnoIdReasignar(String alumnoIdReasignar) {
        this.alumnoIdReasignar = alumnoIdReasignar;
    }

    public List<GrupoModelo> getGrupoModeloPorProfesorLista() {
        return grupoModeloPorProfesorLista;
    }

    public void setGrupoModeloPorProfesorLista(List<GrupoModelo> grupoModeloPorProfesorLista) {
        this.grupoModeloPorProfesorLista = grupoModeloPorProfesorLista;
    }

    public String getGrupoIdReasignar() {
        return grupoIdReasignar;
    }

    public void setGrupoIdReasignar(String grupoIdReasignar) {
        this.grupoIdReasignar = grupoIdReasignar;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }
}
