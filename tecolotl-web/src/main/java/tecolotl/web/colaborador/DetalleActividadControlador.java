package tecolotl.web.colaborador;

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
public class DetalleActividadControlador {
    private List<GlosarioModelo> glosarioModeloLista;

    @Inject
    private GlosarioSesionBean glosarioSesionBean;
    @PostConstruct
    public void init(){
        glosarioModeloLista = glosarioSesionBean.busca(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("video"));
    }

    public List<GlosarioModelo> getGlosarioModeloLista() {
        return glosarioModeloLista;
    }

    public void setGlosarioModeloLista(List<GlosarioModelo> glosarioModeloLista) {
        this.glosarioModeloLista = glosarioModeloLista;
    }
}
