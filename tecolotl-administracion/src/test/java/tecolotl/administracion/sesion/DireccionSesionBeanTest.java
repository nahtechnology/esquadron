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
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DireccionSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(EscuelaBaseModelo.class.getPackage())
                .addPackage(ColoniaModelo.class.getPackage())
                .addClasses(CatalagoEntidad.class, CatalogoModelo.class, PersonaEntidad.class,DireccionSesionBean.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Test
    public void busca() {
        DireccionModelo direccionModelo = direccionSesionBean.busca(28);
    }

}
