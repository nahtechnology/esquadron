package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.modelo.EscribirBaseModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class EscribirSessionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ActividadEntidad.class.getPackage()).addClasses(PersonaEntidad.class, CatalagoEntidad.class, EscribirSessionBean.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private EscribirSessionBean escribirSessionBean;

    @Test
    public void buscaEscrbir() {
        List<EscribirBaseModelo> escribirBaseModeloLista = escribirSessionBean.busca("JcMtWwiyzpU");
        assertNotNull(escribirBaseModeloLista);
        assertFalse(escribirBaseModeloLista.isEmpty());
        escribirBaseModeloLista.forEach(escribirBaseModelo -> {
            assertNotNull(escribirBaseModelo);
            assertNotNull(escribirBaseModelo.getId());
            assertNotNull(escribirBaseModelo.getPregunta());
        });
    }

}
