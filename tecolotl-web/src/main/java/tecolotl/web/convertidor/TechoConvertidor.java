package tecolotl.web.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "tecolotl.web.convertidor.TechoConvertidor")
public class TechoConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Float valor = new Float(value);
        Double aDouble = Math.ceil(valor);
        return aDouble.intValue();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
