package tecolotl.web.profesor;

import tecolotl.alumno.sesion.AlumnoSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@RequestScoped
@Named
public class CambioNivelAlumnoControlador {

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    private String idAlumno;
    private Short nivel;

    public void cambiaNivel(AjaxBehaviorEvent ajaxBehaviorEvent) {
        alumnoSesionBean.cambioNivel(UUID.fromString(idAlumno), nivel);
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

}
