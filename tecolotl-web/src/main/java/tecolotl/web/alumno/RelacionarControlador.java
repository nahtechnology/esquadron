package tecolotl.web.alumno;

import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.scope.RelacionarRespuestaScope;
import tecolotl.alumno.sesion.RelacionarSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@ViewScoped
@Named
public class RelacionarControlador implements Serializable {

    @Inject
    private RelacionarSesionBean relacionarSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private RelacionarRespuestaScope relacionarRespuestaScope;

    @Inject
    private Logger logger;

    private List<RelacionarModelo> relacionarModeloLista;

    @PostConstruct
    public void inicio() {
        relacionarModeloLista = relacionarSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public void enviaRespuesta() {
        relacionarRespuestaScope.respuesta(relacionarModeloLista, alumnoControlador.getTareaActividadModelo().getId());
    }

    public String buscaPalabra(String codigo) {

        return null;
    }

    public List<RelacionarModelo> getRelacionarModeloLista() {
        return relacionarModeloLista;
    }

    public void setRelacionarModeloLista(List<RelacionarModelo> relacionarModeloLista) {
        this.relacionarModeloLista = relacionarModeloLista;
    }

}
