package tecolotl.web.el;

import javax.el.ELContext;
import javax.el.ELResolver;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public class TechoResolver extends ELResolver {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Object getValue(ELContext elContext, Object base, Object propiedad) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext elContext, Object base, Object propiedad) {
        return null;
    }

    @Override
    public void setValue(ELContext elContext, Object base, Object propiedad, Object valor) {

    }

    @Override
    public boolean isReadOnly(ELContext elContext, Object base, Object propiedad) {
        return true;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext elContext, Object o) {
        List<FeatureDescriptor> featureDescriptorLista = new ArrayList<>();
        FeatureDescriptor featureDescriptor = new FeatureDescriptor();
        featureDescriptor.setDisplayName("Calcula techo");
        featureDescriptor.setName("Techo");
        featureDescriptorLista.add(featureDescriptor);
        return featureDescriptorLista.iterator();
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext elContext, Object base) {
        return base != null ? null : Number.class;
    }
}
