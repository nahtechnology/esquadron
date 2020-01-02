package tecolotl.web.herramienta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@FacesConverter(value = "tecolotl.web.ContraseniaConvertidor")
public class ContraseniaConvertidor implements Converter {

    Charset charset = StandardCharsets.UTF_8;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value == null ? null : value.getBytes(charset);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof String) {
            return value.toString();
        }
        return value == null ? "" : new String((byte[])value, charset);
    }
}
