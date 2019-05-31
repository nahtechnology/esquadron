package tecolotl.web.administracion.validacion;

import tecolotl.administracion.sesion.EscuelaSesionBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@FacesValidator("escuelaExisteValidacion")
public class EscuelaValidacion implements Validator {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String nombre = escuelaSesionBean.valida((String) value);
        if (nombre != null) {
            FacesMessage facesMessage = new FacesMessage("Mensaje de error");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
