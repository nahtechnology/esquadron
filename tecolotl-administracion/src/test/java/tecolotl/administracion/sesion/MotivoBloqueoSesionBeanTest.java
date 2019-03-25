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
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class MotivoBloqueoSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MotivoBloqueoSesionBean.class, MotivoBloqueoModelo.class, MotivoBloqueoEntidad.class,
                        CatalogoModelo.class, CatalogoSesionBean.class, CatalagoEntidad.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private MotivoBloqueoSesionBean motivoBloqueoSesionBean;

    @Test
    public void busca() {
        List<MotivoBloqueoModelo> motivoBloqueoModeloLista = motivoBloqueoSesionBean.busca();
        Assert.assertNotNull(motivoBloqueoModeloLista);
        Assert.assertFalse(motivoBloqueoModeloLista.isEmpty());
        for (MotivoBloqueoModelo motivoBloqueoModelo : motivoBloqueoModeloLista) {
            Assert.assertNotNull(motivoBloqueoModelo.getClave());
            Assert.assertNotNull(motivoBloqueoModelo.getValor());
        }
    }

    @Test
    public void buscaId() {
        MotivoBloqueoModelo motivoBloqueoModelo = motivoBloqueoSesionBean.busca((short)0);
        Assert.assertNotNull(motivoBloqueoModelo);
        Assert.assertNotNull(motivoBloqueoModelo.getValor());
        Assert.assertNotNull(motivoBloqueoModelo.getClave());
    }

    @Test
    public void actualiza() {
        MotivoBloqueoModelo motivoBloqueoModelo = new MotivoBloqueoModelo((short)1);
        motivoBloqueoModelo.setValor("Falta de pago");
        int modificados = motivoBloqueoSesionBean.actualiza(motivoBloqueoModelo);
        Assert.assertTrue(modificados > 0);
    }

}
