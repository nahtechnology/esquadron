package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named(value = "administracionProfesorControlador")
public class ProfesorControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private String claveCentroTrabajo;
    private List<ProfesorModelo> profesorModeloLista;

    public void inicio() {
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
    }

    public String getClaveCentroTrabajo() {
        return claveCentroTrabajo;
    }

    public void setClaveCentroTrabajo(String claveCentroTrabajo) {
        this.claveCentroTrabajo = claveCentroTrabajo;
    }

    public List<ProfesorModelo> getProfesorModeloLista() {
        return profesorModeloLista;
    }

    public void setProfesorModeloLista(List<ProfesorModelo> profesorModeloLista) {
        this.profesorModeloLista = profesorModeloLista;
    }
}
