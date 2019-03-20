package tecolotl.nucleo.herramienta;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class TransformaObjectoSessionBean {

    @Inject
    private Logger logger;

    public <T> T transforma(Object padre, Class<T> clazz) {
        T objecto = null;
        try {
            Constructor<T> constructor = clazz.getConstructor();
            objecto = constructor.newInstance();
            for (Field campo : clazz.getDeclaredFields()) {
              //  PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
            }
        } catch (NoSuchMethodException ex) {
            logger.warning("Metodo no encontrado:"+ex.getMessage());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            logger.log(Level.WARNING, "No se puede invocar construcotr:" + ex.getMessage(), ex);
        }
        return objecto;
    }
}
