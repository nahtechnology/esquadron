package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class BibliotecaLibreControlador {

    @Inject
    private ActividadSesionBean actividadSesionBean;

    private List<ActividadModelo> actividadModeloLista;

    @PostConstruct
    public void inicio() {
        actividadModeloLista = actividadSesionBean.busca(1);
//        actividadModeloLista = (List<ActividadModelo>) actividadSesionBean.busca(1).stream().collect(Collectors.groupingBy(ActividadModelo::getNivelLenguajeModeloLista));
    }

    public List<ActividadModelo> getActividadModeloLista() {
        return actividadModeloLista;
    }

    public void setActividadModeloLista(List<ActividadModelo> actividadModeloLista) {
        this.actividadModeloLista = actividadModeloLista;
    }
}
