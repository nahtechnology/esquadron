package tecolotl.web.administracion.validacion;

import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.web.herramienta.MensajeBundle;
import tecolotl.web.herramienta.TipoMensaje;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

/**
 * Validación para saber si una escuela ya está registrada en la base de datos
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
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
        if (value != null && null != escuelaSesionBean.valida((String)value)) {
            FacesMessage facesMessage = new FacesMessage(resourceBundle.getString("dashboard.validation.cct"));
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
