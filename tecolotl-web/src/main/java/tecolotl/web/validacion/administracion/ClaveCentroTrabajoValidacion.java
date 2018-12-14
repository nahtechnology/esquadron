package tecolotl.web.validacion.administracion;

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

@Named
public class ClaveCentroTrabajoValidacion implements Validator {

    @Inject
    private EscuelaSesionBean escuelaSesionBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (escuelaSesionBean.buscaDuplicado((String)value)) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Llave duplicada", "Clave de escuela duplicada");
            throw new ValidatorException(facesMessage);
        }
    }
}
