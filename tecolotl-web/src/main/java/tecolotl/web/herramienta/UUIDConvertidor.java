package tecolotl.web.herramienta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

@FacesConverter(value = "tecolotl.web.UUIDConverter")
public class UUIDConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value == null ? "" : UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null ? "No id" : ((UUID)value).toString();
    }
}
