package tecolotl.web.alumno;

import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ActividadViewControlador implements Serializable {
    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private Logger logger;

    @Inject
    private AlumnoControlador alumnoControlador;

    private TareaActividadModelo tareaActividadModelo;
    private String idTarea;

    @PostConstruct
    public void inicio(){
    }

    public String seleccion(){
        logger.info("Id Tarea: ".concat(idTarea));
        tareaActividadModelo = tareaSesionBean.buscaActividad(UUID.fromString(idTarea), alumnoControlador.getAlumnoModelo().getId());
        this.tareaActividadModelo.agregarDatos(tareaSesionBean.detalles(UUID.fromString(idTarea)));
        return "transcript";
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

    public TareaActividadModelo getTareaActividadModelo() {
        return tareaActividadModelo;
    }

    public void setTareaActividadModelo(TareaActividadModelo tareaActividadModelo) {
        this.tareaActividadModelo = tareaActividadModelo;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }
}
