package tecolotl.administracion.persistencia.vista;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AlumnoEscuelaVistaTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ContactoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addClass(AlumnoEscuelaVista.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void busca() {

    }

}
