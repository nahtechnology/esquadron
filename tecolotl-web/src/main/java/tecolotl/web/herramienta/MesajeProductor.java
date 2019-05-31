package tecolotl.web.herramienta;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import java.util.ResourceBundle;

@Singleton
public class MesajeProductor {

    @Produces
    public ResourceBundle generaMensaje() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getResourceBundle(facesContext, "administracion");
    }
}
