package tecolotl.web.herramienta;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator(value = "tecolotl.web.validacion.FechaPasada")
public class FechaPasadaValidador implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date fechaValidar = (Date)value;
        Date hoy = new Date();
        if (fechaValidar != null && fechaValidar.before(hoy)) {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesMessage.setSummary("No puede estar en el pasado");
            throw new ValidatorException(facesMessage);
        }
    }
}
