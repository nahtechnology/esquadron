package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.modelo.escribir.EscribirBaseModelo;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.escribir.EscribirRespuestaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
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
                .addPackage(ActividadEntidad.class.getPackage())
                .addClasses(PersonaEntidad.class, CatalagoEntidad.class, EscribirSessionBean.class, EscribirModelo.class,
                        EscribirBaseModelo.class, EscribirNuevoValidacion.class, LoggerProducer.class, EscribirRespuestaValidacion.class,
                        ValidadorSessionBean.class, EscribirLlavePrimariaValidacion.class)
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

    @Test
    public void buscaTarea() {
        List<EscribirModelo> escribirModeloLista = escribirSessionBean.busca(1);
        assertNotNull(escribirModeloLista);
        assertFalse(escribirModeloLista.isEmpty());
        escribirModeloLista.forEach(escribirModelo -> {
            assertNotNull(escribirModelo);
            assertNotNull(escribirModelo.getPregunta());
            assertNotNull(escribirModelo.getFechaRespuesta());
            assertNotNull(escribirModelo.getTextoRespuesta());
        });
    }

    @Test
    public void respuesta() {
        EscribirModelo escribirModelo = new EscribirModelo();
        escribirModelo.setId(2);
        escribirModelo.setTextoRespuesta("Respuesta desde el servicio");
        escribirSessionBean.respuesta(escribirModelo, -21, "JcMtWwiyzpU");
    }

}
