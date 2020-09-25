package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.*;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class HeredarAlumnoControlador implements Serializable {

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private HeredaSesionBean heredaSesionBean;

    @Inject
    private Logger logger;

    private String grupo;
    private String escuela;
    private GrupoModelo grupoModelo;
    private EscuelaBaseModelo escuelaBaseModelo;
    private List<CicloEscolarModelo> cicloEscolarModeloLista;
    private List<ProfesorModelo> profesorModeloLista;
    private int idCicloEscolar;
    private String idProfeosor;
    private List<GrupoModelo> grupoModeloLista;
    private String idGrupo;
    private List<AlumnoModelo> alumnoModeloLista;
    private List<UUID> alumnosSeleccionLista;

    public void inicio() {
        escuelaBaseModelo = new EscuelaBaseModelo(escuela);
        grupoModelo = new GrupoModelo(UUID.fromString(grupo));
        cicloEscolarModeloLista = cicloEscolarSessionBean.busca(escuela);
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(escuela);
    }

    public void buscaGrupo() {
        Optional<CicloEscolarModelo> cicloEscolarModeloOptional = cicloEscolarModeloLista.stream().filter(ce -> ce.hashCode() == idCicloEscolar).findFirst();
        cicloEscolarModeloOptional.ifPresent(ce -> {
            grupoModeloLista = grupoSesionBean.busca(ce.getInicio(), ce.getFin(), escuelaBaseModelo.getClaveCentroTrabajo(), UUID.fromString(idProfeosor));
        });
        grupoModeloLista = grupoModeloLista.stream().filter(grupo -> grupo.getId().compareTo(grupoModelo.getId()) != 0).collect(Collectors.toList());
    }

    public void buscaAlumnosGrupo(AjaxBehaviorEvent event) {
        alumnoModeloLista = grupoAlumnoSesionBean.buscaAlumno(UUID.fromString(idGrupo));
    }

    public void hereda() {
        heredaSesionBean.heresaAlumnos(grupoModelo.getId(), alumnosSeleccionLista);
        alumnoModeloLista = null;
        grupoModeloLista = null;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public List<CicloEscolarModelo> getCicloEscolarModeloLista() {
        return cicloEscolarModeloLista;
    }

    public void setCicloEscolarModeloLista(List<CicloEscolarModelo> cicloEscolarModeloLista) {
        this.cicloEscolarModeloLista = cicloEscolarModeloLista;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }

    public int getIdCicloEscolar() {
        return idCicloEscolar;
    }

    public void setIdCicloEscolar(int idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public String getIdProfeosor() {
        return idProfeosor;
    }

    public void setIdProfeosor(String idProfeosor) {
        this.idProfeosor = idProfeosor;
    }

    public List<GrupoModelo> getGrupoModeloLista() {
        return grupoModeloLista;
    }

    public void setGrupoModeloLista(List<GrupoModelo> grupoModeloLista) {
        this.grupoModeloLista = grupoModeloLista;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<UUID> getAlumnosSeleccionLista() {
        return alumnosSeleccionLista;
    }

    public void setAlumnosSeleccionLista(List<UUID> alumnosSeleccionLista) {
        this.alumnosSeleccionLista = alumnosSeleccionLista;
    }

    public List<AlumnoModelo> getAlumnoModeloLista() {
        return alumnoModeloLista;
    }

    public void setAlumnoModeloLista(List<AlumnoModelo> alumnoModeloLista) {
        this.alumnoModeloLista = alumnoModeloLista;
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }
}
