package tecolotl.web.profesor.validacion;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import java.util.Date;
import java.util.logging.Logger;

@FacesValidator(value = "tecolotl.web.profesor.FechaFutura")
public class FechaFuturaValidacion implements Validator {

    @Inject
    private Logger logger;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date fechaValidar = (Date)value;
        Date hoy = new Date();
        if (fechaValidar != null && fechaValidar.after(hoy)) {
            FacesMessage facesMessage = new FacesMessage();
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesMessage.setSummary("Fecha futura");
            throw new ValidatorException(facesMessage);
        }
    }

}
