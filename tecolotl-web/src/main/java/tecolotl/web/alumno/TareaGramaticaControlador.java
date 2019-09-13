package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
@Named
public class TareaGramaticaControlador {

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private Logger logger;

    private String respuesta;

    public void enviarRespuesta(String codigo, String idActividad) {
        GramaticaModelo gramaticaModelo = new GramaticaModelo();
        gramaticaModelo.setCodigo(codigo);
        gramaticaModelo.setActividadModelo(new ActividadModelo(idActividad));
        gramaticaModelo.setRespuesta(respuesta.trim());
        gramaticaSesionBean.respuesta(gramaticaModelo, alumnoControlador.getTareaActividadModelo().getId());
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
