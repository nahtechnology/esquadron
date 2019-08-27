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
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.escribir.EscribirEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GlosarioSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(EscribirEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(EscribirModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Test
    public void buscaActivdad() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca("DNHmujbuC74");
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getImagen());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void buscaPalabra() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.buscaPalabra("palabra");
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getImagen());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void buscaTarea() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca(-20);
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getImagen());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void inserta() {
        GlosarioModelo glosarioModelo = new GlosarioModelo();
        glosarioModelo.setSignificado("Significado");
        glosarioModelo.setPalabra("palabra");
        glosarioModelo.setClaseGlosarioModelo(new ClaseGlosarioModelo((short)2));
        glosarioModelo.setImagen("palabra".getBytes());
        glosarioSesionBean.agregar(glosarioModelo, "DNHmujbuC74");
        assertNotNull(glosarioModelo);
    }
}
