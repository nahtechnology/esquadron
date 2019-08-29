package tecolotl.web.colaborador;

import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ClaseGlosarioSesionBean;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class DetalleActividadControlador {
    private List<GlosarioModelo> glosarioModeloLista;
    private List<ClaseGlosarioModelo> claseGlosarioModeloLista;
    private GlosarioModelo glosarioModelo;
    private String video;

    @Inject
    private ClaseGlosarioSesionBean claseGlosarioSesionBean;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private Logger logger;

    @PostConstruct
    public void init() {
        video = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("video");
        logger.info(video);
        glosarioModeloLista = glosarioSesionBean.busca(video);
        logger.info(glosarioModeloLista.toString());
        claseGlosarioModeloLista = claseGlosarioSesionBean.busca();
        logger.info(claseGlosarioModeloLista.toString());
        glosarioModelo = new GlosarioModelo();
        logger.info(glosarioModelo.toString());
    }

    public void agregaGlosario(){
        glosarioSesionBean.agregar(glosarioModelo, video);
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }

    public List<ClaseGlosarioModelo> getClaseGlosarioModeloLista() {
        return claseGlosarioModeloLista;
    }

    public void setClaseGlosarioModeloLista(List<ClaseGlosarioModelo> claseGlosarioModeloLista) {
        this.claseGlosarioModeloLista = claseGlosarioModeloLista;
    }

    public GlosarioModelo getGlosarioModelo() {
        return glosarioModelo;
    }

    public void setGlosarioModelo(GlosarioModelo glosarioModelo) {
        this.glosarioModelo = glosarioModelo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
