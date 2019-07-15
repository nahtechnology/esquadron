package tecolotl.web.administracion.validacion;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * Validación para la fecha de inicion de una licencia, la fecha de inicio no puede ser anterior a la fecha de
 * adquisición
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
public class FechaInicioValidacion implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; //valor no puede ser validado
        }
        Date fechaInicio = (Date) value;
        UIInput uiInput = (UIInput)component.getAttributes().get("input-fecha-adquisicion");
        Date fechaAdquisicion = (Date) uiInput.getSubmittedValue();
    }
}
