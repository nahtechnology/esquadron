package tecolotl.web.alumno.mapamental;

import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.scope.MapaMentalRespuestaScope;
import tecolotl.alumno.sesion.MapaMentalSessionBean;
import tecolotl.web.alumno.AlumnoControlador;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ViewScoped
@Named
public class MapaMentalControlador implements Serializable {

    @Inject
    private AlumnoControlador alumnoControlador;

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    @Inject
    private MapaMentalRespuestaScope mapaMentalRespuestaScope;

    @Inject
    private Logger logger;

    private List<TareaMapaMentalModelo> tareaMapaMentalModeloLista;
    private String cardinalidad;

    public void llenaLista() {
        tareaMapaMentalModeloLista = mapaMentalSessionBean.busca(alumnoControlador.getTareaActividadModelo().getId(), Short.parseShort(cardinalidad));
    }

    public String respondeMapaMental() {
        mapaMentalRespuestaScope.respuesta(
                tareaMapaMentalModeloLista,
                alumnoControlador.getTareaActividadModelo().getId(),
                alumnoControlador.getTareaActividadModelo().getIdActividad());
        return "success";
    }

    public void validaParametro() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!facesContext.isPostback() && facesContext.isValidationFailed()) {
            facesContext.getExternalContext().redirect("no-mindmap.xhtml");
        }
    }

    public List<TareaMapaMentalModelo> getTareaMapaMentalModeloLista() {
        return tareaMapaMentalModeloLista;
    }

    public void setTareaMapaMentalModeloLista(List<TareaMapaMentalModelo> tareaMapaMentalModeloLista) {
        this.tareaMapaMentalModeloLista = tareaMapaMentalModeloLista;
    }

    public String getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(String cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

}
