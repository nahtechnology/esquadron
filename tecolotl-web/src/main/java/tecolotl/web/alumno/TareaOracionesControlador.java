package tecolotl.web.alumno;

import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.scope.OracionesRespuestaScope;
import tecolotl.alumno.sesion.OracionesSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class TareaOracionesControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private OracionesSesionBean oracionesSesionBean;

    @Inject
    private OracionesRespuestaScope oracionesRespuestaScope;

    private String respuestaOraciones;
    private List<TareaOracionesModelo> tareaOracionesModeloLista;

    @PostConstruct
    public void init(){
        tareaOracionesModeloLista = oracionesSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());
    }

    public void llenaDatos(){
        String[] respuestas = respuestaOraciones.split("\\|");
        for (int i = 0; i < respuestas.length; i++) {
            tareaOracionesModeloLista.get(i).setRespuesta(Short.valueOf(respuestas[i]));
        }
        oracionesRespuestaScope.respuesta(tareaOracionesModeloLista, alumnoControlador.getTareaActividadModelo().getId());
    }

    public String buscaOracion(Short id) {
        for (TareaOracionesModelo tareaOracionesModelo : tareaOracionesModeloLista) {
            if (tareaOracionesModelo.getOracionesModelo().getCardinalidad().compareTo(id) == 0) {
                return tareaOracionesModelo.getOracionesModelo().getOracion();
            }
        }
        return null;
    }

    public List<TareaOracionesModelo> getTareaOracionesModeloLista() {
        return tareaOracionesModeloLista;
    }

    public void setTareaOracionesModeloLista(List<TareaOracionesModelo> tareaOracionesModeloLista) {
        this.tareaOracionesModeloLista = tareaOracionesModeloLista;
    }

    public String getRespuestaOraciones() {
        return respuestaOraciones;
    }

    public void setRespuestaOraciones(String respuestaOraciones) {
        this.respuestaOraciones = respuestaOraciones;
    }
}
