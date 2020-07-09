package tecolotl.web.herramienta;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import java.util.ResourceBundle;

@Singleton
public class MesajeProductor {

    @Produces
    @TipoMensaje(value = MensajeBundle.ADMINISTRACION)
    public ResourceBundle generaMensajeAdministrador() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getResourceBundle(facesContext, "administracion");
    }

    @Produces
    @TipoMensaje(value = MensajeBundle.COORDINADOR)
    public ResourceBundle generaMensajeCoordinador() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getResourceBundle(facesContext, "coordinador");
    }

    @Produces
    @TipoMensaje(value = MensajeBundle.ETIQUETA)
    public ResourceBundle genreraMensajeEtiqueta() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getResourceBundle(facesContext, "etiqueta");
    }

}
