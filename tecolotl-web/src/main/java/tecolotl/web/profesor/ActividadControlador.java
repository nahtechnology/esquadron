package tecolotl.web.profesor;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;


import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ActividadControlador implements Serializable {
    private List<ActividadModelo> actividadModeloLista;
    private ActividadModelo actividadModelo;
    private String trasncrip;

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private Logger logger;

    @PostConstruct
    public void init(){
        String nivelLenguaje = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivelLenguaje");
        if(nivelLenguaje != null){
            actividadModeloLista = actividadSesionBean.busca(nivelLenguaje);
            logger.info(actividadModeloLista.toString());
        }
     }

    public void buscaTranscripcion(ActividadModelo actividadModelo){
        trasncrip = actividadSesionBean.transcripcion(actividadModelo.getIdVideo());
    }
    public String buscaTranscripcion(String idActividad){
        return actividadSesionBean.transcripcion(idActividad);
    }

    public List<ActividadModelo> getActividadModeloLista() {
        return actividadModeloLista;
    }

    public void setActividadModeloLista(List<ActividadModelo> actividadModeloLista) {
        this.actividadModeloLista = actividadModeloLista;
    }

    public ActividadModelo getActividadModelo() {
        return actividadModelo;
    }

    public void setActividadModelo(ActividadModelo actividadModelo) {
        this.actividadModelo = actividadModelo;
    }

    public String getTrasncrip() {
        return trasncrip;
    }

    public void setTrasncrip(String trasncrip) {
        this.trasncrip = trasncrip;
    }
}
