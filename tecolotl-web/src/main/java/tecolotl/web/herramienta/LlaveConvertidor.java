package tecolotl.web.herramienta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

/**
 * Convierte las llave primarias del tipo UUID a tipo cadena
 */
@FacesConverter(value = "tecolotl.web.LlaveConvertidor")
public class LlaveConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        UUID uuid = (UUID) value;
        return uuid.toString();
    }
}
