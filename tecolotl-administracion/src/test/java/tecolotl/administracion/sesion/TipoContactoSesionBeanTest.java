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
import tecolotl.administracion.modelo.escuela.TipoContactoModelo;
import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TipoContactoSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TipoContactoSesionBean.class, TipoContactoEntidad.class, TipoContactoModelo.class,
                        CatalogoSesionBean.class, CatalogoModelo.class, CatalagoEntidad.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TipoContactoSesionBean tipoContactoSesionBean;

    @Test
    public void busca() {
        List<TipoContactoModelo> tipoContactoModeloLista = tipoContactoSesionBean.busca();
        Assert.assertNotNull(tipoContactoModeloLista);
        Assert.assertFalse(tipoContactoModeloLista.size() == 0);
        for (TipoContactoModelo tipoContactoModelo : tipoContactoModeloLista) {
            Assert.assertNotNull(tipoContactoModelo);
            Assert.assertNotNull(tipoContactoModelo.getClave());
            Assert.assertNotNull(tipoContactoModelo.getValor());
        }
    }

    @Test
    public void buscaId() {
        TipoContactoModelo tipoContactoModelo = tipoContactoSesionBean.busca((short)0);
        Assert.assertNotNull(tipoContactoModelo);
        Assert.assertNotNull(tipoContactoModelo.getValor());
        Assert.assertNotNull(tipoContactoModelo.getClave());
    }

    @Test
    public void actualiza() {
        TipoContactoModelo tipoContactoModelo = new TipoContactoModelo((short)1);
        tipoContactoModelo.setValor("Coordinador");
        int modificado = tipoContactoSesionBean.actualiza(tipoContactoModelo);
        Assert.assertFalse(modificado == 0);
    }
}
