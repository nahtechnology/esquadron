package tecolotl.web.profesor;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.modelo.TareaAlumnoGrupoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class GrupoAlumnoControlador {

    @Inject
    private ProfesorControlador profesorControlador;

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    private Integer idGrupo;
    private List<TareaAlumnoGrupoModelo> tareaAlumnoGrupoModeloLista;

    public void buscaDetalleAlumnos() {
        tareaAlumnoGrupoModeloLista = grupoAlumnoSesionBean.busca(idGrupo);
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
}
