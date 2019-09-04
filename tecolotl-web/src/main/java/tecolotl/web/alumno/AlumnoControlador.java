package tecolotl.web.alumno;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class AlumnoControlador implements Serializable {

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    private AlumnoModelo alumnoModelo;

    @PostConstruct
    public void init(){
        alumnoModelo = alumnoSesionBean.busca(2);
    }

    public AlumnoModelo getAlumnoModelo() {
        return alumnoModelo;
    }

    public void setAlumnoModelo(AlumnoModelo alumnoModelo) {
        this.alumnoModelo = alumnoModelo;
    }
}
