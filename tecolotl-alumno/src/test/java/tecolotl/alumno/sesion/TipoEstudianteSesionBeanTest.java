package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class TipoEstudianteSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(CatalogoModelo.class.getPackage())
            .addPackage(TipoEstudianteModelo.class.getPackage())
            .addClasses(CatalagoEntidad.class, CatalogoSesionBean.class, TipoEstudianteEntidad.class,
                TipoEstudianteSesionBean.class, LoggerProducer.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TipoEstudianteSesionBean tipoEstudianteSesionBean;

    @Test
    public void busca(){
        List<TipoEstudianteModelo> tipoEstudianteModeloLista = tipoEstudianteSesionBean.busca();
        Assert.assertNotNull(tipoEstudianteModeloLista);
        Assert.assertFalse(tipoEstudianteModeloLista.isEmpty());
        for (TipoEstudianteModelo tipoEstudianteModelo : tipoEstudianteModeloLista){
            Assert.assertNotNull(tipoEstudianteModelo);
            Assert.assertNotNull(tipoEstudianteModelo.getClave());
            Assert.assertNotNull(tipoEstudianteModelo.getValor());
        }
    }

}
