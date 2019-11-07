package tecolotl.web.alumno.bibliotecalibre;

import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;
import tecolotl.web.alumno.AlumnoControlador;

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
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    private List<GramaticaModelo> gramaticaModeloLista;

    @PostConstruct
    public void inicio() {
        gramaticaModeloLista = gramaticaSesionBean.busca(alumnoControlador.getActividadModeloBibliotecaLibre().getIdVideo());
    }

    public List<GramaticaModelo> getGramaticaModeloLista() {
        return gramaticaModeloLista;
    }

    public void setGramaticaModeloLista(List<GramaticaModelo> gramaticaModeloLista) {
        this.gramaticaModeloLista = gramaticaModeloLista;
    }
}
