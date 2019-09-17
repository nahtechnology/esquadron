package tecolotl.web.alumno;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.scope.RelacionOracionRespuestaScope;
import tecolotl.alumno.sesion.RelacionarOracionSesionBean;
import tecolotl.alumno.sesion.RelacionarSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Named
public class RelacionarOracionControlador {

    @Inject
    private Logger logger;

    @Inject
    private RelacionarOracionSesionBean relacionarOracionSesionBean;

    @Inject
    private RelacionOracionRespuestaScope relacionOracionRespuestaScope;

    @Inject
    private AlumnoControlador alumnoControlador;

    private String idRespuesta;
    private List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista;


    @PostConstruct
    public void init() {
        tareaRelacionarOracionModeloLista = relacionarOracionSesionBean.busca(alumnoControlador.getTareaActividadModelo().getId());

    }

    public void llenarTareaModelo(){
        String[] respuestas = idRespuesta.split(",");
        for (int i = 0; i < respuestas.length; i++) {
            tareaRelacionarOracionModeloLista.get(i).setRespuesta(Integer.parseInt(respuestas[i]));
        }
        relacionOracionRespuestaScope.respuesta(tareaRelacionarOracionModeloLista);
    }

    public String respuestaVal(Integer id){
        String resp = "";
        for (TareaRelacionarOracionModelo tareaRelacionarOracionModelo : tareaRelacionarOracionModeloLista){
            if (tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId().equals(id)){
                resp = tareaRelacionarOracionModelo.getRelacionarOracionModelo().getRespuesta();
                break;
            }
        }
        return resp;
    }
    public List<TareaRelacionarOracionModelo> desordenaLista(List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista){
        List<TareaRelacionarOracionModelo> temporal = tareaRelacionarOracionModeloLista;
        Collections.shuffle(temporal);
        return temporal;
    }

    public List<TareaRelacionarOracionModelo> getTareaRelacionarOracionModeloLista() {
        return tareaRelacionarOracionModeloLista;
    }

    public void setTareaRelacionarOracionModeloLista(List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista) {
        this.tareaRelacionarOracionModeloLista = tareaRelacionarOracionModeloLista;
    }

    public String getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(String idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

}

