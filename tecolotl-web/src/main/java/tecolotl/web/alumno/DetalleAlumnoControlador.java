package tecolotl.web.alumno;

import tecolotl.alumno.modelo.DetalleAlumnoModelo;
import tecolotl.alumno.sesion.AlumnoSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
public class DetalleAlumnoControlador {

    private DetalleAlumnoModelo detalleAlumnoModelo;

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @PostConstruct
    public void init(){
        detalleAlumnoModelo = alumnoSesionBean.detalle(alumnoControlador.getAlumnoModelo().getId());
    }

    public DetalleAlumnoModelo getDetalleAlumnoModelo() {
        return detalleAlumnoModelo;
    }

    public void setDetalleAlumnoModelo(DetalleAlumnoModelo detalleAlumnoModelo) {
        this.detalleAlumnoModelo = detalleAlumnoModelo;
    }
}
