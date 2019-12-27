package tecolotl.nucleo.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class PersonaSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "prueba.war")
                .addPackage(CatalogoModelo.class.getPackage()).addPackage(PersonaMotivoBloqueoEntidad.class.getPackage())
                .addPackage(PersonaMoitvoBloqueoSesionBean.class.getPackage()).addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(LoggerProducer.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private PersonaSesionBean personaSesionBean;

    @Test
    public void buscaApodos() {
        List<String> stringLista = personaSesionBean.buscaApodo("21DBA0014J");
        Assert.assertNotNull(stringLista);
        Assert.assertFalse(stringLista.isEmpty());
        stringLista.forEach(apodo -> Assert.assertNotNull(apodo));
    }
}
