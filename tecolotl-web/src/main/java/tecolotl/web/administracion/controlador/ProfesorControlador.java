package tecolotl.web.administracion.controlador;

import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.sesion.ProfesorSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named(value = "administracionProfesorControlador")
public class ProfesorControlador {

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    private String claveCentroTrabajo;
    private List<ProfesorModelo> profesorModeloLista;
    private ProfesorModelo profesorModelo;

    public void inicio() {
        profesorModeloLista = profesorSesionBean.buscaPorEscuela(claveCentroTrabajo);
    }

    public void busca(UUID idProfesor) {
        profesorModelo = profesorSesionBean.busca(idProfesor);
    }

    public void inserta() {
        profesorSesionBean.inserta(profesorModelo);
    }

    public void actualiza() {
        profesorSesionBean.actualiza(profesorModelo);
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

    public ProfesorModelo getProfesorModelo() {
        return profesorModelo;
    }

    public void setProfesorModelo(ProfesorModelo profesorModelo) {
        this.profesorModelo = profesorModelo;
    }
}
