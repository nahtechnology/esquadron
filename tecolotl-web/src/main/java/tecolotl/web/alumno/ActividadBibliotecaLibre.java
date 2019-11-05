package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@FlowScoped(value = "biblioteca-libre")
public class ActividadBibliotecaLibre implements Serializable {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    private ActividadModelo actividadModelo;

    public String principal() {
        return "/alumno/dashboard.xhtml";
    }

    public String buscaActividad(String IdActividad) {
        actividadModelo = actividadSesionBean.detalle(IdActividad);
        return "activity-free-library.xhtml";
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }
}