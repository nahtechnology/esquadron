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
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RelacionarSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(RelacionarEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private RelacionarSesionBean relacionarSesionBean;

    @Test
    public void buscaActividad() {
        List<RelacionarModelo> relacionarSesionBeanLista = relacionarSesionBean.busca("0_1NU60qHWs");
        assertNotNull(relacionarSesionBeanLista);
        assertFalse(relacionarSesionBeanLista.isEmpty());
        relacionarSesionBeanLista.forEach(relacionarModelo -> {
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo.getCodigo());
            assertNotNull(relacionarModelo.getPalabra());
            assertNotNull(relacionarModelo.getIdActividad());
        });
    }

    @Test
    public void buscaTarea() {
        List<RelacionarModelo> relacionarSesionBeanLista = relacionarSesionBean.busca(2);
        assertNotNull(relacionarSesionBeanLista);
        assertFalse(relacionarSesionBeanLista.isEmpty());
        relacionarSesionBeanLista.forEach(relacionarModelo -> {
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo.getCodigo());
            assertNotNull(relacionarModelo.getPalabra());
            assertNotNull(relacionarModelo.getIdActividad());
        });
    }

    @Test
    public void respuesta() {
        RelacionarModelo relacionarModelo = new RelacionarModelo();
        relacionarModelo.setIdActividad("0_1NU60qHWs");
        relacionarModelo.setIdTarea(2);
        relacionarModelo.setCodigo("c9ae3273cc488a5a879c9f1354367bf8");
        relacionarSesionBean.respuesta(relacionarModelo, "8c373fd1a64c1a3083fff348caf4b4ab");
    }
}
