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
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.direccion.DireccionModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DireccionSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(EscuelaBaseModelo.class.getPackage())
                .addPackage(ColoniaModelo.class.getPackage())
                .addClasses(CatalagoEntidad.class, CatalogoModelo.class, PersonaEntidad.class,DireccionSesionBean.class,
                        LoggerProducer.class, ColoniaNuevaValidacion.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private DireccionSesionBean direccionSesionBean;

    @Test
    public void busca() {
        DireccionModelo direccionModelo = direccionSesionBean.busca(28);
        Assert.assertNotNull(direccionModelo);
        Assert.assertNotNull(direccionModelo.getEstado());
        Assert.assertFalse(direccionModelo.getEstado().isEmpty());
        Assert.assertNotNull(direccionModelo.getMunicipio());
        Assert.assertFalse(direccionModelo.getMunicipio().isEmpty());
    }

    @Test(expected = EJBException.class)
    public void buscaVacio() {
        DireccionModelo direccionModelo = direccionSesionBean.busca(120066);
    }

    @Test
    public void buscaCodigoPosal() {
        DireccionModelo direccionModelo = direccionSesionBean.busca("74120");
        Assert.assertNotNull(direccionModelo);
        Collection<ColoniaModelo> coloniaModeloColeccion = direccionModelo.getColoniaModeloCollection();
        Assert.assertNotNull(coloniaModeloColeccion);
        Assert.assertFalse(coloniaModeloColeccion.isEmpty());
        for (ColoniaModelo coloniaModelo : coloniaModeloColeccion) {
            Assert.assertNotNull(coloniaModelo);
            Assert.assertNotNull(coloniaModelo.getId());
            Assert.assertNotNull(coloniaModelo.getCodigoPostal());
            Assert.assertNotNull(coloniaModelo.getNombre());
        }
    }

    @Test
    public void inserta() {
        ColoniaModelo coloniaModelo = new ColoniaModelo();
        coloniaModelo.setNombre("Nombre de la colonia");
        coloniaModelo.setCodigoPostal("74120");
        int idGenerado = direccionSesionBean.inserta(coloniaModelo, 1646);
        Assert.assertTrue(idGenerado > 0);
    }

    @Test(expected = Exception.class)
    public void insertaVacio() {
        ColoniaModelo coloniaModelo = null;
        direccionSesionBean.inserta(coloniaModelo, 1646);
    }

    @Test
    public void actualiza() {
        ColoniaModelo coloniaModelo = new ColoniaModelo(120067);
        coloniaModelo.setNombre("Nombre DE LA colonia");
        coloniaModelo.setCodigoPostal("74121");
        int modificados = direccionSesionBean.actualiza(coloniaModelo);
        Assert.assertTrue(modificados > 0);
    }

}
