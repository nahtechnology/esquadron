package tecolotl.web.alumno;

import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalResueltoModelo;
import tecolotl.alumno.sesion.MapaMentalSessionBean;
import tecolotl.alumno.sesion.TareaSesionBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
@Named
public class SeleccionMapaMentalControlador {

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    @Inject
    private Logger logger;

    private List<MapaMentalResueltoModelo> mapaMentalResueltoModeloLista;
    private String idTarea;
    private TareaActividadModelo tareaActividadModelo;

    public String seleccion(){
        logger.info("Id Tarea: ".concat(idTarea));
        tareaActividadModelo = tareaSesionBean.buscaActividad(UUID.fromString(idTarea), alumnoControlador.getAlumnoModelo().getId());
        mapaMentalResueltoModeloLista = mapaMentalSessionBean.buscaResuelto(tareaActividadModelo.getId());
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

    public List<MapaMentalResueltoModelo> getMapaMentalResueltoModeloLista() {
        return mapaMentalResueltoModeloLista;
    }

    public void setMapaMentalResueltoModeloLista(List<MapaMentalResueltoModelo> mapaMentalResueltoModeloLista) {
        this.mapaMentalResueltoModeloLista = mapaMentalResueltoModeloLista;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }
}
