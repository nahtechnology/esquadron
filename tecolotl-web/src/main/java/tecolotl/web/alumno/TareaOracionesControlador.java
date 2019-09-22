package tecolotl.web.alumno;

import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.scope.OracionesRespuestaScope;
import tecolotl.alumno.sesion.OracionesSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.groups.ConvertGroup;
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
        //Collections.shuffle(tareaOracionesModeloLista);
    }

    public void llenaDatos(){

    }

    public List<TareaOracionesModelo> desordena(){
        List<TareaOracionesModelo> temp = this.getTareaOracionesModeloLista();
        Collections.shuffle(temp);
        return temp;
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