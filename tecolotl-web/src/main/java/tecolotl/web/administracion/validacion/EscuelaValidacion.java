package tecolotl.web.administracion.validacion;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@RequestScoped
@Named
public class EscuelaValidacion implements Validator {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Inject
    @TipoMensaje(MensajeBundle.ADMINISTRACION)
    private ResourceBundle resourceBundle;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nombre = escuelaSesionBean.valida((String) value);
        if (nombre != null) {
            FacesMessage facesMessage = new FacesMessage(resourceBundle.getString("dashboard.validation.cct"));
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
