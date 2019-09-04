package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ActividadAlumnoControlador implements Serializable {
    private List<ActividadModelo> actividadModeloLista;

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @PostConstruct
    public void init(){
        String nivelLenguaje = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivelLenguaje");
        actividadModeloLista = actividadSesionBean.busca(nivelLenguaje);
    }

    public List<ActividadModelo> getActividadModeloLista() {
        return actividadModeloLista;
    }

    public void setActividadModeloLista(List<ActividadModelo> actividadModeloLista) {
        this.actividadModeloLista = actividadModeloLista;
    }
}
