package tecolotl.web.alumno;

import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.sesion.GramaticaSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class TareaGramaticaControlador {

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private TareaSesionBean tareaSesionBean;

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

    public void incrementaReporduccion(short reproducciones, Integer idTarea) {
        tareaSesionBean.reproducciones(reproducciones,idTarea);
        for (TareaActividadModelo tareaActvidadModelo : alumnoControlador.getTareaActvidadModeloLista()) {
            if (tareaActvidadModelo.getId().compareTo(idTarea) == 0) {
                tareaActvidadModelo.setReproducciones((short)(tareaActvidadModelo.getReproducciones() + reproducciones));
            }
        }
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
