package tecolotl.web.alumno;

import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class GramaticaControlador {

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private Logger logger;

    private List<GramaticaModelo> gramaticaModeloLista;

    @PostConstruct
    public void init() {
        gramaticaModeloLista = gramaticaSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public List<GramaticaModelo> getGramaticaModeloLista() {
        return gramaticaModeloLista;
    }

    public void setGramaticaModeloLista(List<GramaticaModelo> gramaticaModeloLista) {
        this.gramaticaModeloLista = gramaticaModeloLista;
    }
}
