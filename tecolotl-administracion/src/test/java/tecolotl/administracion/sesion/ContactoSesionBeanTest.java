package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.dto.ContactoDto;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.ContactoEntidadPK;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ContactoSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(ContactoSesionBean.class, ContactoDto.class)
                .addPackage(ContactoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ContactoSesionBean contactoSesionBean;

    @Test
    public void inserta() {
        ContactoDto contactoDto =
            contactoSesionBean.inserta("21DBA0035W", (short)-32765, "Jose Maria Espiza Paz", "2345-546789");
        Assert.assertNotNull(contactoDto);
        Assert.assertNotNull(contactoDto.getClaveCentroTrabajo());
        Assert.assertNotNull(contactoDto.getNombre());
        Assert.assertNotNull(contactoDto.getTelefono());
    }

    @Test
    public void elimina() {
        contactoSesionBean.elimina("21DBA0035W", (short)-32765);
    }

    @Test
    public void actualiza() {
        contactoSesionBean.actualiza("21DBA0035W", (short)-32765, "Jose Maria Espiza", "2345-5467");
    }
}
