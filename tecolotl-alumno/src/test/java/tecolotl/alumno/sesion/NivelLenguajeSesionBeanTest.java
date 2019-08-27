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
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class NivelLenguajeSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(NivelLenguajeEntidad.class, CatalagoEntidad.class, NivelLenguajeModelo.class, NivelLenguajeSesionBean.class,
                        CatalogoSesionBean.class, CatalogoModelo.class)

                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private NivelLenguajeSesionBean nivelLenguajeSesionBean;

    @Test
    public void busca() {
        List<NivelLenguajeModelo> nivelLenguajeModeloLista = nivelLenguajeSesionBean.busca();
        assertNotNull(nivelLenguajeModeloLista);
        assertFalse(nivelLenguajeModeloLista.isEmpty());
        nivelLenguajeModeloLista.forEach(nivelLenguajeModelo -> {
            assertNotNull(nivelLenguajeModelo);
            assertNotNull(nivelLenguajeModelo.getValor());
            assertNotNull(nivelLenguajeModelo.getClave());
        });
    }

}
