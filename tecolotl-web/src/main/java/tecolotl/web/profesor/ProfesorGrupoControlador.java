package tecolotl.web.profesor;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.sesion.GrupoSesionBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@SessionScoped
@Named
public class ProfesorGrupoControlador implements Serializable {

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    private GrupoModelo grupoModelo;
    private AlumnoModelo alumnoModelo;

    public void detalleGrupo(@NotNull UUID idGrupo) {
        grupoModelo = grupoSesionBean.buscaId(idGrupo);
    }

    public void detalleAlumno(@NotNull Integer idAlumno) {
        alumnoModelo = alumnoSesionBean.busca(idAlumno);
    }

    public GrupoModelo getGrupoModelo() {
        return grupoModelo;
    }

    public void setGrupoModelo(GrupoModelo grupoModelo) {
        this.grupoModelo = grupoModelo;
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }
}
