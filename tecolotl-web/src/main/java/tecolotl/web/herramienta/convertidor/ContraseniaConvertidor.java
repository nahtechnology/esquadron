package tecolotl.web.herramienta.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.nio.charset.StandardCharsets;

@FacesConverter(value = "tecoltol.web.convertidor.Contrasenia")
public class ContraseniaConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value == null ? null : value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null ? null : new String((byte[])value, StandardCharsets.UTF_8);
    }

}
