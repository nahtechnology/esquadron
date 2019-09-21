package tecolotl.web.alumno;

import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.GlosarioSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class GlosarioControlador {
    private List<GlosarioModelo> glosarioModeloLista;
    private GlosarioModelo glosarioModelo;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @PostConstruct
    public void init(){
//        String idAvtividad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idActividad");
//        glosarioModeloLista = glosarioSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }

    public GlosarioModelo getGlosarioModelo() {
        return glosarioModelo;
    }

    public void setGlosarioModelo(GlosarioModelo glosarioModelo) {
        this.glosarioModelo = glosarioModelo;
    }
}
