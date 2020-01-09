package tecolotl.web.profesor;

import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;
import tecolotl.profesor.scope.CalificaGramaticaScope;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class CalificacionGramaticaControlador implements Serializable {

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private CalificaGramaticaScope calificaGramaticaScope;

    @Inject
    private Logger logger;

    private String idTarea;
    private List<GramaticaModelo> gramaticaModeloLista;

    public void buscaTareas() {
        gramaticaModeloLista = gramaticaSesionBean.busca(UUID.fromString(idTarea));
    }

    public String enviaRespuesta() {
        logger.info(gramaticaModeloLista.toString());
        calificaGramaticaScope.inserta(gramaticaModeloLista, UUID.fromString(idTarea));
        return "tareas";
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public List<GramaticaModelo> getGramaticaModeloLista() {
        return gramaticaModeloLista;
    }

    public void setGramaticaModeloLista(List<GramaticaModelo> gramaticaModeloLista) {
        this.gramaticaModeloLista = gramaticaModeloLista;
    }
}
