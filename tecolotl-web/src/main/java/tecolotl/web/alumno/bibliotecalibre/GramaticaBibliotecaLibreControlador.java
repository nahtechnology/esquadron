package tecolotl.web.alumno.bibliotecalibre;

import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;
import tecolotl.web.alumno.ActividadBibliotecaLibre;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class GramaticaBibliotecaLibreControlador {

    @Inject
    private ActividadBibliotecaLibre actividadBibliotecaLibre;

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private Logger logger;

    private List<GramaticaModelo> gramaticaModeloLista;

    @PostConstruct
    public void inicio() {
        logger.info(actividadBibliotecaLibre.getActividadModelo().toString());
        gramaticaModeloLista = gramaticaSesionBean.busca(actividadBibliotecaLibre.getActividadModelo().getIdVideo());
    }

    public List<GramaticaModelo> getGramaticaModeloLista() {
        return gramaticaModeloLista;
    }

    public void setGramaticaModeloLista(List<GramaticaModelo> gramaticaModeloLista) {
        this.gramaticaModeloLista = gramaticaModeloLista;
    }
}
