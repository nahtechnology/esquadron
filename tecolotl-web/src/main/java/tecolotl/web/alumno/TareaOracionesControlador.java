package tecolotl.web.alumno;

import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.scope.OracionesRespuestaScope;
import tecolotl.alumno.sesion.OracionesSesionBean;
import tecolotl.alumno.sesion.TareaSesionBean;
import tecolotl.nucleo.herramienta.AlmacenamientoEnum;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class TareaOracionesControlador implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private OracionesSesionBean oracionesSesionBean;

    @Inject
    private OracionesRespuestaScope oracionesRespuestaScope;

    private String respuestaOraciones;
    private List<TareaOracionesModelo> tareaOracionesModeloLista;
    private String idTarea;
    private TareaActividadModelo tareaActividadModelo;


    public String seleccion(){
        logger.info("Id Tarea: ".concat(idTarea));
        tareaActividadModelo = tareaSesionBean.buscaActividad(UUID.fromString(idTarea), alumnoControlador.getAlumnoModelo().getId());
        tareaOracionesModeloLista = oracionesSesionBean.busca(tareaActividadModelo.getId());
        return "more-activities";
    }

    public void validarParametro(){
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            if (!facesContext.isPostback() && facesContext.isValidationFailed()) {
                facesContext.getExternalContext().redirect("activities.xhtml");
            }
        }catch(IOException ioe){
            logger.severe("Ocurrio un error: ".concat(ioe.getMessage()));
        }
    }

    public void llenaDatos(){
        String[] respuestas = respuestaOraciones.split("\\|");
        for (int i = 0; i < respuestas.length; i++) {
            tareaOracionesModeloLista.get(i).setRespuesta(Short.valueOf(respuestas[i]));
        }
        oracionesRespuestaScope.respuesta(tareaOracionesModeloLista, tareaActividadModelo.getId());
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

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public TareaActividadModelo getTareaActividadModelo() {
        return tareaActividadModelo;
    }

    public void setTareaActividadModelo(TareaActividadModelo tareaActividadModelo) {
        this.tareaActividadModelo = tareaActividadModelo;
    }
}
