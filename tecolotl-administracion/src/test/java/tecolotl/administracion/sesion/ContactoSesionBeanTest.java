package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class ContactoSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(ContactoSesionBean.class)
                .addPackage(ContactoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ContactoSesionBean contactoSesionBean;


    @Test
    public void elimina() {
        contactoSesionBean.elimina("21DBA0035W", (short)-32765);
    }

    @Test
    public void actualiza() {
        contactoSesionBean.actualiza("21DBA0035W", (short)-32765, "Jose Maria Espiza", "2345-5467");
    }
}
