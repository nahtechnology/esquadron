package tecolotl.administracion.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CoordinadorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(EscuelaBaseModelo.class.getPackage())
                .addPackage(ColoniaModelo.class.getPackage())
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                .addPackage(ColoniaNuevaValidacion.class.getPackage())
                .addClasses(CatalagoEntidad.class, CatalogoModelo.class, PersonaEntidad.class,DireccionSesionBean.class,
                        LoggerProducer.class, ColoniaNuevaValidacion.class, CoordinadorSesionBean.class, CoordinadorModelo.class,
                        PersonaModelo.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private CoordinadorSesionBean coordinadorSesionBean;

    @Test
    public void buscaFiltrado() {
        List<CoordinadorModelo> coordinadorModeloLista = coordinadorSesionBean.busca("21DBS0029K");
        assertNotNull(coordinadorModeloLista);
    }
}
