package tecolotl.web.administracion;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AdministraActividadControlador {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    private List<ActividadModelo> actividadModeloLista;
    private String actividad;

    public void inicio() {
        actividadModeloLista = actividadSesionBean.buscaEstatus();
    }

    public String bloquea() {
        int modificados = actividadSesionBean.estatus(actividad, Boolean.FALSE);
        return "dashboard";
    }

    public List<ActividadModelo> getActividadModeloLista() {
        return actividadModeloLista;
    }

    public void setActividadModeloLista(List<ActividadModelo> actividadModeloLista) {
        this.actividadModeloLista = actividadModeloLista;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
