package tecolotl.web.administracion.controlador;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class EscuelaControlador implements Serializable {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private EscuelaBaseModelo escuelaBaseModelo;
    private ProfesorModelo profesorModelo;

    public void busca(String claveCentroTrabajo) {
        escuelaBaseModelo = escuelaSesionBean.busca(claveCentroTrabajo);
    }

    public EscuelaBaseModelo getEscuelaBaseModelo() {
        return escuelaBaseModelo;
    }

    public void setEscuelaBaseModelo(EscuelaBaseModelo escuelaBaseModelo) {
        this.escuelaBaseModelo = escuelaBaseModelo;
    }

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }
}