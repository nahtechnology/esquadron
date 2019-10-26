package tecolotl.web.profesor;

import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class CalificacionGramaticaControlador {

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    private int idTarea;
    private List<GramaticaModelo> gramaticaModeloLista;

    public void buscaTareas() {
        gramaticaModeloLista = gramaticaSesionBean.busca(idTarea);
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public List<GramaticaModelo> getGramaticaModeloLista() {
        return gramaticaModeloLista;
    }

    public void setGramaticaModeloLista(List<GramaticaModelo> gramaticaModeloLista) {
        this.gramaticaModeloLista = gramaticaModeloLista;
    }
}
