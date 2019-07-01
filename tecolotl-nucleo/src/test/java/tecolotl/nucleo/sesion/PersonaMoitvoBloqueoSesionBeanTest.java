package tecolotl.nucleo.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaMotivoBloqueoModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PersonaMoitvoBloqueoSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "prueba.war")
                .addPackage(CatalogoModelo.class.getPackage()).addPackage(PersonaMotivoBloqueoEntidad.class.getPackage())
                .addPackage(PersonaMoitvoBloqueoSesionBean.class.getPackage()).addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(LoggerProducer.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private PersonaMoitvoBloqueoSesionBean personaMoitvoBloqueoSesionBean;

    @Test
    public void buscaFiltro() {
        List<PersonaMotivoBloqueoModelo> personaMotivoBloqueoModeloLista = personaMoitvoBloqueoSesionBean.busca("Sin Bloqueo");
        assertNotNull(personaMotivoBloqueoModeloLista);
        assertFalse(personaMotivoBloqueoModeloLista.isEmpty());
        personaMotivoBloqueoModeloLista.forEach(p -> {
            assertNotNull(p);
            assertNotNull(p.getValor());
            assertFalse(p.getValor().isEmpty());
        });
    }

}
